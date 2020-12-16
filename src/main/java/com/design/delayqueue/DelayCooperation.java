package com.design.delayqueue;

import org.springframework.util.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * @Description:
 * 生产者与消费者
 * </pre>
 *
 * @version v1.0
 * @ClassName: DelayCooperation
 * @Author: sanwu
 * @Date: 2020/12/15 19:34
 */
public class DelayCooperation {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayMealTask> delayMealTasks = new DelayQueue<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayProducer delayProducer = new DelayProducer(delayMealTasks);
        Future<?> producer = executorService.submit(delayProducer);
        Future<?> consumer = executorService.submit(new DelayConsumer(delayMealTasks));
        TimeUnit.SECONDS.sleep(10);
        System.out.println("stop the task!");
        producer.cancel(true);
    }
}

class DelayProducer implements Runnable {
    private DelayQueue<DelayMealTask> delayMealTasks;

    public DelayProducer(DelayQueue<DelayMealTask> queue) {
        this.delayMealTasks = queue;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            DelayMealTask task = new DelayMealTask(System.nanoTime() + ThreadLocalRandom.current().nextLong(100000000L, 300000000L));
            delayMealTasks.add(task);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("producer stop!");
                Thread.currentThread().interrupt();
            }
        }
    }
}

class  DelayConsumer implements Runnable{

    private DelayQueue<DelayMealTask> delayMealTasks;

    private StopWatch stopWatch = new StopWatch();

    private AtomicInteger retry = new AtomicInteger(0);

    public DelayConsumer(DelayQueue<DelayMealTask> queue) {
        this.delayMealTasks = queue;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                stopWatch.start();
                DelayMealTask task = delayMealTasks.take();
                stopWatch.stop();
                System.out.println("consumer get task. task Id is:" + task.getTaskId());
                System.out.println("consumer get task cost time:" + stopWatch.getTotalTimeMillis() + "ms");
                TimeUnit.SECONDS.sleep(1);
                while (delayMealTasks.isEmpty()) {
                    if(retry.incrementAndGet() == 5) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    System.out.println(" consumer retry to get task, time: " + retry.get());
                }
                retry.compareAndSet(retry.get(),0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
