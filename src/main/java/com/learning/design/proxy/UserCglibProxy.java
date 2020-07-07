package com.learning.design.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: UserCglibProxy
 * @Author: sanwu
 * @Date: 2020/7/8 0:12
 */
public class UserCglibProxy {
    private Object target;

    public UserCglibProxy(Object target){
        this.target = target;
    }

    public Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback((InvocationHandler) (o, method, objects) -> {
            System.out.println("cglib proxy begin transaction!");
            Object result = method.invoke(target, objects);
            System.out.println("cglib proxy begin transaction!");
            return result;
        });
        return enhancer.create();
    }

    public static void main(String[] args) {
        IUserDAO userDAO = new UserDAO();
        IUserDAO proxy = (IUserDAO) new UserCglibProxy(userDAO).getProxy();
        proxy.save();
    }
}
