package com.learning.basic.java.basictest;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.out;

public class PackageTypeTest {
    @Test
    public void test1() {
        Set<Integer> aSet = new HashSet<Integer>();
        for(int i = 200; i < 3000; i++){
            aSet.add(i);
        }
        for(int i = 200; i < 3000; i++){
            aSet.add(i);
        }
        out.println(aSet.size());
    }
}
