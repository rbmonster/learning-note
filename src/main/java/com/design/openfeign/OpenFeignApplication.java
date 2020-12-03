package com.design.openfeign;

import com.design.openfeign.client.HystrixFallbackClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <pre>
 * @Description:
 * open feign Test
 * </pre>
 *
 * @version v1.0
 * @ClassName: OpenFeignApplication
 * @Author: sanwu
 * @Date: 2020/11/29 16:13
 */
@SpringBootApplication
@EnableFeignClients  // include Feign in your project
public class OpenFeignApplication implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApplication.class, args);
    }

//    @Qualifier("hello")
//    @Autowired
//    HystrixFallbackClient hystrixFallbackClient;
//
    @Override
    public void run(String... args) throws Exception {
//        System.out.println(hystrixFallbackClient.iFailSometimes());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }
}
