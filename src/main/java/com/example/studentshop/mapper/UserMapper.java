package com.example.studentshop.mapper;

import com.example.studentshop.entity.Address;
import com.example.studentshop.entity.User;
import com.example.studentshop.service.ex.PasswordNotMatchException;
import com.example.studentshop.service.ex.UpdateException;
import com.example.studentshop.service.ex.UserNotFoundException;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by Intellij IDEA.
 * User:  haosen
 * Date:  2022/12/12
 */


public interface UserMapper {
    /**
     * 插入用户数据
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     */
    User findUserByUsername(String username);
    /**
     * 修改用户密码
     */
    Integer updatePassword(Integer uid, String password, String modifiedUser, Date modifiedTime);
    /**
     * 通过用户id查找用户
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的数据则写null;
     */
    User findUserByUid(Integer uid) ;

    Integer updateInfo(User user);

    /**
     *
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid( @Param("uid") Integer uid, @Param("avatar") String avatar,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);




}
