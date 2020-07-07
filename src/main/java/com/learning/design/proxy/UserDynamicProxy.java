package com.learning.design.proxy;

import java.lang.reflect.Proxy;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: UserDynamicProxy
 * @Author: sanwu
 * @Date: 2020/7/8 0:05
 */
public class UserDynamicProxy {
    private Object target;

    public UserDynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),(proxy, method, args) -> {
            System.out.println("dynamic proxy begin transaction!");
            Object result = method.invoke(target, args);
            System.out.println("dynamic proxy begin transaction!");
            return result;
        });
    }

    public static void main(String[] args) {
        IUserDAO userDAO =new UserDAO();
        IUserDAO proxy = (IUserDAO) new UserDynamicProxy(userDAO).getProxy();
        proxy.save();
    }
}
