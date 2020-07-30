package com.learning.jvm.initial;

/**
 * <pre>
 * @Description:
 *  动态分派的展示
 * </pre>
 *
 * @version v1.0
 * @ClassName: DynamicDispatch
 * @Author: sanwu
 * @Date: 2020/7/28 23:05
 */
public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello() ;
    }
    static class Man extends Human {
        public void sayHello() {
            System.out.println("hello, Man~");
        }
    }
    static class Woman extends Human {
        public void sayHello() {
            System.out.println("hello, Woman~");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
   
}
