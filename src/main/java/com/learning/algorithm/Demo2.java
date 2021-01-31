package com.learning.algorithm;

import com.learning.algorithm.basic.treeNode.TreeNode;

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
        new Demo2().reverseWords("the sky is blue");

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

    }

    public String reverseWords(String s) {
        if (s == null || s.trim().length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int index = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(' ');
                }
                continue;
            }
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                index = sb.length();
            }
            sb.insert(index, ch);
        }
        return sb.reverse().toString();
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
        dfs(str, index + 1);
        int cur = str.charAt(index) - '0';
        if (cur > 0 && cur <= 2 && index + 1 < len) {
            int next = str.charAt(index + 1) - '0';
            if (cur == 2 && next > 5) return;
            dfs(str, index + 2);
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

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }

        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    public TreeNode preIn(int[] p, int pi, int pj, int[]
            n, int ni, int nj, Map<Integer, Integer> map) {

        if (pi > pj) {
            return null;
        }
        TreeNode head = new TreeNode(p[pi]);
        int index = map.get(p[pi]);                  //这样比原始方案一的方式效率要高，值得思考的地方
        head.left = preIn(p, pi + 1, pi + index - ni, n, ni, index - 1, map);
        head.right = preIn(p, pi + index - ni + 1, pj, n, index + 1, nj, map);
        return head;
    }

}
