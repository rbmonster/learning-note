package com.learning.algorithm.greedy;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: ChildCookies
 * @Author: sanwu
 * @Date: 2021/1/24 16:21
 */
public class ChildCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        int len1 = g.length;
        int len2 = s.length;
        while(i < len1 && j < len2) {
            if(g[i] <= s[j]){
                i++;
            }
            j++;
        }
        return i;
    }
}
