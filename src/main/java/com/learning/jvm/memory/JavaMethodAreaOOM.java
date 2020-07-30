package com.learning.jvm.memory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <pre>
 * @Description:
 *  借助CGlib 使方法区出现内初溢出异常，在JDK7中导致内存空间溢出
 *
 * 参数：-XX:PermSize=10M
 * </pre>
 *
 * @version v1.0
 * @ClassName: JavaMethodAreaOOM
 * @Author: sanwu
 * @Date: 2020/7/29 21:23
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(true);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, args);
                }
            });
            enhancer.create();
        }
    }


    static class OOMObject{

    }
}
