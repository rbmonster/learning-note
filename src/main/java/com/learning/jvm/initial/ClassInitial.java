package com.learning.jvm.initial;

/**
 * <pre>
 * @Description:
 * 类加载初始化相关
 * </pre>
 *
 * @version v1.0
 * @ClassName: ClassInitial
 * @Author: sanwu
 * @Date: 2020/7/26 21:09
 */
public class ClassInitial {
    // 非法向前引用
    /*
    static {
        i =0;
        System.out.println(i);
    }
    static int i = 0;
     */

    /*
    如果不加上If 编译期将提示Initializer does not complete normally 并拒绝变异
     */
    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread() + " init DeadLoopClass");
                while (true){
                }
            }
        }
    }

    public static void main(String[] args) {
        ClassInitial classInitial = new ClassInitial();
    }
}
