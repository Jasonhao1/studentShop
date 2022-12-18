package com.example.studentshop.service;

import com.example.studentshop.entity.Address;
import com.example.studentshop.service.ex.InsertException;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/15
 */


public interface AddressService {
    /**
     * 添加收货地址
     * @param address
     * @param username
     * @param uid
     */
    void addNew(Address address,String username,Integer uid) throws InsertException;



    /**
     * 查询某用户的收货地址列表
     * @param uid 用户的id
     * @return 该用户的收货地址列表
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置用户的默认地址
     * @param aid 地址的id
     * @param uid 用户的id
     * @param username 用户名
     */
    void setDefault(Integer aid,Integer uid,String username);


}
