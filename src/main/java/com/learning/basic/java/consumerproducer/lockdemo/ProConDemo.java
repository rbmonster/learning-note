package com.learning.basic.java.consumerproducer.lockdemo;

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
 * @ClassName: ProConDemo
 * @Author: sanwu
 * @Date: 2021/1/3 15:27
 */
public class ProConDemo {

    public static boolean flag=  true;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Object obj = new Object();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        threadPoolExecutor.execute(new Producer(lock, condition));
        threadPoolExecutor.execute(new Producer(lock, condition));
        threadPoolExecutor.execute(new Consumer(lock, condition));
        threadPoolExecutor.execute(new Consumer(lock, condition));
        TimeUnit.SECONDS.sleep(3);
    }
}


class Consumer implements Runnable {

    private ReentrantLock lock;

    private Condition condition;

    public Consumer(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                try {
                    lock.lock();
                    while (!ProConDemo.flag){
                        condition.await();
                    }
                    System.out.println( Thread.currentThread()+ " consumer shout !!!!");
                    ProConDemo.flag = false;
                    condition.signalAll();
                }
                finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private ReentrantLock lock;

    private Condition condition;

    public Producer(ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {


        try {
            while (!Thread.interrupted()) {
                try {
                    lock.lock();
                    while (ProConDemo.flag){
                        condition.await();
                    }
                    System.out.println( Thread.currentThread()+ " producer shout~~~~");
                    ProConDemo.flag = true;
                    condition.signalAll();
                }
                finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
