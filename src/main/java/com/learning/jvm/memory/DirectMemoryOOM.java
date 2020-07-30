package com.learning.jvm.memory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <pre>
 * @Description:
 * 参数：-Xmx20M -XX:MaxDirectMemorySize=10M
 * 本机直接内存溢出
 *
 *  -XX:MaxDirectMemorySize 用于指定直接内存大小
 * 真正申请内存分配的方法Unsafe::allocateMemory()
 * </pre>
 *
 * @version v1.0
 * @ClassName: DirectMemoryOOm
 * @Author: sanwu
 * @Date: 2020/7/29 22:33
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError
     * 	at sun.misc.Unsafe.allocateMemory(Native Method)
     * 	at com.learning.jvm.memory.DirectMemoryOOM.main(DirectMemoryOOM.java:27)
     */
}
