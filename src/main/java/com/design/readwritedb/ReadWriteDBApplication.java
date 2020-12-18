package com.design.readwritedb;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <pre>
 * @Description:
 * 读写分离Application
 * </pre>
 *
 * @version v1.0
 * @ClassName: ReadWriteDBApplication
 * @Author: sanwu
 * @Date: 2020/12/17 14:54
 */
@SpringBootApplication
public class ReadWriteDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteDBApplication.class);
    }
}
