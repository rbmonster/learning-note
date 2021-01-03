package com.learning.basic.java.consumerproducer.correctorder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: SynchronizeObject
 * @Author: sanwu
 * @Date: 2021/1/3 14:45
 */
public class SynchronizeObject {
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        threadPoolExecutor.execute(new Thread1(obj));
        threadPoolExecutor.execute(new Thread2(obj));
        TimeUnit.SECONDS.sleep(3);
        threadPoolExecutor.shutdown();
    }


}

class Thread1 implements Runnable {

    private Object obj;

    public Thread1(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (obj) {
                    while (!SynchronizeObject.flag) {
                        obj.wait();
                    }
                    System.out.println(Thread.currentThread() + " this is thread1");
                    SynchronizeObject.flag = false;
                    obj.notify();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread2 implements Runnable {

    private Object obj;

    public Thread2(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                synchronized (obj) {
                    while (SynchronizeObject.flag) {
                        obj.wait();
                    }
                    System.out.println(Thread.currentThread() + " this is thread2~~");
                    SynchronizeObject.flag = true;
                    obj.notify();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

