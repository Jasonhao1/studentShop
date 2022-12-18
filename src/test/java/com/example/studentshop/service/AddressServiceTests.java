package com.example.studentshop.service;

import com.example.studentshop.entity.Address;
import com.example.studentshop.mapper.AddressMapper;
import com.example.studentshop.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by Intellij IDEA.
 *
 * @Author hao-sen
 * Date:  2022/12/15
 */

@SpringBootTest
public class AddressServiceTests {
    @Autowired
    private AddressService addressService;
    @Test
    public void addnew() {
        try {
            Integer uid = 100;
            String username = "小赵同学";
            Address address = new Address();
            address.setName("鸣人君");
            addressService.addNew(address, username, uid);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void getByUid() {
        Integer uid = 2;
        List<Address> list = addressService.getByUid(uid);
        System.err.println("count=" + list.size());
        for (Address item : list) {
            System.err.println(item);
        }
    }


    @Test
    public void setDefault() {
        try {
            Integer aid = 42;
            Integer uid = 15;
            String username = "Admin";
            addressService.setDefault(aid, uid, username);
            System.err.println("OK");
        } catch (ServiceException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }
    }


}
