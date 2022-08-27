package com.learning.algorithm.basic.dynamic.packagePro;

/**
 * <pre>
 * @Description:
 * 完全背包问题
 * </pre>
 *
 * @version v1.0
 * @ClassName: FullPackage
 * @Author: sanwu
 * @Date: 2020/9/12 14:42
 */
public class FullPackage {

    public static void main(String[] args) {
        FullPackage fullPackage = new FullPackage();
        int[] v = {1, 2, 3, 4};
        int[] val = {2, 4, 4, 5};
        System.out.println(fullPackage.getMaxPackageValue2(4, 5, v, val));

    }

    /**
     * 二维数组完全背包
     * 按硬币和体积的顺序二维遍历，每次物品只有放与不放两种情况，第i个物品不放则总价值与i-1个物品一致
     * 第i个物品放进背包，则价值为 f[i][j-v[i]]+ val[i] 注意这边同样为第i行，因为物品可以放多次。
     * f[i][j] = max( f[i-1][j] + f[i][j-v[i]+ val[i])
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public int getMaxPackageValue(int n,int m ,int[]v, int [] val) {
        int [][]dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 默认第i个物品不放进背包
                dp[i][j] = dp[i-1][j];
                // 第i个物品放进背包，前提条件体积j大于物品i的体积，取最大价值
                if (j>=v[i-1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-v[i-1]] +val[i-1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 一维压缩 完全背包
     * 此处不需要从大到小遍历，因为物品可以多次放入背包，因此取得状态为第i个物品遍历体积的前序状态。
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public int getMaxPackageValue2(int n,int m ,int[]v, int [] val) {
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = v[i-1]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j-v[i-1]] + val [i-1]);
            }
        }
        return dp[m];
    }
}
