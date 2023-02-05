package com.learning.algorithm;

public class Algorithm4 {

    private double forMaxElement(double[] num) {
        double max = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] > max) {
                max = num[i];
            }
        }
        return max;
    }

    public static boolean isPrime(int N) {
        if (N < 2) {
            return false;
        }
        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算平方根
     * 牛顿迭代法
     *
     * @param c
     * @return
     */
    public static double sqrt(double c) {
        if (c < 0) {
            return Double.NaN;
        }
        double err = 1e-15;
        double t = c;

        while (Math.abs(t - c / t) > err * t) {
            t = (c / t + t) / 2.0;
        }
        return t;
    }

    /**
     * 计算调和级数
     *
     * @param N
     * @return
     */
    public static double H(int N) {
        double sum = 0.0;
        for (int i = 0; i <= N; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    public static void main(String[] args) {
        double sum = 0.0;
        int cnt = 0;
    }
}
