package com.example.studentshop.Controller;


import com.example.studentshop.entity.User;
import com.example.studentshop.service.IUserService;
import com.example.studentshop.service.ex.*;
import com.example.studentshop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/13
 *
 * @author haose
 */

@RestController
@RequestMapping("user")
public class UserController extends BaseController {




    @Autowired
    private IUserService iUserService;

    /**
     * 注册功能
     *
     * @param user 用户数据
     * @return
     */
    @RequestMapping("register")
    public JsonResult<Void> register(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("password2") String password2, User user, HttpSession session) {
        if (!password.equals(password2)) {
            throw new InsertException("两次密码不一样，注册失败！！请重新输入");
        } else if (username.length() == 0) {
            throw new UsernameDuplicateException("用户名不能为空");
        } else if (password.length() < 8) {
            throw new PasswordNotMatchException("密码长度小于8位");
        } else {
            iUserService.register(user);
            return new JsonResult<Void>(SUCCESS);
        }
    }

    @RequestMapping("login")
    public JsonResult<User> login(@RequestParam("username") String username,
                                  @RequestParam("password") String password, HttpSession session) {
        //执行登录功能
        User user = iUserService.login(username, password);
        //将用户名和Id绑定到session中
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        return new JsonResult<>(SUCCESS, user);
    }

    @RequestMapping("updatepassword")
    public JsonResult<Void> updatePassword(@RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword,
                                           HttpSession session) {
        //从session中获取用户名和Id
        //Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        //String username = session.getAttribute("username").toString();

        if (session.getAttribute("uid") != null) {
            iUserService.changePassword((int)session.getAttribute("uid"),(String)session.getAttribute("username"), oldPassword, newPassword);
            return new JsonResult<>(SUCCESS);
        }
        return new JsonResult<>(FAILED);
    }
    @RequestMapping("getInfo")
    public JsonResult<User> getFindByUid(HttpSession session){
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        User data = iUserService.getFindUserByUid(uid);
        return new JsonResult<>(SUCCESS,data);
    }
    @RequestMapping("changeInfo")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        if (session.getAttribute("uid") != null) {
            user.setUid(Integer.valueOf(session.getAttribute("uid").toString()));
            user.setUsername(session.getAttribute("username").toString());
            iUserService.changeInfo(user);
            return new JsonResult<>(SUCCESS);
        }
        return new JsonResult<>(FAILED);
    }



    /**
     * 上传头像文件时的最大大小,使用字节为单位(从配置文件读取)
     */
    @Value("${project.avatar-max-size}")
    private int avatarMaxSize;
    /**
     * 上传头像时允许的图片类型(从配置文件中读取)
     */
    @Value("${project.avatar-types}")
    private List<String> avatarTypes;

    @PostMapping("avatar/change")
    public JsonResult<String> changeAvatar(MultipartFile file, HttpSession session){
        System.err.println("UserController.changeAvatar()");
        // 判断上传文件是否为空
        boolean isEmpty = file.isEmpty();
        if(isEmpty) {
            throw new FileEmptyException("上传文件失败！请选择有效的头像文件！");
        }
        // 上传文件的大小(SpringBoot框架默认限制了上传文件的大小)
        long size = file.getSize();
        if(size > avatarMaxSize) {
            throw new FileSizeException("上传文件失败，不允许上传超过"+(avatarMaxSize/1024)+"KB大小的图片文件");
        }
        // 上传文件的类型
        String contentType = file.getContentType();
        if(!avatarTypes.contains(contentType)) {
            throw new FileTypeException("上传头像失败，只允许上传如下格式:\n\n"+ avatarTypes);
        }
        // 创建保存头像文件的目录（需要的话也可以写在properties配置文件中）
        String dirName = "upload";
        // 获取webapp下的某个文件夹的真实路径,"upload"是要创建的子目录名称
        String parentPath = session.getServletContext().getRealPath(dirName);

        // 目标文件夹
        File parent = new File(parentPath);
        if(!parent.exists()) {
            parent.mkdirs();
        }

        // 上传的文件保存的文件名(用当前时间表示，防止重复)
        String filename = ""+System.currentTimeMillis()+System.nanoTime();
        // 上传的文件的原始名
        String originalFilename = file.getOriginalFilename();
        // 上传的文件保存的后缀名
        /* 如果原文件名中没有小数点，则返回-1，在这种情况下，还调用substring截取，就会出现StringIndexOutOfBoundsException
   		 如果原文件名中只有1个小数点，且是文件名的第1个字符，这样的命名方式其实是表示Linux系统中的隐藏文件，且substring是不合理的
   		 可能需要进行 if (beginIndex > 0) 的判断
   		 (以上判断因为在上面对上传文件的类型做了处理，所以得到的都是正确的文件格式，以上判断就不需要了)
   		 */

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 文件名称
        String child = filename + suffix;

        // 上传的文件保存的路径及名字
        File dest = new File(parent, child);
        // 执行保存文件
        try {
            file.transferTo(dest );
        } catch (IllegalStateException e) {
            throw new FileStateException("上传文件失败！原文件可能被删除， 请稍后尝试！");
        } catch (IOException e) {
            throw new FileUploadIOException("上传文件失败！原文件读写出错，请稍后尝试");
        }

        // 将上传的文件路径保存到数据库中
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        String avatar = "/"+ dirName +"/" + child;
        iUserService.changeAvatar(uid, username, avatar );

        // 响应成功与头像路径
        return new JsonResult<>(OK, avatar);
    }

}
