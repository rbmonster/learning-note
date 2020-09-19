package com.learning.algorithm.basic;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: RotateArrayBS
 * @Author: sanwu
 * @Date: 2020/9/17 21:41
 */
public class RotateArrayBS {

    /**
     * 寻找旋转数组目标值
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 寻找旋转数组的最小值
     * 无重复数字
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[0]) {
            return nums[0];
        }
        // Binary search way
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 寻找旋转数组的最小值
     * 有重复数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }


    /**
     * 查找峰值
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        //本题从-inf到-inf, 相邻元素必不相同
        //不可能都是-inf,只要有一个元素就一定有上坡,就一定有峰顶
        // 使用二分法找峰顶
        if(nums.length==1) return 0;
        int l=0,r=nums.length-1;
        while(l<r){// [l,r] 型二分法,此处判别是开还是闭看l和r的取值
            // 虽然是闭区间上查,但用<号,所以最后退出循环时[l,l]或者[r,r]是不会查的
            int mid=(l+r)/2; // 向下取整,所以mid+1不会溢出
            if(nums[mid]>nums[mid+1]){// mid比右侧大, 峰顶在左侧或就在mid处
                r=mid;// [l,mid]
            }else if(nums[mid]<nums[mid+1]){
                l=mid+1;// mid比右侧小,峰顶在右侧[mid+1,r]
            }
        }// 退出循环时 l==r

        // 在l==r时,其实是没有判断当前是否就是答案, 但本题一定会有答案
        // ==>所以就没有去判断了
        return l;
    }
}
