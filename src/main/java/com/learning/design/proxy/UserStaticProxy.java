package com.learning.design.proxy;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: UserStaticProxy
 * @Author: sanwu
 * @Date: 2020/7/8 0:02
 */
public class UserStaticProxy implements IUserDAO{
    private IUserDAO target;

    public UserStaticProxy(IUserDAO target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("begin transaction~");
        target.save();
        System.out.println("stop transaction!");
    }

    public static void main(String[] args) {
        IUserDAO userDAO = new UserDAO();
        UserStaticProxy proxy = new UserStaticProxy(userDAO);
        proxy.save();
    }
}
