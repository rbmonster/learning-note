package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description: 快速排序
 * </pre>
 *
 * @version v1.0
 * @ClassName: QuickSort
 * @Author: 86159
 * @Date: 2020/4/22 23:07
 */
public class QuickSort {

    public static void main(String[] args) {
        int [] array = {3,1,2,2,2,5,7,23,123,45,2,15,12};
        new QuickSort().quickSort(array, 0 , array.length-1);
        System.out.println(Arrays.toString(array));

    }

    public void quickSort(int[] arr, int start, int end) {
        if (start> end){
            return;
        }
        int i = start;
        int j = end;
        int tmp = arr[i];
        while (i<j){
            while (i<j && arr[j] >= tmp) {
                j--;
            }
            arr[i] = arr[j];
            while (i< j && arr[i] <= tmp) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = tmp;
        quickSort(arr,start, i-1);
        quickSort(arr, i+1, end);
    }
}
