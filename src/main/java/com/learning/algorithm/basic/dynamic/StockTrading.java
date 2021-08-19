package com.learning.algorithm.basic.dynamic;

/**
 * <pre>
 * @Description:
 * k次股票买卖
 * </pre>
 *
 * @version v1.0
 * @ClassName: StockTrading
 * @Author: sanwu
 * @Date: 2020/9/5 21:54
 */
public class StockTrading {

    public static void main(String[] args) {
//        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        int[] prices = new int[]{1,2,3,4,5};
        StockTrading st = new StockTrading();
        System.out.println(st.maxProfit(2, prices));
    }


    /**
     * 1. 定义dp及下标
     * dp[i][j][k]含义为第i天所能获取的最大利润
     * 下标i：天数
     * 下标j：第j次交易
     * 下标k：0买入、1卖出
     *
     * 2. 初始化，由于dp代表的是第i天所能获取的最大利润，第0天卖出均要初始化成-price[0]
     *
     * 3. 状态转移：
     *   dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
     *   dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[prices.length][k+1][2];

        // 若交易数超过天数一半，转化为不限交易次数问题
        if(k> len/2)  return maxProfitNoLimit(prices);
        for(int i =1;i<=k; i++ ){
            dp[0][i][1] = -prices[0];
        }
        for(int i = 1;i<prices.length;i++) {
            for(int j = 1; j<=k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }

        return dp[len-1][k][0];
    }

    public int maxProfitNoLimit(int[] prices) {
        int n = prices.length;
        int buy = -prices[0];
        int empty = 0;
        for(int i =1;i<n;i++) {
            int curEmpty = Math.max(empty, buy+prices[i]);
            int curBuy = Math.max(buy, empty-prices[i]);
            empty = curEmpty;
            buy = curBuy;
        }
        return empty;
    }
}
