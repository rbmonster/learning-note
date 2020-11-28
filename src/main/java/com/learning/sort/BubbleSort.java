package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * 冒泡排序
 *  1. 区间的元素比较，比较区间内两两之间的元素大小，进行交换
 *  2. 一轮比较下来，最大的元素最大的元素被交换到最后
 *  3. 再比较 n-1 的区间，以此类推。
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: BubbleSort
 * @Author: 86159
 * @Date: 2020/4/22 21:28
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        bubbleSort2(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 外层循环表示当前获取第几个最大的数
     * 内层循环表示进行排序的区间
     * @param nums
     */
    public static void sort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < len - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
