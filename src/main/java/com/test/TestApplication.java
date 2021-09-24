package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);

        TestController.Demo11 demo11 = new TestController.Demo11();
        TestController testController = new TestController();
        TestController.Demo22 demo22 = testController.new Demo22();
    }
}
