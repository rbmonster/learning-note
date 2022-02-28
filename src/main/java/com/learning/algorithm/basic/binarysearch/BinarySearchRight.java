package com.learning.algorithm.basic.binarysearch;

public class BinarySearchRight {

    public static void main(String[] args) {
        searchCase(BinarySearchRight::searchRight);
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
     * 寻找等于或小于 右边界的二分
     *  注意：结果左侧越界
     *
     * <p>
     * {1,2,2,3,4,5,5,7}  寻找0(左越界)          -> right: -1    越界           result:-1
     * {1,2,2,3,4,5,5,7}  寻找2(存在)            -> right: 2    最后一个相等    result:2
     * {1,2,2,3,4,5,5,7}  寻找8(右越界)          -> right: 7    第一个小于      result:-1
     * {1,2,2,3,4,5,5,7}  寻找6(范围内但值不存在)  -> right: 6    第一个小于      result:-1
     * <p>
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchRight(int[] nums, int target) {
        int left = 0, right = nums.length;
        // 结束条件 left == right
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                // 搜索到目标值之后，仍修改左边界，不断向右逼近
                left = mid + 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        System.out.print("right ->" + (right - 1) + " ");
        // 为什么要减⼀，这是搜索右侧边界的⼀个特殊点，关键在这个条件判断：left = mid + 1;
        // while 循环结束时， nums[left] ⼀定不等于 target 了，⽽ nums[left-1] 可能是 target 。
        if (right - 1 < 0) {
            return -1;
        }
        return right - 1;

    }

    /**
     * left <= right 且 int left = 0, right = nums.length - 1;
     * 寻找等于或小于 的target右边界的二分
     *  注意：结果左侧越界
     *
     * <p>
     * {1,2,2,3,4,5,5,7}  寻找0(左越界)          -> right: -1  越界           result:-1
     * {1,2,2,3,4,5,5,7}  寻找2(存在)            -> right: 2   最后一个相等    result:2
     * {1,2,2,3,4,5,5,7}  寻找8(右越界)          -> right: 7   第一个小于      result:7
     * {1,2,2,3,4,5,5,7}  寻找6(范围内但值不存在)  -> right: 6   第一个小于      result:8
     *
     * <p>
     *
     * @param nums
     * @param target
     * @return
     */
    public static int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        System.out.print("right ->" + right + " ");
        // 为啥用right判断? 若存在重复target元素，循坏跳出的条件肯定是想右收缩过程中 left+1 导致的break
        if (right < 0) {
            return -1;
        }
        return right;
    }


    /**
     * 查找第一个等于或大于key的元素
     *  注意：结果右侧越界
     *
     * <p>
     * {1,2,2,3,4,5,5,7}  寻找0(左越界)          -> left: 0  第一个大于  result:0
     * {1,2,2,3,4,5,5,7}  寻找2(存在)            -> left: 3  第一个大于  result:3
     * {1,2,2,3,4,5,5,7}  寻找8(右越界)          -> left: 8  越界       result:-1
     * {1,2,2,3,4,5,5,7}  寻找6(范围内但值不存在)  -> left: 7  第一个大于  result:7
     *
     * @param nums
     * @param target
     * @return
     */
    public static int findFirstLarger(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        System.out.print("left ->" + left + " ");
        // 为什么用left，因为left是循环跳出的地方默认在right的右边
        if (left >= nums.length) {
            return -1;
        }
        return left;
    }

    @FunctionalInterface
    private interface Execution {
        int execute(int[] nums, int target);
    }
}
