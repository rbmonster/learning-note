package com.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        System.out.println(solution(5));
    }

    private static int solution(int N) {
        if(N> 45) {
            return -1;
        }
        if (N>=0 && N<=9) {
            return N;
        }
        List<Integer> combineList = new LinkedList<>();
        for(int i = 1;i<=9;i++) {
            int start = i;
            int target = N;
            List<Integer> combine = new LinkedList<>();
            while(target>0 && start<=9) {
                combine.add(start);
                target-=start;
                if(target<0 || target<i) {
                    break;
                }
                if (target>start && target<=9) {
                    combine.add(target);
                    combineList.add(combine.size());
                    combine.remove(combine.size()-1);
                }
                start++;
            }
        }
        int res = 0;
        for(Integer tmp : combineList) {
            res+= countCombine(tmp);
        }
        return res;
    }

    private static int countCombine(int n) {
        int res = 1;
        while(n>0) {
            res *=n;
            n--;
        }
        return res;
    }
}
