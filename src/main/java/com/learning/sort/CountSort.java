package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CountSort
 * @Author: sanwu
 * @Date: 2020/6/28 23:22
 */
public class CountSort {

    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
//        int[] result = sort(array,0,array.length-1);
        int[] result = sort(array);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 不稳定排序版本
     * @param array
     * @return
     */
    public static int[] sort(int[] array) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] countArray = new int[max-min+1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]-min]++;
        }
        int[] sortArray = new int[array.length];
        for (int i = 0; i < sortArray.length; i++) {
            for (int j = 0; j < countArray.length; j++) {
                while (countArray[j] >0){
                    sortArray[i++] = j + min;
                    countArray[j]--;
                }
            }
        }
        return sortArray;
    }

    /**
     * 优化版计数排序
     *
     * @param array
     * @return
     */
    public static int[] countSort(int[] array) {
        //1.得到数列的最大值与最小值，并算出差值d
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if(array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        //2.创建统计数组并计算统计对应元素个数
        int[] countArray = new int[d + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        //3.统计数组变形，后面的元素等于前面的元素之和
        int sum = 0;
        for (int i = 0; i < countArray.length; i++) {
            sum += countArray[i];
            countArray[i] = sum;
        }
        //4.倒序遍历原始数组，从统计数组找到正确位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i > 0; i--) {
            sortedArray[countArray[array[i] - min] - 1] = array[i];
            // -1 表示后来的元素的位置应向前移动
            countArray[array[i] - min]--;
        }
        return sortedArray;
    }
}
