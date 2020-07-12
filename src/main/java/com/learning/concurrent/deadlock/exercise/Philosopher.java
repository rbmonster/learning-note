package com.learning.concurrent.deadlock.exercise;//: concurrency/Philosopher.java
// A dining philosopher

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {
  private Chopstick left;
  private Chopstick right;
  private final int id;
  private final int ponderFactor;
  private Random rand = new Random(47);
  private void pause() throws InterruptedException {
    if(ponderFactor == 0) return;
    TimeUnit.MILLISECONDS.sleep(
      rand.nextInt(ponderFactor * 250));
  }
  public Philosopher(Chopstick left, Chopstick right,
                     int ident, int ponder) {
    this.left = left;
    this.right = right;
    id = ident;
    ponderFactor = ponder;
  }
  public void run() {
    try {
      while(!Thread.interrupted()) {
        System.out.println(this + " " + "thinking");
        pause();
        // Philosopher becomes hungry
        System.out.println(this + " " + "grabbing right");
        right.take();
        System.out.println(this + " " + "grabbing left");
        left.take();
        System.out.println(this + " " + "eating");
        pause();
        right.drop();
        left.drop();
      }
    } catch(InterruptedException e) {
      System.out.println(this + " " + "exiting via interrupt");
    }
  }
  public String toString() { return "Philosopher " + id; }
} ///:~
