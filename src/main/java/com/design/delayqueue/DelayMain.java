package com.design.delayqueue;

import org.springframework.util.StopWatch;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * 延迟队列使用
 * </pre>
 *
 * @version v1.0
 * @ClassName: DelayOri
 * @Author: sanwu
 * @Date: 2020/12/15 18:56
 */
public class DelayMain {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayMealTask> queue = new DelayQueue<>();
        DelayMealTask task = new DelayMealTask(System.nanoTime() + ThreadLocalRandom.current().nextLong(100000000L, 300000000L));
        queue.add(task);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("begin to take task");
        DelayMealTask take = queue.take();
        System.out.println("get task complete id :"+take.getTaskId());
        stopWatch.stop();
        System.out.println("cost time : " +stopWatch.getTotalTimeMillis());
    }
}
