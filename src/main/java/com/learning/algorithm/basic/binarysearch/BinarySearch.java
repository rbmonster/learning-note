package com.learning.algorithm.basic.binarysearch;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * 二分法
 * </pre>
 *
 * @version v1.0
 * @ClassName: BinarySearch
 * @Author: sanwu
 * @Date: 2020/8/16 22:46
 */
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch main = new BinarySearch();
        int [] array = {2, 4, 6, 12, 14, 17, 22, 24, 25, 26, 28, 83};
        int [] arr = {1,2,2,4,4,5,5,7};
//        int res = main.binarySearch(array, 5);
    }


    /**
     * 闭区间
     * int left = 0, right = nums.length - 1; 且 left <= right
     * 通用的二分法模板
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    /**
     * 开区间变形
     *  int left = 0, right = nums.length; 且 left < right
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return -1;
    }


    /**
     * java 程序中的二分
     *
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}
