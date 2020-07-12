package com.learning.concurrent.cooperation;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: WaitTestThead
 * @Author: sanwu
 * @Date: 2020/7/11 14:31
 */
public class TestWaitThead implements Runnable{
    public static volatile Boolean flag = false;

    TestTimeWaitThread testTimeWaitThread ;

    public TestWaitThead(TestTimeWaitThread testTimeWaitThread) {
        this.testTimeWaitThread = testTimeWaitThread;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
            flag = true;
            synchronized (testTimeWaitThread) {
                testTimeWaitThread.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
