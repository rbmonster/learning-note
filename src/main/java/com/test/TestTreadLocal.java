package com.test;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

public class TestTreadLocal {

    @Getter
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        TestTreadLocal testTreadLocal = new TestTreadLocal();
        testTreadLocal.getThreadLocal().set("sdfasd");
        System.out.println(testTreadLocal.getThreadLocal().get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(testTreadLocal.getThreadLocal().get());

    }
}
