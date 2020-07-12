package com.learning.concurrent.shutDown;//: concurrency/Interrupting.java
// Interrupting a blocked thread.

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 使用 Executors的submit 获取线程上下文再调用关闭
 */
class SleepBlocked implements Runnable {
  public void run() {
    try {
      TimeUnit.SECONDS.sleep(100);
    } catch(InterruptedException e) {
      System.out.println("InterruptedException");
    }
    System.out.println("Exiting SleepBlocked.run()");
  }
}

/**
 * IO读取期间，即使调用线程的中断也无法停止
 */
class IOBlocked implements Runnable {
  private InputStream in;
  public IOBlocked(InputStream is) { in = is; }
  public void run() {
    try {
      System.out.println("Waiting for read():");
      in.read();
    } catch(IOException e) {
      if(Thread.currentThread().isInterrupted()) {
        System.out.println("Interrupted from blocked I/O");
      } else {
        throw new RuntimeException(e);
      }
    }
    System.out.println("Exiting IOBlocked.run()");
  }
}

/**
 * 设计一个用于不释放锁的程序再调用其的中断方法
 */
class SynchronizedBlocked implements Runnable {
  public synchronized void f() {
    while(true) // Never releases lock
      Thread.yield();
  }
  public SynchronizedBlocked() {
    new Thread(() -> {
      f(); // Lock acquired by this thread
    }).start();
  }
  public void run() {
    System.out.println("Trying to call f()");
    f();
    System.out.println("Exiting SynchronizedBlocked.run()");
  }
}

public class Interrupting {
  private static ExecutorService exec = Executors.newCachedThreadPool();

  static void test(Runnable r) throws InterruptedException{
    Future<?> f = exec.submit(r);
    TimeUnit.MILLISECONDS.sleep(100);
    System.out.println("Interrupting " + r.getClass().getName());
    f.cancel(true); // Interrupts if running
    System.out.println("Interrupt sent to " + r.getClass().getName());
  }

  public static void main(String[] args) throws Exception {
    test(new SleepBlocked());
    test(new IOBlocked(System.in));
    test(new SynchronizedBlocked());
    TimeUnit.SECONDS.sleep(3);
    System.out.println("Aborting with System.exit(0)");
    System.exit(0); // ... since last 2 interrupts failed
  }
} /* Output: (95% match)
Interrupting SleepBlocked
InterruptedException
Exiting SleepBlocked.run()
Interrupt sent to SleepBlocked
Waiting for read():
Interrupting IOBlocked
Interrupt sent to IOBlocked
Trying to call f()
Interrupting SynchronizedBlocked
Interrupt sent to SynchronizedBlocked
Aborting with System.exit(0)
*///:~
