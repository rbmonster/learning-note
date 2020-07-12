package com.learning.concurrent.tool;//: concurrency/ExchangerDemo.java

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ExchangerProducer<T> implements Runnable {
    private Exchanger<T> exchanger;
    private T changeThing;

    ExchangerProducer(Exchanger<T> exchg, T changeThing) {
        exchanger = exchg;
        this.changeThing = changeThing;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                exchanger.exchange(changeThing);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
        }
    }
}

class ExchangerConsumer<T> implements Runnable {
    private Exchanger<T> exchanger;
    private T changeThing;
    ExchangerConsumer(Exchanger<T> ex, T changeThing) {
        exchanger = ex;
        this.changeThing = changeThing;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
              changeThing = exchanger.exchange(changeThing);
            }
        } catch (InterruptedException e) {
            // OK to terminate this way.
        }
        System.out.println("Final value: " + changeThing);
    }
}

public class ExchangerDemo {
    static int delay = 5; // Seconds

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<String> xc = new Exchanger<>();
        exec.execute(new ExchangerProducer<>(xc, "producer generate a egg"));
        exec.execute(new ExchangerConsumer<>(xc, "consumer generate a chicken"));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
} /* Output: (Sample)
Final value: Fat id: 29999
*///:~
