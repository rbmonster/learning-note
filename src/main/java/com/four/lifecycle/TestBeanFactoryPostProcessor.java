package com.four.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * <pre>
 * @Description:
 * BeanFactoryPostProcessor接口
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestBeanFactoryPostProcessor
 * @Author: sanwu
 * @Date: 2020/10/18 13:36
 */
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public TestBeanFactoryPostProcessor() {
        System.out.println("【BeanFactoryPostProcessor接口】初始化");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryPostProcessor接口】调用postProcessBeanFactory方法");
    }
}
