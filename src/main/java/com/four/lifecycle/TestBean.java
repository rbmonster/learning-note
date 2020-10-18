package com.four.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * <pre>
 * @Description:
 * 测试Bean
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestBeanFactoryAware
 * @Author: sanwu
 * @Date: 2020/10/18 10:14
 */
public class TestBean implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private String name;

    private String beanName;
    private BeanFactory beanFactory;

    public TestBean() {
        System.out.println("【Bean对象】具体初始化");
    }

    /**
     * beanFactory 接口方法
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("【注入属性】注入属性name");
        this.name = name;
    }

    /**
     * BeanNameAware 接口方法
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        System.out.println("【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = s;
    }

    /**
     * InitializingBean 接口方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    /**
     * DisposableBean 接口方法
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("【DisposableBean接口】调用DisposableBean.destroy()");
    }


    /**
     * @bean 注解init-method指定方法
     */
    public void dynamicInit() {
        System.out.println("【init-method】调用@bean的init-method属性指定的初始化方法");
    }

    /**
     * @bean 注解destroy-method指定方法
     */
    public void dynamicDestroy() {
        System.out.println("【destroy-method】调用@bean的destroy-method属性指定的初始化方法");
    }
}
