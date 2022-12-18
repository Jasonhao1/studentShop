package com.example.studentshop.mapper;

import com.example.studentshop.entity.Address;
import com.example.studentshop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user=new User();
        user.setUsername("张三");
        user.setPassword("123456");
        Integer rows=userMapper.insert(user);
        System.err.println(rows);
    }
    @Test
    public void findUserByUsername(){
        User user = userMapper.findUserByUsername("赵强");
        System.err.println(user);
    }

    @Test
    public void updatePassword(){
        Integer uid=5;
        String password="666";
        String modifiedUser="超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
        System.err.println(rows);
    }
    @Test
    public void findByUid(){
        User user = userMapper.findUserByUid(5);
        System.err.println(user);
    }
    @Test
    public void updateInfo(){
        User user = new User();
        user.setPhone("110");
        user.setUid(1);
        user.setGender(0);
        user.setEmail("jason@qq.com");
        user.setModifiedUser("李白");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfo(user);
        System.err.println(rows);
    }

    @Test
    public void updateAvatarByUid() {
        Integer uid = 6;
        String avatar = "头像路径";
        String modifiedUser = "头像管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }




}
