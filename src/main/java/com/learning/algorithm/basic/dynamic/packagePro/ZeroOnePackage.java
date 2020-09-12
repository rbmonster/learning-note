package com.learning.algorithm.basic.dynamic.packagePro;

/**
 * <pre>
 * @Description:
 * 01 背包问题
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: ZeroOnePackage
 * @Author: sanwu
 * @Date: 2020/9/12 13:52
 */
public class ZeroOnePackage {

    public static void main(String[] args) {
        ZeroOnePackage zeroOnePackage = new ZeroOnePackage();
        int[] v = {1, 2, 3, 4};
        int[] val = {2, 4, 4, 5};
        System.out.println(zeroOnePackage.getMaxValueOfPackage2(4, 5, v, val));
    }

    /**
     * 二维矩阵 01 背包
     * n个物品  m的体积的背包
     * 先遍历物品再遍历背包，测试每个物品放进背吧与不放进背包的最大价值
     *
     * f[n][m] = max( f[n-1][m], f[n-1][m-v[i]] + val[i]
     *
     * @param m
     * @param n
     * @param v
     * @param val
     * @return
     */
    public int getMaxValueOfPackage(int n, int m, int[] v, int[] val) {
        // 这边定义为n+1 与 m+1 个二维矩阵，表示i个物品的j体积的最大价值。下面注意 数组-1问题
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 默认第i个物品不放进背包
                dp[i][j] = dp[i - 1][j];
                // 第i个物品放进背包， 前提条件体积j大于物品i的体积，取最大价值
                if (j >= v[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 01 背包一维数组的解法
     * 注意需要逆序处理，因为原先的二维压缩成一维，在开始第i个物品的遍历时，dp存储的为i-1个物品的最大价值。
     * 若使用顺序遍历，则i-1个物品的最大价值会被覆盖，每次放进物品取 dp[j-v[i-1]] 的数据是往前取，因此需要逆序遍历
     * f[j] = max(f[j], f[j-v[i]])
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public int getMaxValueOfPackage2(int n, int m, int[] v, int[] val) {
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            //优化使用j>=v[i-1], 仅需要查看 v[i-1] ~ m 这个体积区间的最大价值是否需要更新
            for (int j = m; j >=v[i-1]; j--) {
                dp[j] = Math.max(dp[j], dp[j-v[i-1]] + val[i-1]);
            }
        }
        return dp[m];
    }


}
