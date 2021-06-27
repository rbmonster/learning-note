package com.learning.concurrent;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

public class StringSynchronized {


    public static void main(String[] args) throws InterruptedException {
        Interner<String> pool = Interners.newWeakInterner();

        for (int i = 1; i < 10; i++) {
            TestString billno123 = new TestString("billNo:123123123", i, pool);
            Thread thread = new Thread(billno123);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("finish");
    }
}


@Slf4j
@AllArgsConstructor
@Data
class TestString implements Runnable{
    private final String lock;
    private int workingNo;

    private Interner<String> pool;


    @Override
    public void run() {
//        synchronized (lock) {
//        synchronized (lock.intern()) {
        synchronized (pool.intern(lock)) {
            log.info(lock + " ==>" +workingNo);
        }
    }
}