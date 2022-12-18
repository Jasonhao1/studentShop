package com.example.studentshop.mapper;

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
public class DistrictMapperTests {

    @Autowired
    private DistrictMapper mapper;

    @Test
    public void findByParent() {
        String parent = "86";
        List<District> list = mapper.findByParent(parent);
        System.err.println("count=" + list.size());
        for (District item : list) {
            System.err.println(item);
        }
    }
    @Test
    public void findNameByCode() {
        String code = "110000";
        String name = mapper.findNameByCode(code);
        System.err.println("name=" + name);
    }


}
