package com.learning.concurrent.cooperation;

import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * @Description:
 * 测试wait时间
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestTimeWaitThread
 * @Author: sanwu
 * @Date: 2020/7/11 14:34
 */
public class TestTimeWaitThread implements Runnable {
    @Override
    public void run() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        synchronized (this){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TestWaitThead.flag = false;
        stopWatch.stop();
        System.out.println("reset flag to flag ,it costs:" + stopWatch.getTotalTimeMillis());
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        TestTimeWaitThread testTimeWaitThread = new TestTimeWaitThread();
        executorService.execute(new TestWaitThead(testTimeWaitThread));
        executorService.execute(testTimeWaitThread);
        executorService.shutdown();
    }
}
