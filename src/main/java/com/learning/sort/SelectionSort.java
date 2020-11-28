package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 *     选择排序
 *     1. 选择第一个节点的数的位置，假设该节点为最小的值，
 *     2. 挨个比较该节点后的所有数字，取最小位置的索引
 *     3. 与第一个节点交换。
 *     4. 假设第二个节点为最小数，挨个比较，以此类推.
 * </pre>
 *
 * @version v1.0
 * @ClassName: SelectionSort
 * @Author: 86159
 * @Date: 2020/4/22 22:06
 */
public class SelectionSort {
    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 选择一个最小的元素交换至第一位
     * 选择一个次小的元素交换至第二位
     * 以此类推
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i< arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j< arr.length; j++){
                if (arr[j]< arr[min]) {
                    min = j;
                }
            }
            if (min != i){
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
    }
}
