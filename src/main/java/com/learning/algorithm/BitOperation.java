package com.learning.algorithm;


public class BitOperation {

    public static void main(String[] args) {
        toLowerCase();
        toUpperCase();
        exchangeCase();
        assertNumberMinus();
        exchangeNum();
        countBit();
        isPowerOfTwo(16);
    }


    /**
     * 统一转小写
     * ('A' | ' ') = 'a';
     * ('a' | ' ') = 'a';
     */
    private static void toLowerCase() {
        char ch1 = 'c';
        char ch2 = 'C';
        System.out.println("('c'|' ') == 'c' :" + ((ch1 | ' ') == 'c'));
        System.out.println("('C'|' ') == 'c' :" + ((ch2 | ' ') == 'c'));
    }

    /**
     * 统一转大写
     * ('b' & '_') = 'B'
     * ('B' & '_') = 'B'
     */
    private static void toUpperCase() {
        char ch1 = 'c';
        char ch2 = 'C';
        System.out.println("('c' & '_') == 'C' :" + ((ch1 & '_') == 'C'));
        System.out.println("('C' & '_') == 'C' :" + ((ch2 & '_') == 'C'));
    }

    /**
     * 大小写交换
     * ('d' ^ ' ') = 'D'
     * ('D' ^ ' ') = 'd'
     */
    private static void exchangeCase() {
        char ch1 = 'c';
        char ch2 = 'C';
        System.out.println("('c' ^ ' ') == 'C' :" + ((ch1 ^ ' ') == 'C'));
        System.out.println("('C' ^ ' ') == 'c' :" + ((ch2 ^ ' ') == 'c'));
    }

    /**
     * 判断负数
     */
    private static void assertNumberMinus() {
        int num1 = 1231;
        int num2 = -12;
        System.out.println("(1231 ^ -12) < 0 :" + ((num1 ^ num2) < 0));
    }

    /**
     * 数字交换
     */
    private static void exchangeNum() {
        int a = 12312;
        int b = 22;
        System.out.println("before num[a]:" + a + " num[b]:" + b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("after num[a]:" + a + " num[b]:" + b);
    }

    /**
     * 计算一个数二进制中1的个数
     */
    private static void countBit() {
        int res = 0;
        int num = 1002930;
        int n = num;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        System.out.println("num:" + num + ",count one number:" + res);
    }

    /**
     * 判断一个数是否为二的指数
     * @param n
     * @return
     */
    private static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}
