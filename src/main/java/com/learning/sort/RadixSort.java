package com.learning.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 基数排序
 * </pre>
 *
 * @version v1.0
 * @ClassName: RadixSort
 * @Author: sanwu
 * @Date: 2020/11/28 11:59
 */
public class RadixSort {

    public static void main(String[] args) {
        int [] array = {3,1,2,2,2,5,7,23,123,45,2,15,12};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 基数排序
     * 循环：
     * 1. 获取每个数的第i个位置的基数，放到桶中
     * 2. 遍历桶元素替换到排序数组中
     * 3. 递归循环直至最高位的基数
     * @param nums
     */
    public static void sort(int[] nums) {
        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int maxLen = String.valueOf(max).length();
        int mod = 10, div = 1;
        for (int i = 1; i <= maxLen; i++, mod*=10, div*=10) {
            for (int num : nums) {
                // 求当前数在位置i上的基数
                int index = (num%mod)/div;
                buckets[index].add(num);
            }
            int index = 0;
            for (List<Integer> curList : buckets) {
                int curIndex = 0;
                while (curIndex < curList.size()) {
                    nums[index++] = curList.get(curIndex++);
                }
                curList.clear();
            }
        }
    }

}
