package com.learning.algorithm.basic.dynamic.packagePro;

import org.springframework.cglib.proxy.Mixin;

/**
 * <pre>
 * @Description:
 * 混合背包问题
 * 即放入的商品有要么只能放一次，要么可以放无数次，要么有数量限制
 * </pre>
 *
 * @version v1.0
 * @ClassName: MixingPackage
 * @Author: sanwu
 * @Date: 2020/9/12 15:54
 */
public class MixingPackage {

    public static void main(String[] args) {
        int[] v = {1, 2, 3, 4};
        int[] val = {2, 4, 4, 5};
        int[] type = {-1,1,0,2};
        MixingPackage mixingPackage = new MixingPackage();
        System.out.println(mixingPackage.getMaxValueOfPackageSuper(4,5,v,val,type));
    }

    /**
     * 区分情况处理即可
     * @param n
     * @param m
     * @param v
     * @param val
     * @param type
     * @return
     */
    public int getMaxValueOfPackageSuper(int n, int m, int[] v, int[] val, int[] type) {
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            if (type[i] == -1) {
                for (int j = m; j >= v[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + val[i]);
                }
            }else if(type[i] == 0) {
                for (int j = v[i]; j <=m; j++) {
                    dp[j] = Math.max(dp[j], dp[j-v[i]] + val[i]);
                }
            } else {
                int num = type[i];
                for (int j = m; j >= v[i]; j--) {
                    for (int k = 1; k <= num && k*v[i]<= j; k++) {
                        dp[j] = Math.max(dp[j], dp[j - v[i]*k] + k*val[i]);
                    }
                }
            }
        }
        return dp[m];
    }
}
