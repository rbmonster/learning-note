package com.learning.basic.java.consumerproducer.correctorder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueOrder {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Object> queue1 = new LinkedBlockingDeque<>();
        BlockingQueue<Object> queue2 = new LinkedBlockingDeque<>();
        BlockingQueue<Object> queue3 = new LinkedBlockingDeque<>();
        new Thread(createRunnable(queue1, queue2, "A")).start();
        new Thread(createRunnable(queue2, queue3, "B")).start();
        new Thread(createRunnable(queue3, queue1, "C")).start();

        queue1.offer(new Object());
        new CountDownLatch(1).await();
    }


    private static Runnable createRunnable(BlockingQueue<Object> consumerQueue, BlockingQueue<Object> producerQueue, String msg) {
        return () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    consumerQueue.take();
                    System.out.println(msg);
                    producerQueue.offer(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
