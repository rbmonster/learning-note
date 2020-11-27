package com.learning.basic.java;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: AutomicTest
 * @Author: sanwu
 * @Date: 2020/11/26 16:14
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(3,12);
        atomicInteger.set(112);
        System.out.println(atomicInteger.get());
    }
}
