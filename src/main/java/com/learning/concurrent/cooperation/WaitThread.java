package com.learning.concurrent.cooperation;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: WaitThread
 * @Author: sanwu
 * @Date: 2020/7/11 14:22
 */
public class WaitThread implements Runnable {
    @Override
    public void run() {
        try {
            synchronized (this) {
                wait();
            }
            System.out.println("be notify!! here I come~");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
