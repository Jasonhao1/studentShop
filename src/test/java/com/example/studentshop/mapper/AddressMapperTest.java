package com.example.studentshop.mapper;

import com.example.studentshop.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author haosen
 * Date:  2022/12/15
 */

@SpringBootTest
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUID(2);
        address.setName("马大国");
        Integer rows = addressMapper.insert(address);
        System.err.println(rows);

    }
    @Test
    public void countByUid(){
        Integer rows = addressMapper.countByUid(2);
        System.err.println(rows);
    }

    @Test
    public void findByUid() {
        Integer uid = 2;
        List<Address> list = addressMapper.findByUid(uid);
        System.err.println("count=" + list.size());
        for (Address item : list) {
            System.err.println(item);
        }
    }
    @Test
    public void updateDefaultByAid() {
        Integer aid = 15;
        String modifiedUser = "ROOT";
        Date modifiedTime = new Date();
        Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
        System.err.println("rows=" + rows);
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer uid = 43;
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        System.err.println("rows=" + rows);
    }

    @Test
    public void findByAid() {
        Integer aid = 43;
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            System.out.println(1);
        }else {
            System.out.println(0);
        }
        System.err.println(result);

    }


}
