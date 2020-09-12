package com.learning.algorithm.basic.dynamic;

/**
 * <pre>
 * @Description:
 * 正则表达式匹配  暴力解法
 * </pre>
 *
 * @version v1.0
 * @ClassName: PattenMatch
 * @Author: sanwu
 * @Date: 2020/8/30 18:24
 */
public class PattenMatch {

    public boolean isMatch(String s, String p) {
        return isMatchByIndex(s, p , 0,0);
    }

    private boolean isMatchByIndex(String s, String p, int p1, int p2) {
        if(p2>=p.length()) return p1 >= s.length();
        boolean first = p1 < s.length() &&  (s.charAt(p1) == p.charAt(p2) || p.charAt(p2) == '.');
        if(p2 <= p.length()-2 && p.charAt(p2 +1) == '*') {
            return isMatchByIndex(s, p, p1, p2+2) || (first && isMatchByIndex(s, p, p1+1, p2)  );
        } else  {
            return  first && isMatchByIndex(s, p, p1+1,p2+1);
        }
    }

    /**
     * DP方法匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int n = s.length() + 1, m = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        // base case
        dp[0][0] = true;
        if (m > 2) {
            for (int i = 1; i < m-1; i+=2) {
                if(p.charAt(i)=='*'){
                    dp[i][0] = dp[i-1][0] ;
                    dp[i+1][0] = dp[i][0] ;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                char pat = p.charAt(i-1);
                dp[i][j] = dp[i-1][j-1] && (s.charAt(j-1) == pat || pat == '.');
                if(pat =='*' && i>=2){
                    // 进行减一位匹配的条件= 之前*匹配为true   或者  未进行*匹配是为true
                    if(dp[i][j-1]||dp[i-1][j-1]) {
                        dp[i][j] = dp[i-2][j] ||(s.charAt(j-1) == p.charAt(i-2)|| p.charAt(i-2) == '.');
                    } else {
                        dp[i][j] = dp[i-2][j];
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }
}
