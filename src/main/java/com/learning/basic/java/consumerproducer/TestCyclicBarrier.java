package com.learning.basic.java.consumerproducer;

import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestCyclicBarrier {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> log.info("barrier on going"));

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                log.info("{} cyclicBarrier await, parties:{}, waitingNumber:{}", name, cyclicBarrier.getParties(), cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            log.info("{} : start to work", name);
        };
        workProcess(runnable);
        TimeUnit.SECONDS.sleep(2);
        log.info("=============  next loop ===========");
        cyclicBarrier.reset();
        workProcess(runnable);

    }

    private static void workProcess(Runnable runnable) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            new Thread(runnable).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
