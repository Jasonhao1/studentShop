package com.example.studentshop.Controller;

import com.example.studentshop.service.ex.*;
import com.example.studentshop.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


/**
 * Created by Intellij IDEA.
 * User:  hao sen
 * Date:  2022/12/13
 *
 * @author haose
 */


public abstract class BaseController {
    public static final Integer SUCCESS = 200;
    public static final Integer FAILED = 201;

    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> jr = new JsonResult<Void>();
        jr.setMessage(e.getMessage());
        if (e instanceof UsernameDuplicateException) {
            /*
            用户名被占用
             */
            jr.setState(400);
        }else if(e instanceof UserNotFoundException){
            //用户被冻结
            jr.setState(401);
        }else if (e instanceof PasswordNotMatchException){
            //密码错误
            jr.setState(402);
        } else if (e instanceof InsertException) {
            /*
            插入数据异常
             */
            jr.setState(500);
        } else if (e instanceof UpdateException) {
            /*
            修改数据异常
             */
            jr.setState(501);
        }else if (e instanceof FileUploadException) {
            /*
            文件上传异常
             */
            jr.setState(502);
        }else if (e instanceof FileUploadIOException) {
            /*
            修改数据异常
             */
            jr.setState(503);
        }else if (e instanceof FileTypeException) {
            /*
            文件类型一场异常
             */
            jr.setState(504);
        }else if (e instanceof FileStateException) {
            /*
            文件
             */
            jr.setState(505);
        }else if (e instanceof FileSizeException) {
            /*
            上传文件大小异常
             */
            jr.setState(506);
        }else if (e instanceof FileEmptyException) {
            /*
            上传的文件为空
             */
            jr.setState(507);
        }else if (e instanceof AddressSizeLimitException) {
            /*
            地址超过最大数量上限
             */
            jr.setState(508);
        }
        return jr;
    }

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
