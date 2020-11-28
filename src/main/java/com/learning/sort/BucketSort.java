package com.learning.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 桶排序  计数排序升级版
 *   稳定排序
 * </pre>
 *
 * @version v1.0
 * @ClassName: BucketSort
 * @Author: sanwu
 * @Date: 2020/11/28 11:07
 */
public class BucketSort {
    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 桶排序
     * 1. 获取最大最小树，桶的空间
     * 2. num[i] -min 定位桶索引，添加当前树到桶中。
     * 3. 使用索引扫描每个桶位，更新到排序数组中
     * @param nums
     */
    public static void sort(int[] nums) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        ArrayList<Integer>[] buckets = new ArrayList[max-min +1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            buckets[nums[i]-min].add(nums[i]);
        }
        for (int i = 0,j = 0; i < buckets.length; i++) {
            List<Integer> curList = buckets[i];
            int index = 0;
            while (!curList.isEmpty() && index< curList.size()){
                nums[j++] = curList.get(index++);
            }
        }
    }
}
