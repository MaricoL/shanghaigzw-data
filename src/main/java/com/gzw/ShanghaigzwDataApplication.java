package com.gzw;

import com.github.pagehelper.PageHelper;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.gzw.dao")
@EnableEncryptableProperties //开启配置文件加密功能jasypt
//@EnableTransactionManagement(proxyTargetClass = true)  //开启事务管理
public class ShanghaigzwDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShanghaigzwDataApplication.class, args);
    }


    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
