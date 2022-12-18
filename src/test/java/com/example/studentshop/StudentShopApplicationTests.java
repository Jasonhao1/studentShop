package com.example.studentshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class StudentShopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataSource dataSource;
    //测试连接数据
    @Test
    public void getConnection() throws SQLException {
        Connection conn=dataSource.getConnection();
        System.err.println(conn);
    }




}
