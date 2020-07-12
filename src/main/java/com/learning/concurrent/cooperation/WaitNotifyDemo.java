package com.learning.concurrent.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * @Description:
 *  非同步方法调用notify 和 wait 方法
 *  重现IllegalMonitorStateException
 * </pre>
 *
 * @version v1.0
 * @ClassName: WaitNotifyDemo
 * @Author: sanwu
 * @Date: 2020/7/11 14:02
 */
public class WaitNotifyDemo implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("begin~");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaitNotifyDemo());
    }

}
