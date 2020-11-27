package com.learning.basic.java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestCountdownLatch
 * @Author: sanwu
 * @Date: 2020/11/26 10:58
 */
public class TestCountdownLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sdfsadfasdfasdf");
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111111111");
        }).start();
        TimeUnit.SECONDS.sleep(1);
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.await();
    }
}
