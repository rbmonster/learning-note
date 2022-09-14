package com.learning.basic.java;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestSemaphore {

    private static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        createThread1();
        createThread2();
        createThread3();
    }


    private static void createThread1() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaphore.acquire(2);
                    log.info("AAA-"+semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }
            }
        }).start();
    }

    private static void createThread2() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaphore.acquire();
                    log.info("BBB-"+ semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        }).start();
    }

    private static void createThread3() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaphore.acquire(3);
                    log.info("CCC-"+ semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(3);
                }
            }
        }).start();
    }

}
