package com.learning.design.singleton;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: SingletonOne
 * @Author: sanwu
 * @Date: 2020/7/8 0:22
 */
public class SingletonOne {
    private static final SingletonOne singleton = new SingletonOne();

    private SingletonOne(){}

    public SingletonOne getSingleton(){
        return singleton;
    }
}
