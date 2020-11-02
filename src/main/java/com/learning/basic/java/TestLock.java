package com.learning.basic.java;

import lombok.SneakyThrows;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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
    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,3,1000, TimeUnit.SECONDS,new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
        ReentrantLock lock =new ReentrantLock();
        new Thread(new Thread1(lock)).start();
        new Thread(new Thread2(lock)).start();
        new Thread(new Thread3(lock)).start();

    }


    static class Thread1 implements Runnable{
        ReentrantLock lock;
        Thread1(ReentrantLock lock) {
            this.lock = lock;
        }
        @SneakyThrows
        @Override
        public void run() {
            lock.lock();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(this.toString()+ "complete sleep" );
            lock.unlock();
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
