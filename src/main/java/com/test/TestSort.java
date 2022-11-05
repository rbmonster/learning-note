package com.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestSort {


    public static void main(String[] args) throws InterruptedException {
        int[] array = {3, 1, 2, 2, 2, 5, 7, 23, 123, 45, 2, 15, 12};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }


    private static  void heapSort(int[] nums) {
        int len = nums.length;
        for (int i = len/2+1; i >=0; i--) {
            adjustHeap(nums, i, len);
        }
        for (int j = len-1; j >=0; j--) {
            swap(nums, 0, j);
            adjustHeap(nums, 0, j);
        }
    }

    private static  void adjustHeap(int[] nums, int i, int len) {
        int tmp = nums[i];
        for (int k = 2*i+1; k < len; k = 2*k+1) {
            if (k+1<len && nums[k+1]>nums[k]) {
                k++;
            }
            if(nums[k] > tmp) {
                nums[i] = nums[k];
                i = k;
            }
        }
        nums[i] = tmp;
    }

    private static void bubbleSort(int[] nums) {
        int len = nums.length;
        for(int i = len-1;i>=0;i--){
            for (int j = 0; j < i; j++) {
                if(nums[j]>nums[j+1]) {
                    swap(nums, j, j+1);
                }
            }
        }
    }

    private static void quickSort2(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int[] p = partition2(nums, left, right);
        quickSort2(nums, left, p[0]);
        quickSort2(nums, p[1], right);
    }

    private static int[] partition2(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left - 1, j = right + 1;
        int p = left;
        while (p < j) {
            if (nums[p] > pivot) {
                swap(nums, --j, p);
            } else if (nums[p] < pivot) {
                swap(nums, ++i, p);
                p++;
            } else {
                p++;
            }
        }
        return new int[]{i, j};
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= pivot) j--;
            while (i < j && nums[i] <= pivot) i++;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, i, left);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
