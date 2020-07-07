package com.learning.design.singleton;

/**
 * <pre>
 * @Description:
 * 简单来说使用单例模式可以带来下面几个好处:
 * 对于频繁使用的对象，可以省略创建对象所花费的时间，这对于那些重量级对象而言，是非常可观的一笔系统开销；pp
 * 由于 new 操作的次数减少，因而对系统内存的使用频率也会降低，这将减轻 GC 压力，缩短 GC 停顿时间。
 * </pre>
 *
 * @version v1.0
 * @ClassName: SingletonTwo
 * @Author: sanwu
 * @Date: 2020/7/8 0:23
 */
public class SingletonTwo {
    private static volatile SingletonTwo singleton = null;

    private SingletonTwo() {
    }

    public static SingletonTwo getSingleton() {
        if (singleton == null) {
            synchronized (SingletonTwo.class) {
                if (singleton == null) {
                    singleton = new SingletonTwo();
                }
            }
        }
        return singleton;
    }
}
