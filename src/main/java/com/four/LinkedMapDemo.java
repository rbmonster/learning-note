package com.four;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: LinkedMapDemo
 * @Author: sanwu
 * @Date: 2020/10/21 13:54
 */
public class LinkedMapDemo<K,V> extends LinkedHashMap<K,V> implements Map<K,V>{

    public LinkedMapDemo(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, true);
    }

    public static void main(String[] args) {
        LinkedMapDemo<Integer,Integer> map = new LinkedMapDemo<>(16,0.75f,true);
        map.put(1,2);
        map.put(2,3);
        map.put(4,5);
        map.get(2);
        map.put(6,7);
        System.out.println();
        map.keySet().stream().forEach(key -> System.out.println(key));
    }

    public void sort(int[]nums, int start, int end) {
        if(start>end){
            return;
        }
        int i =start,j = end,tmp=nums[start];
        while(i<j) {
            while(i<j && nums[j]>tmp) j--;
            nums[i] = nums[j];
            while(i<j&& nums[i]<tmp) i++;
            nums[j] = nums[i];
        }
        nums[i] = tmp;
        sort(nums,start,i-1);
        sort(nums,end, i+1);
    }
}
