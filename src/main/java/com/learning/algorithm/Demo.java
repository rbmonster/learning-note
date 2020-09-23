package com.learning.algorithm;

import javafx.util.Pair;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo
 * @Author: sanwu
 * @Date: 2020/8/2 15:25
 */
public class Demo {

    public static void main(String[] args) {
        Demo main = new Demo();
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        int[][] xx = {{0, 1, 0, 0, 1, 1, 0}, {1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1, 0}};
//        System.out.println(main.shortestPathBinaryMatrix(xx));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        System.out.println(map);
        map.putIfAbsent(1, 4);
        map.putIfAbsent(2, 4);
        System.out.println(map);
        int mer = map.merge(1, 6, (v1, v2) -> v1 + v2);
        System.out.println(map);
        int value = 123123;
        int key2 = map.computeIfAbsent(2, k -> value);
        int key3 = map.computeIfAbsent(3, k -> value);
        System.out.println(map);
        int res = map.computeIfPresent(3, (key, oldVal) -> oldVal - 1);
        System.out.println(map);
        int result1 = map.compute(3, (key, oldValue) -> oldValue - 10);
        int[][] example = new int[][]{{1,1,0}, {1,1,0},{0,0,1}};
//        System.out.println(main.findCircleNum(example));
//        System.out.println(main.findDuplicate(new int[] {1,2,3,3,4}));
//        System.out.println(main.hammingDistance(1,4));
    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i])-1;
            if(nums[index]>0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >0)
                res.add(i+1);
        }
        return res;
    }

    public char nextGreatestLetter(char[] letters, char target) {
        int min = Integer.MAX_VALUE;
        for(int i =0;i< letters.length;i++) {
        if(letters[i]> target) {
            min = Math.min(letters[i]- target, min);
        }
    }
        return (char)(target+ min);
    }

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length-1;
        int left = 0, right = nums.length;
        while(left<right) {
            int mid = left + (right - left)/2;
            if(nums[mid] < mid+1 ) {
                right= mid;
            }else {
                left = mid+1;
            }
        }

        return nums[left];
    }

    public boolean canJump(int[] nums) {
        int len = nums.length;
        boolean [] dp = new boolean[len];
        dp[len-1] =true;
        for (int i = len-2; i >=0 ; i--) {
            int distance = nums[i];
            for (int j = i+1; j <= i+distance && j<len; j++) {
                dp[i] = dp[i] || dp[j];
                if(dp[i]) break;
            }
        }
        return dp[0];
    }

//    private void dfs(int[]nums, int index) {
//        if (result|| index>= nums.length) return;
//        if (note.containsKey(index)) {
//            return;
//        }
//        if(index == nums.length-1) {
//            result = true;
//            return;
//        }
//        int distance = nums[index];
//        for (int i = index+1; i <= index+distance && i< nums.length; i++) {
//            dfs(nums, i);
//            if (result) break;
//            note.put(i, false);
//        }
//    }
}
