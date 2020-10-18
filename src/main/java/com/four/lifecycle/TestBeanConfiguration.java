package com.four.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @Description:
 * BeanConfiguration
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestBeanConfiguration
 * @Author: sanwu
 * @Date: 2020/10/18 10:25
 */
@Configuration
public class TestBeanConfiguration {

    @Bean(initMethod = "dynamicInit", destroyMethod = "dynamicDestroy")
    public TestBean getTestBean() {
        return new TestBean();
    }

    @Bean
    public TestBeanPostProcessor testBeanPostProcessor() {
        return new TestBeanPostProcessor();
    }

    @Bean
    public TestInstantiationAwareBeanPostProcessor testInstantiationAwareBeanPostProcessor() {
        return new TestInstantiationAwareBeanPostProcessor();
    }

    @Bean
    public TestBeanFactoryPostProcessor testBeanFactoryPostProcessor() {
        return new TestBeanFactoryPostProcessor();
    }

    @Bean
    public TestBeanDefinitionRegistryPostProcessor testBeanDefinitionRegistryPostProcessor() {
        return new TestBeanDefinitionRegistryPostProcessor();
    }
}
