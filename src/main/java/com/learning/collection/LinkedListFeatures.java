package com.learning.collection;//: holding/LinkedListFeatures.java

import java.util.Arrays;
import java.util.LinkedList;

/**
 * linkedList的一些特点
 */
public class LinkedListFeatures {
  public static void main(String[] args) {
    Integer[] tmp = new Integer[]{1,2,1,3,5,3};
    LinkedList<Integer> Integers =
      new LinkedList<Integer>(Arrays.asList(tmp));
    System.out.println(Integers);
    // Identical:
    System.out.println("Integers.getFirst(): " + Integers.getFirst());
    System.out.println("Integers.element(): " + Integers.element());
    // Only differs in empty-list behavior:
    System.out.println("Integers.peek(): " + Integers.peek());
    // Identical; remove and return the first element:
    System.out.println("Integers.remove(): " + Integers.remove());
    System.out.println("Integers.removeFirst(): " + Integers.removeFirst());
    // Only differs in empty-list behavior:
    System.out.println("Integers.poll(): " + Integers.poll());
    System.out.println(Integers);
    Integers.addFirst(1233);
    System.out.println("After addFirst(): " + Integers);
    Integers.offer(123);
    System.out.println("After offer(): " + Integers);
    Integers.add(123);
    System.out.println("After add(): " + Integers);
    Integers.addLast(333);
    System.out.println("After addLast(): " + Integers);
    System.out.println("Integers.removeLast(): " + Integers.removeLast());
  }
} /* Output:
[Rat, Manx, Cymric, Mutt, Pug]
Integers.getFirst(): Rat
Integers.element(): Rat
Integers.peek(): Rat
Integers.remove(): Rat
Integers.removeFirst(): Manx
Integers.poll(): Cymric
[Mutt, Pug]
After addFirst(): [Rat, Mutt, Pug]
After offer(): [Rat, Mutt, Pug, Cymric]
After add(): [Rat, Mutt, Pug, Cymric, Pug]
After addLast(): [Rat, Mutt, Pug, Cymric, Pug, Hamster]
Integers.removeLast(): Hamster
*///:~
