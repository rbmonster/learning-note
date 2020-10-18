package com.four.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * <pre>
 * @Description:
 * TestBeanDefinitionRegistryPostProcessor
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestBeanDefinitionRegistryPostProcessor
 * @Author: sanwu
 * @Date: 2020/10/18 21:39
 */
public class TestBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    public TestBeanDefinitionRegistryPostProcessor() {
        System.out.println("【BeanDefinitionRegistryPostProcessor接口】初始化");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("【BeanDefinitionRegistryPostProcessor接口】调用postProcessBeanDefinitionRegistry方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanDefinitionRegistryPostProcessor接口】调用postProcessBeanFactory方法");
    }
}
