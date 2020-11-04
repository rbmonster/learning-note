package com.learning.basic.java;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestLock
 * @Author: sanwu
 * @Date: 2020/11/3 0:21
 */
public class TestLock {

    private  static volatile boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,3,1000, TimeUnit.SECONDS,new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
//        ReentrantLock lock =new ReentrantLock();
//        Condition condition = lock.newCondition();
//        lock.lock();
//       condition.signal();
//
//       condition.signal();
        Thread thread = new Thread(new Thread1(new ReentrantLock()));
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        CyclicBarrier barrier = new CyclicBarrier(12);
        System.out.println("interrupt thread");
        while (flag) {

        }
    }


    static class Thread1 implements Runnable{
        ReentrantLock lock;
        Thread1(ReentrantLock lock) {
            this.lock = lock;
        }
        @SneakyThrows
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){

            }
            System.out.println("i am interrupted");
            Thread.interrupted();

            while(!Thread.currentThread().isInterrupted()){

            }
            flag =false;
            System.out.println("out of work");
        }
    }
    static class Thread2 implements Runnable{
        ReentrantLock lock;
        Thread2(ReentrantLock lock) {
            this.lock = lock;
        }
        @SneakyThrows
        @Override
        public void run() {
            lock.lock();
            TimeUnit.SECONDS.sleep(5);
            System.out.println(this.toString()+ "complete sleep" );
            lock.unlock();

        }
    }
    static class Thread3 implements Runnable{
        ReentrantLock lock;
        Thread3(ReentrantLock lock) {
            this.lock = lock;
        }
        @SneakyThrows
        @Override
        public void run() {
            while (!lock.tryLock()){
                System.out.println(this.toString()+ "complete sleep" );
                lock.unlock();
            }

        }
    }
}
