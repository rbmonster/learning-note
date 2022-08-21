package com.learning.basic.java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 *
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
        Runnable runnable = () -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ ": countDown complete");
        };

        new Thread(runnable).start();
        new Thread(runnable).start();

        TimeUnit.SECONDS.sleep(1);
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.await();
    }
}
