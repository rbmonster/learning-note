package com.learning.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * @Description: TODO(一句话描述该类的功能)
 * </pre>
 *
 * @version v1.0
 * @ClassName: Soluction
 * @Author: 86159
 * @Date: 2020/4/1 20:17
 */
public class Soluction {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int birthLen = input.nextInt();
        List<Integer> births = new ArrayList<>();
        for (int i = 0; i<birthLen; i++){
            births.add(input.nextInt());
        }
        int time = input.nextInt();
        int a = new Soluction().birthChild(n,1,time, m,births);
        System.out.println(a);
    }

    public int birthChild(int num ,int age, int time, int end, List<Integer> births ) {
        if (age >= end|| time<=0){
            return num;
        }
        if (births.contains(age)){
            return birthChild(num, ++age, --time, end, births) + birthChild(num, 1, --time, end, births);
        }
        return birthChild(num, ++age, --time,end, births);
    }
}
