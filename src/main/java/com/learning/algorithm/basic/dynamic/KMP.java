package com.learning.algorithm.basic.dynamic;

import java.util.Arrays;
public class KMP {

    public static void main(String[] args) {
        new KMP("ABABC");
    }

    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256]; // base case
        dp[0][pat.charAt(0)] = 1;
        // 影⼦状态 X 初始为 0
        int X = 0;
        // 当前状态 j 从 1 开始
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[X][c];
                }
            }
            // 更新影⼦状态
            X = dp[X][pat.charAt(j)];
            System.out.println(Arrays.toString(dp[j]));
        }
    }

}
