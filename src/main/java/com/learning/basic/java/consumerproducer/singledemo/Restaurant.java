package com.learning.basic.java.consumerproducer.singledemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: Restaurant
 * @Author: sanwu
 * @Date: 2021/1/2 18:09
 */
public class Restaurant {

    public static void main(String[] args) throws InterruptedException {
        Meal meal = new Meal(false);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Producer producer = new Producer(meal);
        Consumer consumer = new Consumer(meal);
        executorService.execute(consumer);
        executorService.execute(producer);
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdownNow();
    }
}
