package com.learning.basic.java.consumerproducer.correctorder;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionOrder {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition1 = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();
    private static final Condition condition3 = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(createRunnable(condition1, condition2, "A"));
        Thread thread2 = new Thread(createRunnable(condition2, condition3, "B"));
        Thread thread3 = new Thread(createRunnable(condition3, condition1, "C"));

        thread1.start();
        thread2.start();
        thread3.start();

        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        condition1.signal();
        lock.unlock();
        TimeUnit.SECONDS.sleep(3);
    }


    private static Runnable createRunnable(Condition waitCondition, Condition signalCondition, String msg) {
        return () -> {
            try {
                lock.lock();
                while (!Thread.currentThread().isInterrupted()) {
                    waitCondition.await();
                    log.info(msg);
                    signalCondition.signal();
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                log.error("error:", e);
            } finally {
                lock.unlock();
            }
        };
    }
}
