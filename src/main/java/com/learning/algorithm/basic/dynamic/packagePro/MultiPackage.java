package com.learning.algorithm.basic.dynamic.packagePro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 多重背包问题
 * 物品有数量限制，每次只能放一个
 * </pre>
 *
 * @version v1.0
 * @ClassName: MultiPackage
 * @Author: sanwu
 * @Date: 2020/9/12 15:06
 */
public class MultiPackage {

    public static void main(String[] args) {
        int[] v = {1, 2, 3, 4};
        int[] val = {2, 4, 4, 5};
        int[] nums = {3,1,3,2};
        MultiPackage multiPackage = new MultiPackage();
        System.out.println(multiPackage.getMaxValueOfPackageSuper(4,5,v,val,nums));
    }
    /**
     * 多重背包问题二进制优化
     *
     * 针对物品进行二进制优化，此处的优化表示对物品进行打包操作，比如7个物品，可以打包成1,2,4个物品包裹
     * 如物品数量为10 那么可以用二进制 1， 2， 4 表示0~7，而剩余的3打包成一个物品包裹，4个包裹就任意组合表示原来10物品情况
     * 因此原来10个物品的数量，现在优化成只要遍历4个物品包裹，减少时间复杂度
     *
     * @param n
     * @param m
     * @param v
     * @param val
     * @param nums
     * @return
     */
    public int getMaxValueOfPackageSuper(int n, int m, int[] v, int[] val, int[] nums) {
        List<Good> goods = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int s = nums[i];
            int curV = v[i];
            int curVal = val[i];
            // 此处为二进制处理
            for (int j = 1; j < s; j*=2) {
                s-=j;
                goods.add(new Good(curV*j, curVal*j));
            }
            if(s>0) {
                goods.add(new Good(curV*s, curVal*s));
            }
        }
        int[]dp = new int[m+1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = m; j >= v[i-1]; j--) {
                //针对原来的部分进行优化
//                for (int k = 1; k <=nums[i-1] && j>=k*v[i-1]; k++) {
//                    dp[j] = Math.max(dp[j], dp[j-k*v[i-1]] + k*val[i-1]);
//                }

//            }
//        }
        for (Good good : goods) {
            for (int j = m; j >= good.v; j--) {
                dp[j] = Math.max(dp[j], dp[j - good.v] + good.val);
            }
        }
        return dp[m];
    }

    /**
     * 多重背包问题，01 背包变形
     * 一维数组版本
     * @param n
     * @param m
     * @param v
     * @param val
     * @param nums
     * @return
     */
    public int getMaxValueOfPackage1(int n, int m, int[] v, int[] val, int[] nums) {
        int[]dp = new int[m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= v[i-1]; j--) {
                // 增加遍历数量，判断条件增加j>=k*v[i-1]，保证体积满足，减少判断
                for (int k = 1; k <=nums[i-1] && j>=k*v[i-1]; k++) {
                    dp[j] = Math.max(dp[j], dp[j-k*v[i-1]] + k*val[i-1]);
                }
            }
        }
        return dp[m];
    }



    /**
     * 多重背包问题 为01背包的变形版本，增加了数量的参数
     * 二维数组版本
     * 在第i个物品放与不放的判断时，增加数量的遍历
     * @param n
     * @param m
     * @param v
     * @param val
     * @param nums
     * @return
     */
    public int getMaxValueOfPackage2(int n, int m, int[] v, int[] val, int[] nums) {
        int[][]dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j];
                // 遍历数量
                for (int k = 1; k <=nums[i-1] && j>=k*v[i-1]; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-k*v[i-1]] + k*val[i-1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 多重背包三维数组版本较难理解  见备注
     * @param n
     * @param m
     * @param v
     * @param val
     * @param nums
     * @return
     */
    public int getMaxValueOfPackage3(int n, int m, int[] v, int[] val, int[] nums) {
        //取得物品中最大的数量
        int maxNum = Arrays.stream(nums).max().getAsInt();
        int[][][]dp = new int[n+1][m+1][maxNum+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <=maxNum; k++) {
                    // 如果体积满足 j>= k*v[i-1], 判断放k个物品进背包
                    if(j>=k*v[i-1]) {
                        // 与i-1个物品放进max数量的最大价值比较。
                        dp[i][j][k] = Math.max(dp[i-1][j][maxNum], dp[i-1][j-k*v[i-1]][k] + k*val[i-1]);
                    } else {
                        //若j无法放入k个物品，比较i-1个物品的放进max数量的最大价值 与  第i个物品放进k-1个物品的最大价值
                        // 这样默认[i][j][maxNum] 保证获取到前i个物品的最大价值
                        dp[i][j][k] = Math.max(dp[i-1][j][maxNum],dp[i][j][k-1]);
                    }
                }
            }
        }
        return dp[n][m][maxNum];
    }

    class Good{
        int v;
        int val;
        public Good(int v, int val) {
            this.v = v;
            this.val = val;
        }
    }
}
