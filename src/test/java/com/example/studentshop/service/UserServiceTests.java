package com.example.studentshop.service;

import com.example.studentshop.entity.User;
import com.example.studentshop.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("小贾");
            user.setPassword("12345678");
            user.setGender(1);
            user.setEmail("zhoaqiang@qq.com");
            user.setPhone("110120119");
            iUserService.register(user);
            System.err.println("OK");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void messageDigest() {
        String str = "123";
        String salt = UUID.randomUUID().toString();
        str = salt + str + salt;
        str = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        System.err.println(salt);
        System.err.println(str);
    }
    @Test
    public void login(){
        try {
            String username="赵";
            String password="123456";
            User user = iUserService.login(username, password);
            System.err.println(user);
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void changePassword(){
        try {
            Integer uid=130;
            String oldPassword="12345678";
            String newPassword="123456789";
            String modifiedUser="超级管理员";
            iUserService.changePassword(uid,modifiedUser,oldPassword,newPassword);
            System.err.println("ok");
        }catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void getfindUserByUid(){
        try{
            Integer uid=1;
            User user = iUserService.getFindUserByUid(uid);
            System.err.println(user);
        }catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void changeInfo(){
        try{
            User user = new User();
            user.setUid(15);
            user.setPhone("119");
            user.setEmail("13231@baidu.com");
            user.setGender(1);
            iUserService.changeInfo(user);
            System.err.println(user);
            System.err.println("ok");
        }catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }
    @Test
    public void changeAvatar() {
        try {
            Integer uid = 5;
            String username = "管理员";
            String avatar = "1234";
            iUserService.changeAvatar(uid, username, avatar);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }


}
