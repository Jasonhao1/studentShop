package com.example.studentshop.service.impi;

import com.example.studentshop.entity.User;
import com.example.studentshop.mapper.UserMapper;
import com.example.studentshop.service.IUserService;
import com.example.studentshop.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) throws UsernameDuplicateException, InsertException {
        //根据参数user对象中的username属性查询数据
        String username = user.getUsername();
        User result = userMapper.findUserByUsername(username);
        //判断查询结果是否为null
        //不等于空，说明用户已经存在了，则抛出异常
        if (result != null) {
            throw new UsernameDuplicateException("注册失败！尝试注册的用户名" + username + "已被占用");

        }
        //密码加密，生成一个随机的盐值
        String salt = UUID.randomUUID().toString();
        //执行密码加密，得到加密后的密码
        String md5Password = getMD5Password(user.getPassword(), salt);
        //将加密后的密码添加到user对象中
        user.setPassword(md5Password);
        user.setSalt(salt);

        //设置isDelete
        user.setIsDelete(0);
        //设置四个日志
        Date date = new Date();
        user.setCreatedUser(username);
        user.setCreatedTime(date);
        user.setModifiedUser(username);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        //不等于一则表示插入数据失败
        if (rows != 1) {
            throw new InsertException("注册失败！插入数据出现位置错误，请联系管理员");

        }

    }


    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        //根据参数username查询用户数据，如果数据为空，则抛出UserNotFoundException
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("查询失败，用户名不存在");
        }
        //判断用户是否被删除：如果是，则抛出UserNotFoundNotException
        if (user.getIsDelete() != 0) {
            throw new UserNotFoundException("该用户已被冻结");
        }
        //从查询结果中获取盐值
        String salt = user.getSalt();
        //将参数密码和盐值一块进行比较
        String md5Password = getMD5Password(password, salt);
        //将加密之后的密码跟查询出的密码进行对比：如果不一致，则抛出PasswordNotMatchException
        if (!user.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        //为了数据的安全，需要将查询出的密码和盐值，是否被标记未删除设为null
        user.setIsDelete(null);
        user.setPassword(null);
        user.setSalt(null);
        //返回查询到的结果
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword)
            throws UpdateException, UserNotFoundException, PasswordNotMatchException {
        User user = findUserByUserUid(uid);
        if (user == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        //判断用户是否被删除：如果是，则抛出UserNotFoundNotException
        if (user.getIsDelete() != 0) {
            throw new UserNotFoundException("该用户已被冻结");
        }
        //从查询结果中获取盐值
        String salt = user.getSalt();
        //将参数密码和盐值一块进行比较
        String md5Password = getMD5Password(oldPassword, salt);
        if (!user.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        String newMd5Password = getMD5Password(newPassword, salt);

        Integer rows = userMapper.updatePassword(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("修改密码时出现异常");
        }
    }

    @Override
    public User getFindUserByUid(Integer uid) {
        User user = findUserByUserUid(uid);
        //为了数据安全,需要将一些信息隐藏
        if (user != null) {
            user.setPassword(null);
            user.setSalt(null);

        }
        return user;
    }

    @Override
    public void changeInfo(User user) throws UpdateException, UserNotFoundException {
        User result = findUserByUserUid(user.getUid());
        if (result == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        //判断用户是否被删除：如果是，则抛出UserNotFoundNotException
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户已被冻结");
        }
        user.setModifiedUser(result.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfo(user);
        if (rows != 1) {
            throw new UpdateException("修改失败,发现未知错误");
        }

    }

    /**
     * 通过用户id查找用户
     *
     * @param uid
     * @return
     */
    private User findUserByUserUid(Integer uid) {
        return userMapper.findUserByUid(uid);
    }

    /**
     * @param username
     * @return
     */
    private User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    /**
     * @param user
     * @return
     */

    private Integer insert(User user) {
        return userMapper.insert(user);
    }


    private String getMD5Password(String password, String salt) {
        String result = salt + password + salt;
        for (int i = 0; i < 5; i++) {
            result = DigestUtils.md5DigestAsHex(result.getBytes());
        }

        return result;
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        // 调用userMapper.findByUid()查询用户数据
        User result = userMapper.findUserByUid(uid);
        // 判断查询结果(result)是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("修改用户头像失败，尝试访问的用户数据不存在！");
        }

        // 判断查询结果(result)中的isDelete属性是否为1
        if (result.getIsDelete() == 1) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("修改用户头像失败，用户数据已被删除！");
        }

        // 调用userMapper.updateAvatarByUid()执行更新，并获取返回值
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("修改用户头像失败，更新头像时出现未知错误，请联系系统管理员！");
        }
    }


}
