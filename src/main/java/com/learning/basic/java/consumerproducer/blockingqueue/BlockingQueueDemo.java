package com.learning.basic.java.consumerproducer.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: BlockedQueue
 * @Author: sanwu
 * @Date: 2021/1/3 15:53
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        threadPoolExecutor.execute(new Producer(queue));
        threadPoolExecutor.execute(new Consumer(queue));
    }
}

class Consumer implements Runnable {

    private BlockingQueue<Object> queue;

    public Consumer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                Object poll;
                poll = queue.take();
                System.out.println(Thread.currentThread() + " consumer consume obj!~~~~" + poll.hashCode());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    BlockingQueue<Object> queue;

    public Producer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {
            try {
                Object o = new Object();
                queue.put(o);
                System.out.println(Thread.currentThread() + " producer produce obj!~~~~" + o.hashCode());
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}