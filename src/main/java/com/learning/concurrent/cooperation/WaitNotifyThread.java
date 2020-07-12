package com.learning.concurrent.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: NotifyThread
 * @Author: sanwu
 * @Date: 2020/7/11 14:23
 */
public class WaitNotifyThread implements Runnable {
    WaitThread waitThread;

    public WaitNotifyThread(WaitThread waitThread) {
        this.waitThread = waitThread;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1l);
            System.out.println("notify thread wait the other thread~");
            synchronized (waitThread) {
                waitThread.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitThread waitThread = new WaitThread();
        WaitNotifyThread waitNotifyThread = new WaitNotifyThread(waitThread);
        executorService.execute(waitThread);
        executorService.execute(waitNotifyThread);
        TimeUnit.SECONDS.sleep(3L);
        executorService.shutdown();
    }
}
