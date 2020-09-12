package com.learning.algorithm.basic.dynamic.packagePro;

/**
 * <pre>
 * @Description:
 * 二维费用的背包问题
 * 即限制因素除了一个体积 还有一个重量
 * </pre>
 *
 * @version v1.0
 * @ClassName: TwoDimensionPackage
 * @Author: sanwu
 * @Date: 2020/9/12 16:21
 */
public class TwoDimensionPackage {

    public static void main(String[] args) {
        TwoDimensionPackage twoDimensionPackage = new TwoDimensionPackage();

        int[] v = {1, 2, 3, 4};
        int[] val = {3,4,5,6};
        int[] wei = {2, 4, 4, 5};
        System.out.println(twoDimensionPackage.getMaxValueOfPackageSuper(4,5,6,v,val,wei));
    }

    /**
     * 建立二维db循环
     * 在01 背包的基础上，再循环重量的限制条件
     * @param n
     * @param m
     * @param w
     * @param v
     * @param val
     * @param wei
     * @return
     */
    public int getMaxValueOfPackageSuper(int n, int m,int w, int[] v, int[] val, int[] wei) {
        int[][] dp = new int[m+1][w+1];
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= v[i]; j--) {
                for (int k = w; k >= wei[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j-v[i]][k-wei[i]] + val[i]);
                }
            }
        }
        return dp[m][w];
    }
}
