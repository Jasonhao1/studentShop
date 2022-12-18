package com.example.studentshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.example.studentshop.mapper")
public class StudentShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentShopApplication.class, args);
    }
    /**
     * 获取MultipartConfigElement
     * 添加了@Bean注解的方法，会被spring框架调用并管理返回的类型
     * @return MultipartConfigElement类型对象，是上传文件的配置类型的对象
     */
    @Bean
    public MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 关于文件上传的全局配置
        // 头像500KB，买家秀图片1MB，买家秀视频5MB
        // 上传的文件的最大大小
        factory.setMaxFileSize(DataSize.ofMegabytes(5));
        // 请求的数据量的最大大小(请求数据量包含文件大小)
        factory.setMaxRequestSize(DataSize.ofMegabytes(5));

        return factory.createMultipartConfig();
    }

}
