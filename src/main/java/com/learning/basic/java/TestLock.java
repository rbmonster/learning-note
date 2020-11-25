package com.learning.basic.java;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
//
//        ReentrantReadWriteLock lock =new ReentrantReadWriteLock();
//        new Thread(new Thread1(lock)).start();
//        new Thread(new Thread2(lock)).start();
        new TestLock().test();
    }

    synchronized void test() throws InterruptedException{
        while(true) {
            System.out.println(1111111);
        }
    }

    static class Thread1 implements Runnable{
        ReentrantReadWriteLock lock;
        Thread1(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("i am interrupted");
            lock.readLock().lock();
            TimeUnit.SECONDS.sleep(5);
            System.out.println(this.toString()+ "complete sleep" );
            lock.readLock().unlock();
            System.out.println("out of work");
        }
    }
    static class Thread2 implements Runnable{
        ReentrantReadWriteLock lock;
        Thread2(ReentrantReadWriteLock lock) {
            this.lock = lock;
        }
        @SneakyThrows
        @Override
        public void run() {
            lock.writeLock().lock();
            TimeUnit.SECONDS.sleep(5);
            System.out.println(this.toString()+ "complete sleep" );
            lock.writeLock().unlock();

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

    static class Person{
        int age;
    }
}
