package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * 希尔 排序
 * </pre>
 *
 * @version v1.0
 * @ClassName: ShellSort
 * @Author: sanwu
 * @Date: 2020/5/11 0:20
 */
public class ShellSort {

    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[]arr) {
        int gap = arr.length;
        while (gap>1){
            gap = gap/2;
            for (int i = 0;i<gap;i++){
                for (int j = i+gap;j<arr.length;j+=gap){
                    // 在shell gap 间隔的区间进行交换排序
                    int k = j-gap;
                    while (k>=0&&arr[k]>arr[k+gap]){
                        int tmp =arr[k+gap];
                        arr[k+gap] = arr[k];
                        arr[k] = tmp;
                    }
                    //这是使用覆盖性质的排序，减少了交换次数。
                  /*  int tmp = nums[j], k=j-gap;
                    while (k>=0 && nums[k] > tmp) {
                        nums[k+gap] = nums[k];
                        k-=gap;
                    }
                    nums[k+gap] = tmp;
                   */
                }
            }
        }
    }
}
