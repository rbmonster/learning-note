package com.learning.algorithm.basic.dynamic;

import com.learning.concurrent.performance.collection.other.Pair;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * 零钱问题2
 * </pre>
 *
 * @version v1.0
 * @ClassName: CoinChange2
 * @Author: sanwu
 * @Date: 2020/8/29 21:33
 */
public class CoinChange2 {

    /**
     * 遍历的顺序需先用单个硬币把所有钱的数量遍历了，
     * 反过来的话 会叠加重复结
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int m = amount+1, n = coins.length;
        // int[][] dp = new int[m][n];
        // Arrays.fill(dp[0], 1);
        int[] dp = new int [m];
        dp[0]=1;
        for(int j = 0;j< n;j++) {
            for(int i =1;i<m;i++) {
                if(i - coins[j]>=0) {
                    dp[i] = dp[i] + dp[i-coins[j]];
                }
            }
        }
        return dp[m-1];
    }

    /**
     * 状态 ：钱的数量 可使用的零钱数
     * 选择解： 本轮硬币是否使用
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        int m = amount+1, n = coins.length+1;
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for(int i =1;i<m;i++) {
            for(int j = 1;j< n;j++) {
                if( i- coins[j-1]>=0) {
                    dp[i][j] = dp[i][j-1] + dp[i-coins[j-1]][j];
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
