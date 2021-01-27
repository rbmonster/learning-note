package com.learning.algorithm.greedy;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: NoRepeatSeq
 * @Author: sanwu
 * @Date: 2021/1/24 16:40
 */
public class NoRepeatSeq {

    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1,100},{11,22},{1,11},{2,12}};
        new NoRepeatSeq().eraseOverlapIntervals(intervals);
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length ==0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[1]<=o2[1] ? -1: 1);
        int end = intervals[0][1];
        int remove = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (end<=intervals[i][0]){
                end = intervals[i][1];
            } else {
                remove++;
            }
        }
        return remove;

    }
}
