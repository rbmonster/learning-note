package com.learning.design;

/**
 * <pre>
 * @Description:
 * 简单来说使用单例模式可以带来下面几个好处:
 * 对于频繁使用的对象，可以省略创建对象所花费的时间，这对于那些重量级对象而言，是非常可观的一笔系统开销；pp
 * 由于 new 操作的次数减少，因而对系统内存的使用频率也会降低，这将减轻 GC 压力，缩短 GC 停顿时间。
 * </pre>
 *
 * @version v1.0
 * @ClassName: Singleton
 * @Author: sanwu
 * @Date: 2020/5/5 0:22
 */
public class Singleton {
    //uniqueInstance 采用 volatile 关键字修饰也是很有必要
    //使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
    private static volatile Singleton singleton = null;
    private Singleton(){}

    public Singleton getSingleton() {
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

class Singleton2{
    private static Singleton2 singleton = new Singleton2();
    private Singleton2(){}
    public static Singleton2 getSingleton(){
        return singleton;
    }
}

/**
 * 静态内部类方式
 * 静态内部实现的单例是懒加载的且线程安全。
 * 只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance（只有第一次使用这个单例的实例的时候才加载，同时不会有线程安全问题）。
 */
class Singleton3{
    private static class SingletonHolder{
        private static final Singleton3 singleton = new Singleton3();
    }
    private Singleton3(){}
    public static Singleton3 getInstance(){
        return SingletonHolder.singleton;
    }
}
