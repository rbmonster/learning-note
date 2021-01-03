package com.learning.basic.java.consumerproducer.singledemo;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: Meal
 * @Author: sanwu
 * @Date: 2021/1/2 18:02
 */
public class Meal {
    private boolean present = false;
    int count = 0;

    public Meal(boolean present) {
        this.present = present;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public synchronized void produce() throws InterruptedException {
        while (present) {
            wait();
        }
        System.out.println(Thread.currentThread() + " Producer produce meal " + count);
        count++;
        this.setPresent(true);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (!present) {
            wait();
        }
        System.out.println(Thread.currentThread() + " consumer present meal" + count);
        this.setPresent(false);
        notifyAll();

    }


    public void createByObject() throws InterruptedException {
        synchronized (this) {
            while (present) {
                wait();
            }
        }
        count++;
        System.out.println(Thread.currentThread() + " Producer produce meal " + count);
        this.setPresent(true);
        synchronized (this) {
            notifyAll();
        }
    }

    public void consumerByObject() throws InterruptedException {
        synchronized (this) {
            while (!present) {
                wait();
            }
        }
        System.out.println(Thread.currentThread() + " consumer present meal " + count);
        this.setPresent(false);
        synchronized (this) {
            notifyAll();
        }
    }
}
