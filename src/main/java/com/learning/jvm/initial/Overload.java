package com.learning.jvm.initial;

import java.io.Serializable;

/**
 * <pre>
 * @Description:
 * 关于重载方法的理解
 *
- 重载方法的优先级，char->int->long->float->double ->Character -> Serializable -> Object ,基本类型的重载方法会按此优先级寻找对应的方法，若重载的方法参数与调用的方法不一致，则会向父类查找匹配上相同类型的方法。
 * </pre>
 *
 * @version v1.0
 * @ClassName: Overload
 * @Author: sanwu
 * @Date: 2020/7/28 22:54
 */
public class Overload {
    public static void sayHello(Object arg){
        System.out.println("hello Object");
    }
    public static void sayHello(int arg){
        System.out.println("hello int");
    }
    public static void sayHello(long arg){
        System.out.println("hello long");
    }
    public static void sayHello(Character arg){
        System.out.println("hello Character");
    }
//    public static void sayHello(char arg){
//        System.out.println("hello char");
//    }
    public static void sayHello(char... arg){
        System.out.println("hello char...");
    }
    public static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }

}
