package com.learning.basic.java.consumerproducer.singledemo;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: Producer
 * @Author: sanwu
 * @Date: 2021/1/2 18:02
 */
public class Producer implements Runnable {

    private Meal meal;

    public Producer(Meal meal) {
        this.meal = meal;
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                meal.createByObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
