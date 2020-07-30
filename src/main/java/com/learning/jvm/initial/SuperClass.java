package com.learning.jvm.initial;

/**
 * <pre>
 * @Description:
 * 虚拟机加载机制相关 初始化的顺序
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestInital
 * @Author: sanwu
 * @Date: 2020/7/25 22:23
 */
public class SuperClass {
    static {
        System.out.println("superclass initial");
    }

    public static int value  =123;
}


class SubClass extends SuperClass{
    static {
        System.out.println("subclass initial");
    }

}

class NotInitialization{
    public static void main(String[] args) {
        System.out.println(SubClass.class);
    }
}
