package com.example.studentshop.service;

import com.example.studentshop.entity.User;
import com.example.studentshop.service.ex.*;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */


public interface IUserService {
    /**
     * 注册用户
     * @param user 尝试注册用户数据
     * @throws UsernameDuplicateException 用户名被占用异常
     * @throws InsertException 插入数据异常
     */
    void register(User user)throws UsernameDuplicateException, InsertException;

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录用户的信息
     * @throws UserNotFoundException 用户名不存在异常
     * @throws PasswordNotMatchException 密码错误的异常
     */

    User login(String username,String password) throws UserNotFoundException, PasswordNotMatchException;
    /**
     * 修改密码
     * @param uid 用户id
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     * @throws UpdateException
     * @throws UserNotFoundException
     * @throws PasswordNotMatchException
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword)
            throws UpdateException, UserNotFoundException, PasswordNotMatchException;

    /**
     * 查找用户信息
     * @param uid 用户id
     * @return 匹配的用户数,如果没有返回null
     */
    public User getFindUserByUid(Integer uid);

    /**
     * 修改个人信息
     * @param user 用户信息
     * @throws UpdateException 用户名被冻结
     * @throws UserNotFoundException 修改异常
     */
    public void changeInfo(User user) throws UpdateException, UserNotFoundException;

    /**
     *
     * @param uid
     * @param username
     * @param avatar
     */
    void changeAvatar(Integer uid, String username, String avatar);

}
