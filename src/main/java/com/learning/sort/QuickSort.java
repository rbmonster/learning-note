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
        int[] array = {3, 1, 2, 5, 7, 23, 123, 45, 2, 15, 12};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序 挖坑法
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void sort1(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int i = start;
        int j = end;
        int tmp = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= tmp) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= tmp) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = tmp;
        sort1(arr, start, i - 1);
        sort1(arr, i + 1, end);
    }


    /**
     * 左右指针法
     * @param arr
     * @param left
     * @param right
     */
    public static void sort(int[] arr, int left, int right) {
        if (left > right) return;
        int pos = partition(arr, left, right);
        sort(arr, left, pos - 1);
        sort(arr, pos + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int tmp = arr[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[i] <= tmp) i++;
            while (i < j && arr[j] >= tmp) j--;
            swap(arr, i, j);
        }
        swap(arr, i, left);
        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
