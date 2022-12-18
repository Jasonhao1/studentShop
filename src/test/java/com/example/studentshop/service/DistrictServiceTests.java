package com.example.studentshop.service;

import com.example.studentshop.entity.District;
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
public class DistrictServiceTests {
    @Autowired
    private DistrictService service;

    @Test
    public void findByParent() {
        String parent = "86";
        List<District> list = service.getByParent(parent);
        System.err.println("count=" + list.size());
        for (District item : list) {
            System.err.println(item);
        }
    }
    @Test
    public void getNameByCode() {
        String code = "110101";
        String name = service.getNameByCode(code);
        System.err.println("name=" + name);
    }


}
