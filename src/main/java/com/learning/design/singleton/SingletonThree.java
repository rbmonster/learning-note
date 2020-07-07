package com.learning.design.singleton;

/**
 * <pre>
 * @Description:
 * ///**
 * // * 静态内部类方式
 * // * 静态内部实现的单例是懒加载的且线程安全。
 * // * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance（只有第一次使用这个单例的实例的时候才加载，同时不会有线程安全问题）。
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: SingletonThree
 * @Author: sanwu
 * @Date: 2020/7/8 0:25
 */
public class SingletonThree {
    private SingletonThree() {}
    private static class SingletonHolder{
        private static SingletonThree singletonThree = new SingletonThree();
    }

    public static SingletonThree getSingleton(){
        return SingletonHolder.singletonThree;
    }
}
