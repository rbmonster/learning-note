package com.learning.concurrent;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo
 * @Author: sanwu
 * @Date: 2020/7/5 21:31
 */
public class Demo implements Runnable {
    public static volatile int value = 0;
    public static int tmp = 0;

    @Override
    public synchronized void run() {
        value++;
        testMethod();
    }

    public static void read(){
        System.out.println(value);
    }

    private void testMethod() {
        tmp++;
    }

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 1000; i++) {
//            executorService.execute(new Demo());
//        }
//        System.out.println(Demo.value);
//        System.out.println(Demo.tmp);
        Demo demo = new Demo();
        Demo.value++;
        

    }
}
