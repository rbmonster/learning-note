package com.learning.basic.java.consumerproducer.singledemo;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: Consumer
 * @Author: sanwu
 * @Date: 2021/1/2 18:01
 */
public class Consumer implements Runnable {

    private Meal meal;

    public Consumer(Meal meal) {
        this.meal = meal;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                meal.consumerByObject();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
