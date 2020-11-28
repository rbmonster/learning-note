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
        int [] array = {3,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12,1,2,2,2,5,7,23,123,45,2,15,12};
//        Quicksort(array, 0 , array.length-1);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

    }

    /**
     * 快速排序
     * @param arr
     * @param start
     * @param end
     */
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

    public static void sortThree(int[] nums, int left, int right) {

    }

    public static int[] partition(int[] array,int left,int right){
        int v = array[left]; //选择右边界为基准
        int less = left - 1; // 最后指向 < v 部分的最后一个数
        int more = right + 1; // 最后指向 > v 部分的第一个数
        int cur = left;
        while(cur < more){
            if(array[cur] < v){
                swap(array,++less,cur);
                cur++;
            }else if(array[cur] > v){
                // 这里为什么 cur指针 cur++，因为指针从左至右开始扫描，当前不确定交换的元素是不是就大于比较值
                swap(array,--more,cur);
            }else{
                cur++;
            }
        }
        return new int[]{less + 1,more - 1};  //返回的是 = v 区域的左右下标
    }

    public static void Quicksort(int array[], int left, int right) {
        if (left < right) {
            int[] p = partition(array,left,right);
            Quicksort(array,left,p[0] - 1); //避开重复元素区间
            Quicksort(array,p[1] + 1,right);
        }
    }

    public static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
