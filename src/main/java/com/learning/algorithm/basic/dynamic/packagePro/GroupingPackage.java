package com.learning.algorithm.basic.dynamic.packagePro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 分组背包问题
 * </pre>
 *
 * @version v1.0
 * @ClassName: GroupingPackage
 * @Author: sanwu
 * @Date: 2020/9/12 16:44
 */
public class GroupingPackage {
    public static void main(String[] args) {
        List<List<Good>> group = new ArrayList<>();
        group.add(Arrays.asList(new Good(1,2),new Good(2,4)));
        group.add(Arrays.asList(new Good(3,4)));
        group.add(Arrays.asList(new Good(4,5)));
        GroupingPackage groupingPackage = new GroupingPackage();
        System.out.println(groupingPackage.getMaxValueOfPackageSuper(3,5, group));
    }



    /**
     * 分组背包问题  —————— 多重背包问题为该问题的特殊情况
     *  此处在重量进行选择的时候，对组内的背包数量进行遍历
     *
     *
     * 输入用例 ：
     *         3 5
     *         2
     *         1 2
     *         2 4
     *         1
     *         3 4
     *         1
     *         4 5
     * @param n
     * @param m
     * @param group
     * @return
     */
    public int getMaxValueOfPackageSuper(int n, int m, List<List<Good>> group) {
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            List<Good> goods = group.get(i);
            for (int j = m; j >=0; j--) {
                // 这里进行物品的循环，针对组内的物品进行物品的01 背包实现
                for (Good good : goods) {
                    if(j>=good.v) {
                        dp[j] = Math.max(dp[j], dp[j-good.v] + good.val);
                    }
                }
            }
        }
        return dp[m];

    }

    static class Good{
        int v;
        int val;
        public Good(int v, int val) {
            this.v = v;
            this.val = val;
        }
    }
}
