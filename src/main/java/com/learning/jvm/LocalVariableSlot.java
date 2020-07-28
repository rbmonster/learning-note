package com.learning.jvm;

/**
 * <pre>
 * @Description:
 * 关于局部变量槽的重用与GC的关系
 * </pre>
 *
 * @version v1.0
 * @ClassName: LocalVariableSlot
 * @Author: sanwu
 * @Date: 2020/7/28 22:27
 */
public class LocalVariableSlot {

    /**
     * 不会回收placeholder，因为还在作用域内
     * @param args
     */
//    public static void main(String[] args) {
//        byte[] placeholder = new byte[64* 1024 *1024];
//        System.gc();
//    }

    /**
     * 不会回收placeholder，虽然离开了作用域，但是局部变量槽还存在着引用
     * @param args
     */
//    public static void main(String[] args) {
//        {
//            byte[] placeholder = new byte[64* 1024 *1024];
//        }
//        System.gc();
//    }

    /**
     * 会收回placeholder，因为变量槽被重用之后，placeholder的引用被释放。
     * @param args
     */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64* 1024 *1024];
        }
        int a = 0;
        System.gc();
    }
}
