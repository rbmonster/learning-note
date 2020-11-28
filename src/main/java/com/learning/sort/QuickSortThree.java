package com.learning.sort;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * 三路快排
 * </pre>
 *
 * @version v1.0
 * @ClassName: QuickSortThree
 * @Author: sanwu
 * @Date: 2020/11/28 16:23
 */
public class QuickSortThree {

    public static void main(String[] args) {
        int [] array = {3,1,2,5,7,23,123,45,2,15,12};
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int array[], int left, int right) {
        if (left < right) {
            int[] p = partition(array,left,right);
            quickSort(array,left,p[0] - 1); //避开重复元素区间
            quickSort(array,p[1] + 1,right);
        }
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

    public static void swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
