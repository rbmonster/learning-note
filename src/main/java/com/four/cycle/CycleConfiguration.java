package com.four.cycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CycleConfiguration
 * @Author: sanwu
 * @Date: 2020/11/12 19:35
 */
@Configuration
@ComponentScan(basePackages = "com.four.cycle")
public class CycleConfiguration {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CycleConfiguration.class);
        context.getBean("classA");
    }
}
