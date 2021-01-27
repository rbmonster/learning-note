package com.learning.algorithm.greedy;

import java.util.Arrays;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: FindMinArrowShots
 * @Author: sanwu
 * @Date: 2021/1/24 17:06
 */
public class FindMinArrowShots {

    public static void main(String[] args) {
        int[][] point = new int[][] {{9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}};
        new FindMinArrowShots().findMinArrowShots(point);
    }

    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (o1, o2)-> o1[1]<=o2[1]? -1: 1);
        int end = points[0][1];
        int res = 1;
        for(int i = 1; i< points.length; i++) {
            if(end >= points[i][0]) {
                continue;
            }
            end = points[i][1];
            res++;
        }
        return res;
    }
}
