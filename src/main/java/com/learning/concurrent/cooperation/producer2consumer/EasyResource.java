package com.learning.concurrent.cooperation.producer2consumer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: EasyResource
 * @Author: sanwu
 * @Date: 2020/10/17 10:20
 */
public class EasyResource {
    volatile boolean flag = false;
    int nums = 100;
    public static volatile boolean outFlag = false;


    public synchronized void create ()  {
        if (outFlag) {
            notifyAll();
            Thread.currentThread().interrupt();
            return;
        }
        while(flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(nums<0) {
            outFlag = true;
            flag = true;
            notifyAll();
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " out of resource");
            return;
        }else {
            nums--;
            flag = true;
            System.out.println(Thread.currentThread().getName() + " producer create resource");
        }
        notifyAll();
    }


    public synchronized  void destroy() {
        if (outFlag) {
            notifyAll();
            Thread.currentThread().interrupt();
            return;
        }
        while(!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " consumer destroy resource");
        flag = false;
        notifyAll();
    }

    public static void main(String[] args) {
        EasyResource resource =new EasyResource();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Thread thread1 = new Thread(()-> {while (!Thread.currentThread().isInterrupted()) resource.create();});
        Thread thread2 = new Thread(()-> {while (!Thread.currentThread().isInterrupted()) resource.destroy();});
        executorService.execute(thread1);
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread2);
        executorService.shutdown();
    }


}
