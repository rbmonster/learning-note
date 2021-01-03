package com.learning.basic.java.consumerproducer.correctorder;

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
public class CorrectOrderLock {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        threadPoolExecutor.execute(new Producer(lock));
        threadPoolExecutor.execute(new Consumer(lock));
        TimeUnit.SECONDS.sleep(3);
    }
}


class Consumer implements Runnable {

    private ReentrantLock lock;


    public Consumer(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread() + " consumer shout !!!!");
            } finally {
                lock.unlock();
            }
        }
    }
}

class Producer implements Runnable {

    private ReentrantLock lock;


    public Producer(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {


        while (!Thread.interrupted()) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread() + " producer shout~~~~");
            } finally {
                lock.unlock();
            }
        }

    }
}

