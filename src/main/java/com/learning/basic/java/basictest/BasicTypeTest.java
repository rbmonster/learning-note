package com.learning.basic.java.basictest;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;

public class BasicTypeTest {
    /**
     * 测试各种类型长度
     */
    @Test
    public void test1() {
        out.println("Integer:" + Integer.SIZE / 8); // Integer:4
        out.println("Short:" + Short.SIZE / 8); // Short:2
        out.println("Long:" + Long.SIZE / 8); // Long:8
        out.println("Byte:" + Byte.SIZE / 8); // Byte:1
        out.println("Character:" + Character.SIZE / 8); // Character:2
        out.println("Float:" + Float.SIZE / 8); // Float:4
        out.println("Double:" + Double.SIZE / 8); // Double:8
        // System.out.println("Boolean:" + Boolean.toString(false));
    }

    /**
     * 测试char类型和数字类型的转换
     */
    @Test
    public void test2() {
        char a = 'a';
        short aInt = 'a';
        out.println(a);
        out.println(aInt);
        out.println(a + 1);
    }
}
