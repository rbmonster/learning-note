package com.learning.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

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
public class Demo implements Delayed {
    public static volatile int value = 0;
    public static int tmp = 0;

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

    @Override
    public long getDelay(TimeUnit unit) {
        return 100;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
