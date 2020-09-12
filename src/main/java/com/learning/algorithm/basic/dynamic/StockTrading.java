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
        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        StockTrading st = new StockTrading();
        System.out.println(st.maxProfit(3, prices));
    }

    /**
     * K次股票买卖
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // 若交易数超过天数一半，转化为不限交易次数问题
        if(k> n/2)  return maxProfitNoLimit(prices);
        int[][][]dp = new int[n][k+1][2];
        for(int i=0; i<n; i++){
            for(int j=0; j<=k; j++){
                if(i == 0){
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[i];
                    continue;
                }
                if(j == 0){
                    dp[i][0][0] = 0;
                    // dp[i][0][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);

            }
        }
        return dp[n-1][k][0];
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
