package com.learning.concurrent.shutDown;//: concurrency/ReentrantLockInterrupting.java
// Interrupting a task blocked with a ReentrantLock.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
  private Lock lock = new ReentrantLock();
  public BlockedMutex() {
    // Acquire it right away, to demonstrate interruption
    // of a task blocked on a ReentrantLock:
    lock.lock();
  }
  public void f() {
    try {
      System.out.println("start lock acquired in f()");
      // This will never be available to a second task
      lock.lockInterruptibly(); // Special call
      System.out.println("lock acquired in f()");
    } catch(InterruptedException e) {
      System.out.println("Interrupted from lock acquisition in f()");
    }
  }
}

class Blocked2 implements Runnable {
  BlockedMutex blocked = new BlockedMutex();
  public void run() {
    System.out.println("Waiting for f() in BlockedMutex");
    blocked.f();
    System.out.println("Broken out of blocked call");
  }
}

public class ReentrantLockInterrupting {
  public static void main(String[] args) throws Exception {
    Thread t = new Thread(new Blocked2());
    t.start();
    TimeUnit.SECONDS.sleep(10);
    System.out.println("Issuing t.interrupt()");
    t.interrupt();
  }
} /* Output:
Waiting for f() in BlockedMutex
Issuing t.interrupt()
Interrupted from lock acquisition in f()
Broken out of blocked call
*///:~
