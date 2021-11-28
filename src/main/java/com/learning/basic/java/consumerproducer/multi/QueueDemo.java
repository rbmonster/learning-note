package com.learning.basic.java.consumerproducer.multi;

import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
//        String poll = blockingQueue.poll(1,TimeUnit.SECONDS);
//        System.out.println(poll);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Producer(lock, producerCondition, blockingQueue));
        executorService.execute(new Producer(lock, producerCondition, blockingQueue));
        executorService.execute(new Consumer(lock, producerCondition, consumerCondition, blockingQueue));
        executorService.execute(new Consumer(lock, producerCondition, consumerCondition, blockingQueue));

    }
}


class Consumer implements Runnable {
    ReentrantLock lock;
    Condition producerCondition;
    Condition consumerCondition;
    LinkedBlockingQueue<String> blockingQueue;

    public Consumer(ReentrantLock lock, Condition producerCondition, Condition consumerCondition, LinkedBlockingQueue<String> blockingQueue) {
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String poll = blockingQueue.poll(1, TimeUnit.SECONDS);
                if (StringUtils.isEmpty(poll)){
                    try {
                        lock.lock();
                        producerCondition.signal();
//                        consumerCondition.await();
                    } finally {
                        lock.unlock();
                    }
                }
                System.out.println("consumer do consume, key:" + poll);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Producer implements Runnable {

    Condition producerCondition;
    LinkedBlockingQueue<String> blockingQueue;
    ReentrantLock lock;

    public Producer(ReentrantLock lock, Condition producerCondition, LinkedBlockingQueue<String> blockingQueue) {
        this.producerCondition = producerCondition;
        this.blockingQueue = blockingQueue;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                String s = UUID.randomUUID().toString();
                blockingQueue.put(s);
                System.out.println("producer do produce, key:" + s);
                try {
                    lock.lock();
                    producerCondition.await();
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}