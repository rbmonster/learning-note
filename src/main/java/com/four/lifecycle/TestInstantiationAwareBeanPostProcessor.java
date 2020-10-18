package com.four.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * <pre>
 * @Description:
 * InstantiationAwareBeanPostProcessor接口
 *  实例化bean 调用在初始化bean之前
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestInstantiationAwareBeanPostProcessor
 * @Author: sanwu
 * @Date: 2020/10/18 10:53
 */
public class TestInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    public TestInstantiationAwareBeanPostProcessor() {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】初始化");
    }

    /**
     * Bean初始化前调用
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】调用postProcessBeforeInstantiation");
        return null;
    }

    /**
     * Bean初始化后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】调用postProcessAfterInstantiation");
        return true;
    }

    /**
     * Bean 设置某个属性时调用
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】调用postProcessProperties");
        return pvs;
    }
}
