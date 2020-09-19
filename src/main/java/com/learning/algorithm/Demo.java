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
//        int result = map.compute(4,(key, oldValue) -> oldValue-10);
//        map.computeIfAbsent()
//        System.out.println(main.fourSum(new int[] {0,0,0,0}, 0 ));
        int[][] example = new int[][]{{1,1,0}, {1,1,0},{0,0,1}};
        System.out.println(main.findCircleNum(example));
    }


    public int findCircleNum(int[][] M) {
        int m = M.length;
        DisjointSet disjointSet = new DisjointSet(m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(i == j) continue;
                if (M[i][j]==1) {
                    disjointSet.union(i,j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            int tmp = disjointSet.find(i);
            if(tmp==i) {
                res++;
            }
        }
        return res;
    }

    class DisjointSet{
        int[] parent;
        byte[] rank;

        public DisjointSet(int n){
            parent = new int[n];
            rank = new byte[n];
        }

        public int find(int i) {
            if(parent[i] == 0) return i;
            return parent[i] = find(parent[i]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) return true;
            if(rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            return false;
        }
    }
}
