package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: InsertionSort
 * @Author: sanwu
 * @Date: 2020/5/7 22:53
 */
public class InsertionSort {
    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertSort(int[]arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j >0 && arr[j] <= arr[j-1]; j--) {
                int tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }
        }
    }
}
