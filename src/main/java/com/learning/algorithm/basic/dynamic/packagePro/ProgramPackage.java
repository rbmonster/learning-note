package com.learning.algorithm.basic.dynamic.packagePro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 背包问题求方案
 * </pre>
 *
 * @version v1.0
 * @ClassName: ProgramPackage
 * @Author: sanwu
 * @Date: 2020/9/12 17:18
 */
public class ProgramPackage {

    public static void main(String[] args) {
        ProgramPackage programPackage = new ProgramPackage();
        int[] v = {1, 2, 3, 4};
        int[] val = {2, 4, 4, 6};
        System.out.println(programPackage.getMaxValueOfPackage2(4, 5, v, val));
    }

    /**
     * 背包问题求方案数
     * ！！！此处的dp表示体积恰好是i的情况下，最大价值为多少，通过初始化dp[0] =0,进而表示所有的状态都是从0转移过来的。
     * 因为取得最大价值时使用物品的方案可能不同，即物品组成的体积不同，
     * 因此在获取到最大价值之后需对方案数进行遍历，累加。
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public int getMaxValueOfPackage(int n, int m, int[] v, int[] val) {
        int[]dp = new int[m+1];
        // 初始化最大价值状态，表示体积恰好是i的情况下，最大价值是多少。
        Arrays.fill(dp, -Integer.MIN_VALUE);
        dp[0] = 0;
        // 用于记录方案数
        int[] g = new int[m+1];
        g[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= v[i]; j--) {
                int putVal = dp[j-v[i]] + val[i];
                int oriVal = dp[j];
                // 判断是否获取到最大价值 各个情况的处理方式
                if(putVal> oriVal) {
                    g[j] = g[j-v[i]];
                    dp[j] = putVal;
                } else if( putVal< oriVal) {
                    // no change
                } else if(putVal == oriVal){
                    g[j] +=g[j-v[i]];
                }
            }
        }
        int max = Arrays.stream(dp).max().getAsInt();
        int res = 0;
        for (int i = 0; i <=m; i++) {
            if(dp[i] == max) {
                res += g[i];
            }
        }

        return res;
    }


    /**
     * 获取最大价值的方案数，结果输出最小序列的方案。即物品的编号最小的一组方案。
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public  List<Integer> getMaxValueOfPackage2(int n, int m, int[] v, int[] val) {
        int[][]dp = new int[n+2][m+1];

        // 此处倒着遍历，即使用第n个物品表示第一个进行遍历，那么第1行表示使用1~n的最大价值
        for (int i = n; i >=1; i--) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = dp[i+1][j];
                if(j >= v[i-1]) {
                    dp[i][j] = Math.max(dp[i][j] ,dp[i+1][j - v[i-1]] + val[i-1]);
                }
            }
        }
        // 因为为倒着遍历，（1，m）表示的为最大价值。从1行出发，寻找编号最小的路径。
        int vol = m;
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            // 如果选入该物品与不选该物品价值一致 说明该物品是需要被选中的，此处使用贪心，有就拿，保证了编号最小。
            if(vol - v[i-1]>=0 && dp[i][m] == dp[i+1][vol - v[i-1]]+ val[i-1]){
                res.add(i);
                //选入该物品 更新体积状态
                vol -= v[i-1];
            }
        }
        return res;
    }

}
