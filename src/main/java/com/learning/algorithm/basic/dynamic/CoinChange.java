package com.learning.algorithm.basic.dynamic;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * 零钱兑换 1
 * </pre>
 *
 * @version v1.0
 * @ClassName: CoinChange
 * @Author: sanwu
 * @Date: 2020/8/29 20:04
 */
public class CoinChange {

    public static void main(String[] args) {
        CoinChange main = new CoinChange();
        int[] coins = {1,2,5};
//        main.coinChange3(coins, 11);
        main.change(5,coins);
    }

    public int coinChange(int[] coins, int amount) {
        int[] res = new int[amount + 1];
        Arrays.fill(res, amount + 1);
        res[0] = 0;
        for (int i = 1; i < res.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                res[i] = Math.min(res[i], res[i - coin] + 1);
            }
        }
        return res[amount] > amount ? -1 : res[amount];
    }

    /**
     * 针对一个重量使用 所用硬币去遍历，求出最少的次数
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int coinLen = coins.length;
        int m = amount + 1;
        int[][] dp = new int[amount + 1][coinLen + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], m);
        }
        Arrays.fill(dp[0], 0);
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 1; j < coinLen + 1; j++) {
                if (i - coins[j - 1] < 0) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - coins[j - 1]][j] + 1);
                }
            }
        }
        return dp[amount][coinLen] == m ? -1 : dp[amount][coinLen];
    }

    /**
     * 比较不好理解
     * 每次使用一个金币遍历所有重量
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        int coinLen = coins.length;
        int m = amount + 1;
        int[][] dp = new int[coinLen + 1][amount + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], m);
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < coinLen + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if(j - coins[i-1] >=0) {
                    dp[i][j]= Math.min(dp[i-1][j], dp[i][j - coins[i-1]] +1);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coinLen][amount] == m ? -1 : dp[coinLen][amount];
    }

    public int change(int amount, int[] coins) {
        int m = amount+1, n = coins.length;
        // int[][] dp = new int[m][n];
        // Arrays.fill(dp[0], 1);
        int[] dp = new int [m];
        dp[0]=1;
        for(int i =1;i<m;i++) {
            for(int j = 0;j< n;j++) {
                if(i - coins[j]>=0) {
                    dp[i] = dp[i] + dp[i-coins[j]];
                }
            }
        }
        return dp[m-1];
    }
}
