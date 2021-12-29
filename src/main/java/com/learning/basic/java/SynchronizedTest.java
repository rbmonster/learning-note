package com.learning.basic.java;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

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
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 验证锁的变化，参考下文 https://mp.weixin.qq.com/s/y0srFUTsxZf8DRPNwPNqTQ
     */
    @Test
    public void test1() {
        Object o = new Object();
        out.println("未进入同步块，MarkWord 为：");
        out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            out.println(("进入同步块，MarkWord 为："));
            out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 验证锁的变化，参考下文 https://mp.weixin.qq.com/s/y0srFUTsxZf8DRPNwPNqTQ
     */
    @Test
    public void test2() {
        // 睡眠 5s
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        out.println("未进入同步块，MarkWord 为：");
        out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            out.println(("进入同步块，MarkWord 为："));
            out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    /**
     * 验证锁的变化，参考下文 https://mp.weixin.qq.com/s/y0srFUTsxZf8DRPNwPNqTQ
     */
    @Test
    public void test3() throws InterruptedException {
        // 睡眠 5s
        Thread.sleep(5000);
        Object o = new Object();
        out.println("未进入同步块，MarkWord 为：");
        out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            out.println(("进入同步块，MarkWord 为："));
            out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                out.println("新线程获取锁，MarkWord为：");
                out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });

        t2.start();
        t2.join();
        out.println("主线程再次查看锁对象，MarkWord为：");
        out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            out.println(("主线程再次进入同步块，MarkWord 为："));
            out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
