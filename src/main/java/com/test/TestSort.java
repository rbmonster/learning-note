package com.test;

import java.util.Arrays;
import java.util.LinkedList;

public class TestSort {

    public static void main(String[] args) {
        int[] array = {3, 1, 2, 5, 7, 23, 123, 45, 2, 15, 12};
        heapsort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void heapsort(int[] nums) {
        int len = nums.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, len);
        }
        for (int j = len - 1; j > 0; j--) {
            swap(nums, 0, j);
            adjustHeap(nums, 0, j );
        }
    }
    public String reverseWords(String s) {
        // Write your code here
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String str: strs) {
            sb.append(reverseHelper(str) + " ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    private String reverseHelper(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static void adjustHeap(int[] nums, int i, int len) {
        int tmp = nums[i];
        for (int j = 2 * i + 1; j < len; j = j * 2 + 1) {
            if (j + 1 < len && nums[j] < nums[j + 1]) {
                j = j + 1;
            }
            if (nums[j] > tmp) {
                nums[i] = nums[j];
                i = j;
            } else {
                break;
            }
        }
        nums[i] = tmp;
    }

    public static void redisSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        LinkedList<Integer>[] bucket = new LinkedList[10];
//        Arrays.fill(bucket, new LinkedList<Integer>());
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new LinkedList<>();
        }
        int len = String.valueOf(max).length();

        int mod = 10;
        int div = 1;
        for (int i = 1; i <= len; i++, mod *= 10, div *= 10) {
            for (int num : nums) {
                int cur = (num % mod) / div;
                bucket[cur].add(num);
            }
            int idx = 0;
            for (int j = 0; j < 10; j++) {
                LinkedList<Integer> list = bucket[j];
                for (Integer num : list) {
                    nums[idx++] = num;
                }
                list.clear();
            }
        }
    }

    public static int[] mergeSort(int[] nums, int start, int end) {
        if (start > end) {
            return new int[0];
        }
        if (start == end) {
            return new int[]{nums[start]};
        }
        int mid = (end - start) / 2 + start;
        int[] left = mergeSort(nums, start, mid);
        int[] right = mergeSort(nums, mid + 1, end);
        int i = 0, j = 0, idx = 0;
        int[] res = new int[left.length + right.length];
        while (i < left.length && j < right.length) {
            res[idx++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            res[idx++] = left[i++];
        }
        while (j < right.length) {
            res[idx++] = right[j++];
        }
        return res;
    }

    public static void countSort(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        int[] site = new int[max - min + 1];
        for (int num : nums) {
            site[num - min]++;
        }
        int index = 0;
        for (int i = 0; i < site.length; i++) {
            while (site[i] > 0) {
                nums[index] = i + min;
                index++;
                site[i]--;
            }
        }
    }

    public static void bubbleSort(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static void insertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j - 1] > nums[j]) {
                    swap(nums, j - 1, j);
                }
            }
        }
    }

    public static void selectionSort(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            int maxIdx = i;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[maxIdx]) {
                    maxIdx = j;
                }
            }
            swap(nums, maxIdx, i);
        }
    }

    public static void quicksort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int site = partition(nums, start, end);
        quicksort(nums, start, site - 1);
        quicksort(nums, site + 1, end);
    }

    public static int partition(int[] nums, int start, int end) {
        int i = start, j = end;
        int site = nums[start];
        while (i < j) {
            while (i < j && nums[j] >= site) j--;
            while (i < j && nums[i] <= site) i++;
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, i, start);
        return i;
    }

    public static void quicksort2(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int[] sites = partition2(nums, start, end);
        quicksort2(nums, start, sites[0]);
        quicksort(nums, sites[1], end);
    }

    public static int[] partition2(int[] nums, int start, int end) {
        int i = start - 1, j = end + 1;
        int site = nums[start];
        int index = 0;
        while (index < j) {
            if (nums[index] > site) {
                swap(nums, index, --j);
            } else if (nums[index] < site) {
                swap(nums, index, ++i);
                index++;
            } else {
                index++;
            }
        }
        return new int[]{i, j};
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
