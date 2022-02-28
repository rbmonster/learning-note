package com.learning.algorithm.basic.binarysearch;

import java.util.Arrays;

public class BinarySearchMutation {

    public static void main(String[] args) {
        int[] array = {-1,0,3,5,9,12};
        System.out.println(problemCloseOpenSearch(array, 2));
    }

    /**
     * 用于查找当前目标值的左右节点
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            System.out.println("当前搜索左右索引分别为：" + left + " " + right);
            System.out.println("当前搜索数组：" + Arrays.toString(Arrays.copyOfRange(nums, left, right)));
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left + 1 == right
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    /**
     * 闭开区间 变种写法问题
     * right = nums.length - 1 会出现未搜索  nums.length - 1 节点问题, 需要额外判断
     *
     * @param nums
     * @param target
     * @return
     */
    public static int closeOpenSearch(int[] nums, int target) {
        // 与常规的闭开区间的写法不同，right = nums.length - 1
        int left = 0, right = nums.length - 1;
        while (left < right) {
            System.out.println("当前搜索左右索引分别为：" + left + " " + right);
            System.out.println("当前搜索数组：" + Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));

            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[right] == target ? right : -1;
    }


    /**
     * 问题写法
     *  int[] array = {-1,0,3,5,9,12}; target = 2
     *
     * 会出现死循环
     *    当前搜索数组：[0, 3]
     *    当前搜索左右索引分别为：1 2
     *    left 一直停留在1
     * @param nums
     * @param target
     * @return
     */
    public static int problemCloseOpenSearch(int[] nums, int target) {
        // 与常规的闭开区间的写法不同，right = nums.length - 1
        int left = 0, right = nums.length;
        while (left < right) {
            System.out.println("当前搜索左右索引分别为：" + left + " " + right);
            System.out.println("当前搜索数组：" + Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));

            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid-1;
            }
        }

        return nums[right] == target ? right : -1;
    }

    /**
     * 双闭区间 变种写法问题
     * left < right  会出现漏搜索left == right的节点问题，需要额外判断
     *
     * @param nums
     * @param target
     * @return
     */
    public static int closeCloseSearch(int[] nums, int target) {
        // 与常规的闭开区间的写法不同，right = nums.length - 1
        int left = 0, right = nums.length - 1;
        while (left < right) {
            System.out.println("当前搜索左右索引分别为：" + left + " " + right);
            System.out.println("当前搜索数组：" + Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[right] == target ? right : -1;
    }

}
