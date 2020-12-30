package com.four.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <pre>
 * @Description:
 * </pre>
 *
 * @version v1.0
 * @ClassName: LearnApplication
 * @Author: 86159
 * @Date: 2020/4/19 22:17
 */
@SpringBootApplication
public class LearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }
}
