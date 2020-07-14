package com.learning.collection;//: holding/ListIteration.java

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * ListIterator 的实现
 */
public class ListIteration {
  public static void main(String[] args) {
    Integer[] tmp = new Integer[]{1,2,1,3,5,3};
    List<Integer> Integers = Arrays.asList(tmp);
    ListIterator<Integer> it = Integers.listIterator();
    while(it.hasNext())
      System.out.println(it.next() + ", " + it.nextIndex() +
        ", " + it.previousIndex() + "; ");
    System.out.println();
    // Backwards:
    while(it.hasPrevious())
      System.out.print(it.previous() + " ");
    System.out.println();
    System.out.println(Integers);	
    it = Integers.listIterator(3);
    while(it.hasNext()) {
      it.next();
      it.set(123);
    }
    System.out.println(Integers);
  }
} /* Output:
Rat, 1, 0; Manx, 2, 1; Cymric, 3, 2; Mutt, 4, 3; Pug, 5, 4; Cymric, 6, 5; Pug, 7, 6; Manx, 8, 7;
7 6 5 4 3 2 1 0
[Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Manx]
[Rat, Manx, Cymric, Cymric, Rat, EgyptianMau, Hamster, EgyptianMau]
*///:~
