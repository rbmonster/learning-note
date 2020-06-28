package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 *  归并排序
 * </pre>
 *
 * @version v1.0
 * @ClassName: MergeSort
 * @Author: sanwu
 * @Date: 2020/6/19 14:59
 */
public class MergeSort {
    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
//        int[] result = sort(array,0,array.length-1);
        int[] result = sort2(array);
        System.out.println(Arrays.toString(result));
    }

    public static int[] sort(int[] nums, int start, int end){
        if (start == end){
            return new int[]{nums[start]};
        }
        int mid = start + (end - start)/2;
        int[]left = sort(nums, start, mid);
        int[]right = sort(nums,mid+1, end);
        int[] result = new int[left.length +right.length];
        int index = 0, i = 0,j=0;
        while (index<result.length && i<left.length && j<right.length){
            result[index++] = left[i]<right[j] ? left[i++]:right[j++];
        }
        while (i < left.length){
            result[index++] = left[i++];
        }
        while (j < right.length){
            result[index++] = right[j++];
        }
        return result;
    }

    public static  int[] sort2(int[] array) {
        int len = array.length;
        int[] result = new int[len];
        int block, start;
        for ( block = 1; block < len*2; block*=2) {
            for ( start = 0; start < len; start+=block*2) {
                int low = start;
                int mid = (start + block) < len?  (start+block): len;
                int end = (start + block*2)<len? (start+block*2): len;
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = end;
                while (start1< end1 && start2< end2) {
                    result[low++] = array[start1]<array[start2]? array[start1++]: array[start2++];
                }
                while (start1 < end1){
                    result[low++] = array[start1++];
                }
                while (start2 < end2){
                    result[low++] = array[start2++];
                }
            }
            int [] temp = array;
            array = result;
            result = temp;
        }
        result = array;
        return result;
    }
}
