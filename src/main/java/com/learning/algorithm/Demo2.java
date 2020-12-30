package com.learning.algorithm;

import java.util.*;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo2
 * @Author: sanwu
 * @Date: 2020/12/13 19:02
 */
public class Demo2 {
    public static void main(String[] args) {
        new Demo2().translateNum(18580);

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2) ->  o2-o1);

    }

    int res = 0;

    public int translateNum(int num) {
        String str = String.valueOf(num);

        dfs(str, 0);
        return res;
    }


    public void dfs(String str, int index) {
        int len = str.length();
        if (index + 1 >= len) {
            res++;
            return;
        }
        dfs(str, index+1);
        int cur = str.charAt(index)-'0';
        if (cur >0 && cur<=2 && index+1<len ) {
            int next = str.charAt(index+1)-'0';
            if (cur == 2&& next>5) return;
            dfs(str, index+2);
        }
    }


    public static int[] printNumbers(int n) {
        int end = (int) Math.pow(10, n);
        int[] res = new int[end - 1];
        for (int i = 1; i < end; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String sortStr = Arrays.stream(str.split("")).sorted(String::compareTo).reduce(String::concat).get();
            List<String> cur = map.getOrDefault(sortStr, new ArrayList<>());
            cur.add(str);
            map.put(sortStr, cur);
        }
        res.addAll(map.values());
        return res;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            boolean res = o1[1] < o2[1];
            return res ? -1 : o1[1] == o2[1] ? 0 : 1;
        });
        int lastIndex = Integer.MIN_VALUE;
        int result = 0;
        for (int[] cur : intervals) {
            if (cur[0] < lastIndex) {
                result++;
            } else {
                lastIndex = cur[1];
            }
        }
        return result;
    }
}
