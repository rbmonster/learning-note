package com.four.filterinterceptor.interceptor.method;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <pre>
 * @Description:
 * 方法拦截器  继承类实现
 * </pre>
 *
 * @version v1.0
 * @ClassName: CustomMethodInterceptor
 * @Author: sanwu
 * @Date: 2020/11/8 19:37
 */
@Slf4j
public class CustomMethodInterceptor implements MethodInterceptor {

//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        log.info("this is " + this.getClass().getName());
//        return null;
//    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("this is " + this.getClass().getName());
        return null;
    }
}
