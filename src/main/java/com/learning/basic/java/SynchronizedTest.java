package com.learning.basic.java;

import org.openjdk.jol.info.ClassLayout;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: SynchronizedTest
 * @Author: sanwu
 * @Date: 2020/11/26 16:57
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        final Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            long size = ClassLayout.parseInstance(o).instanceSize();
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
