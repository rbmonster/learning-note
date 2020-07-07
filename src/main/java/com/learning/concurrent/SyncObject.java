package com.learning.concurrent;//: concurrency/SyncObject.java
// Synchronizing on another object.

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 两种同步方式的效率对比
 */
class DualSynch {
  private Object syncObject = new Object();
  public synchronized void f() throws InterruptedException {
    for(int i = 0; i < 5; i++) {
      System.out.println("f()");
      TimeUnit.SECONDS.sleep(1);
      Thread.yield();
    }
  }
  public void g() throws InterruptedException {
    synchronized(syncObject) {
      for(int i = 0; i < 5; i++) {
        System.out.println("g()");
        TimeUnit.SECONDS.sleep(1);
        Thread.yield();
      }
    }
  }
}

public class SyncObject {
  public static void main(String[] args) throws InterruptedException {
    final DualSynch ds = new DualSynch();
    new Thread() {
      @SneakyThrows
      public void run() {
        ds.f();
      }
    }.start();
    ds.g();
  }
} /* Output: (Sample)
g()
f()
g()
f()
g()
f()
g()
f()
g()
f()
*///:~
