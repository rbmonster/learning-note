package com.learning.algorithm.basic.binarysearch;

import java.util.function.Function;

public class BinarySearchLeft {

    public static void main(String[] args) {
        searchCase(BinarySearchLeft::left_bound);
    }

    private static void searchCase(Execution execution) {
        int[] arr = {1, 2, 2, 4, 4, 5, 5, 7};
        System.out.println("左越界 result:" + execution.execute(arr, 0));
        System.out.println("hit result:" + execution.execute(arr, 2));
        System.out.println("右越界 result:" + execution.execute(arr, 8));
        System.out.println("范围内但值不存在 result:" + execution.execute(arr, 6));
    }


    /**
     * left < right  且  int left = 0, right = nums.length;
     * 寻找第一个等于或大于的 target左边界
     *
     * {1,2,2,3,4,5,5,7}  寻找0(左越界)          -> left: 0 第一个大于的数   result:-1
     * {1,2,2,3,4,5,5,7}  寻找2(存在)            -> left: 1 第一个相等      result:1
     * {1,2,2,3,4,5,5,7}  寻找8(右越界)          -> left: 8 越界           result:-1
     * {1,2,2,3,4,5,5,7}  寻找6(范围内但值不存在)  -> left: 7 第一个大于的数  result:-1
     * <p>
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchLeft(int[] nums, int target) {
        int left = 0, right = nums.length;

        // 结束条件 left == right
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                // 搜索到目标值之后，仍修改右边界
                right = mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        // 判断: 若搜索区间无目标元素 会出现越界情况 如{1,2,2,4,4,5,5,7} target:8
        System.out.print("left ->" + left + " ");
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

    /**
     * left <= right 且 int left = 0, right = nums.length - 1;
     * 寻找第一个等于或大于的target左边界
     *
     * {1,2,2,3,4,5,5,7}  寻找0(左越界)          -> left: 0 第一个大于的数   result:-1
     * {1,2,2,3,4,5,5,7}  寻找2(存在)            -> left: 1 第一个相等      result:1
     * {1,2,2,3,4,5,5,7}  寻找8(右越界)          -> left: 8 越界           result:-1
     * {1,2,2,3,4,5,5,7}  寻找6(范围内但值不存在)  -> left: 7 第一个大于的数  result:-1
     *
     * <p>
     *
     * @param nums
     * @param target
     * @return
     */
    public static int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) { // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        System.out.print("left ->" + left + " ");
        /**
         * 出界检查: 若搜索区间无目标元素 会出现越界情况 如{1,2,2,4,4,5,5,7} target:8
         * 使用left判断的原因： 若存在目标元素往左收缩，最后跳出循坏的结果肯定是：{x, x, right, left, x, x}
         */
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }


    /**
     * 查找第一个等于或者小于key的元素
     *
     * {1,2,2,3,4,5,5,7}  寻找0(左越界)          -> right: -1  越界        result:-1
     * {1,2,2,3,4,5,5,7}  寻找2(存在)            -> right: 0   第一个小于   result:0
     * {1,2,2,3,4,5,5,7}  寻找8(右越界)          -> right: 7   第一个小于    result:7
     * {1,2,2,3,4,5,5,7}  寻找6(范围内但值不存在)  -> right: 6   第一个小于   result:6
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findFirstSmaller(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        System.out.print("right ->" + right + " ");
        if (right < 0) {
            return -1;
        }
        return right;
    }

    @FunctionalInterface
    private interface Execution {
        int execute(int[] nums, int target);
    }
}
