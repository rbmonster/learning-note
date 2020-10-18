package com.four.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <pre>
 * @Description:
 * 主程序
 * </pre>
 *
 * @version v1.0
 * @ClassName: MainTestBean
 * @Author: sanwu
 * @Date: 2020/10/18 10:30
 */
public class MainTestBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBeanConfiguration.class);
        TestBean testBean = applicationContext.getBean(TestBean.class);
        ((AnnotationConfigApplicationContext)applicationContext).registerShutdownHook();
    }
}
