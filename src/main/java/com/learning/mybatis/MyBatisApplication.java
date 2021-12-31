package com.learning.mybatis;

import com.learning.mybatis.interceptor.TenantInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: MyBatisApplication
 * @Author: sanwu
 * @Date: 2021/1/23 16:58
 */
@SpringBootApplication
@MapperScan(basePackages = "com.learning.mybatis.dao")
public class MyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }


    @Bean
    public TenantInterceptor tenantInterceptor() {
        return new TenantInterceptor();
    }
}
