package com.learning.algorithm;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo
 * @Author: sanwu
 * @Date: 2020/8/2 15:25
 */
public class Demo {

    public static void main(String[] args) {
        int [] num  = {-1,2147483647};
        System.out.println(new Demo().containsNearbyAlmostDuplicate(num, 1,2147483647));
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length ==0) {
            return false;
        }
        for(int i =0 ;i<nums.length ; i++) {
            for(int j = i+1; j<nums.length&& j-i<=k;j++) {
                long res = Math.abs(nums[i]-nums[j]);
                if(res <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}
