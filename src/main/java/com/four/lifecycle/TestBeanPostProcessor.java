package com.four.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <pre>
 * @Description:
 * BeanPostProcessor接口
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestBeanPostProcessor
 * @Author: sanwu
 * @Date: 2020/10/18 10:16
 */
public class TestBeanPostProcessor implements BeanPostProcessor {
    public TestBeanPostProcessor() {
        super();
        System.out.println("【BeanPostProcessor接口】初始化");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【BeanPostProcessor接口】方法postProcessBeforeInitialization对属性进行更改！");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【BeanPostProcessor接口】方法postProcessAfterInitialization对属性进行更改！");
        return bean;
    }
}
