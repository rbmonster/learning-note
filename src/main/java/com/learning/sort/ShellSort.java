package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * TODO
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
        while (true){
            gap = gap/2;
            for (int i = 0;i<gap;i++){
                for (int j = i+gap;j<arr.length;j+=gap){
                    int temp = arr[j],k = j-gap;
                    while(k>=0 && arr[k]>temp){
                        arr[k+gap] = arr[k];
                        k-=gap;
                    }
                    arr[k+gap] = temp;
                }
            }
            if (gap ==1){
                break;
            }
        }
    }
}
