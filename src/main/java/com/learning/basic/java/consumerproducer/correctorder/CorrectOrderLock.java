package com.learning.basic.java.consumerproducer.correctorder;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: CorrectOrderLock
 * @Author: sanwu
 * @Date: 2021/1/3 16:10
 */
@Slf4j
public class CorrectOrderLock {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        new Thread(createRunnable(lock, "A")).start();
        new Thread(createRunnable(lock, "B")).start();
        new Thread(createRunnable(lock, "C")).start();
        TimeUnit.SECONDS.sleep(3);
    }

    private static Runnable createRunnable(ReentrantLock lock, String msg) {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    lock.lock();
                    log.info(msg);
                } finally {
                    lock.unlock();
                }
            }
        };
    }
}

