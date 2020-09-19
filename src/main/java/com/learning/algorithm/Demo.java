package com.learning.algorithm;

import javafx.util.Pair;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;
import java.util.stream.Collectors;

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
        Demo main = new Demo();
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        int[][] xx = {{0, 1, 0, 0, 1, 1, 0}, {1, 0, 0, 0, 0, 0, 0}, {1, 0, 0, 1, 1, 1, 1}, {0, 1, 0, 0, 0, 0, 0}, {1, 0, 0, 0, 0, 0, 1}, {1, 0, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1, 0}};
//        System.out.println(main.shortestPathBinaryMatrix(xx));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        System.out.println(map);
        map.putIfAbsent(1, 4);
        map.putIfAbsent(2, 4);
        System.out.println(map);
        int mer = map.merge(1, 6, (v1, v2) -> v1 + v2);
        System.out.println(map);
        int value = 123123;
        int key2 = map.computeIfAbsent(2, k -> value);
        int key3 = map.computeIfAbsent(3, k -> value);
        System.out.println(map);
        int res = map.computeIfPresent(3, (key, oldVal) -> oldVal - 1);
        System.out.println(map);
        int result1 = map.compute(3, (key, oldValue) -> oldValue - 10);
        int[][] example = new int[][]{{1,1,0}, {1,1,0},{0,0,1}};
//        System.out.println(main.findCircleNum(example));
    }

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
            dfs(candidates,0,target, new ArrayList<>());
        return res;
    }

    public void dfs(int[] candidates, int index, int target, List<Integer> path) {
        if (target == 0 ) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (index>= candidates.length) return;
        if(target<candidates[0]) return;
        for (int i = index; i < candidates.length; i++) {
            if(i>index && candidates[i] == candidates[i-1]) continue;
            if(candidates[i] >target) break;
            path.add(candidates[i]);
            dfs(candidates,i+1,target-candidates[i], path);
            path.remove(path.size()-1);
        }
    }
}
