package com.learning.jvm;

/**
 * <pre>
 * @Description:
 * 方法静态分派的演示
 * </pre>
 *
 * @version v1.0
 * @ClassName: StaticDispatch
 * @Author: sanwu
 * @Date: 2020/7/28 22:44
 */
public class StaticDispatch {
    static abstract class Human{

    }
    static class Man extends Human{

    }
    static class Woman extends Human{

    }
    public void sayHello(Human gay) {
        System.out.println("hello, gay~");
    }
    public void sayHello(Man gay) {
        System.out.println("hello, Man~");
    }
    public void sayHello(Woman gay) {
        System.out.println("hello, Woman~");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
