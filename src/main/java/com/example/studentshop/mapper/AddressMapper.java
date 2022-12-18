package com.example.studentshop.mapper;

import com.example.studentshop.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/15
 */


public interface AddressMapper {
    /**
     * 插入用户数据
     * @param address 用户的地址
     * @return
     */
    Integer insert(Address address);

    /**
     * 统计某个用户的收货地址数量
     * @param uid 用户id
     * @return 该用户的收货地址量
     */
    Integer countByUid(Integer uid);
    /**
     * 查询某用户的收货地址列表
     * @param uid 用户的id
     * @return 该用户的收货地址列表
     */
    List<Address> findByUid(Integer uid);

    /**
     * 将某收货地址设置为默认
     * @param aid 收货地址的id
     * @param modifiedUser 最后修改人
     * @param modifiedTime 最后修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 将某用户的所有收货地址设置为非默认
     * @param uid 用户id
     * @return 受影响的行数
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     * 根据收货地址id查询收货地址详情
     * @param aid 收货地址id
     * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
     */
    Address findByAid(Integer aid);



}
