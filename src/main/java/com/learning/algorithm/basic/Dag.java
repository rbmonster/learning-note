package com.learning.algorithm.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * @Description:
 * 拓扑排序经典题目
 * leetcode 课程表
 * </pre>
 *
 * @version v1.0
 * @ClassName: Dag
 * @Author: sanwu
 * @Date: 2020/9/11 16:30
 */
public class Dag {


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> degreeList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            degreeList.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] cur : prerequisites) {
            inDegree[cur[0]]++;
            degreeList.get(cur[1]).add(cur[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            Integer node = queue.poll();
            List<Integer> outDegree = degreeList.get(node);
            for (Integer out : outDegree) {
                inDegree[out]--;
                if(inDegree[out] == 0) {
                    queue.add(out);
                }
            }
            numCourses--;
        }
        return numCourses==0;
    }


    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] cur : prerequisites) {
            adjacency.get(cur[0]).add(cur[1]);
        }
        int[] flag = new int[numCourses];
        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flag, i)) return false;
        return true;
    }

    private boolean dfs(List<List<Integer>> adjacency,int[] flag,int curNode) {
        if(flag[curNode] == 1) return  false;
        if(flag[curNode] == 2) return  true;
        flag[curNode]  =1;
        List<Integer> curAdjacency = adjacency.get(curNode);
        for (Integer node : curAdjacency) {
            if(!dfs(adjacency, flag, node)) {
                return false;
            }
        }
        flag[curNode] = 2;
        return true;
    }

    int index = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] cur : prerequisites) {
            adjacency.get(cur[1]).add(cur[0]);
        }
        int[] flags = new int[numCourses];
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(!dfs2(adjacency, flags, i,result)) {
                return new int[0];
            }
        }

        return result;
    }

    private boolean dfs2(List<List<Integer>> adjacency,int[] flag,int curNode ,int[] result) {
        if(flag[curNode] == 1) return  false;
        if(flag[curNode] == 2) return  true;
        flag[curNode]  =1;
        List<Integer> curAdjacency = adjacency.get(curNode);
        for (Integer node : curAdjacency) {
            if(!dfs2(adjacency, flag, node, result)) {
                return false;
            }
        }
        flag[curNode] = 2;
        result[index++] = curNode;
        return true;
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for (int[] cur : prerequisites) {
            inDegree[cur[0]]++;
            adjacency.get(cur[1]).add(cur[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            Integer curNode = queue.poll();
            result[index++] = curNode;
            for (Integer node : adjacency.get(curNode)) {
                inDegree[node]--;
                if(inDegree[node] == 0) queue.add(node);
            }
        }
        if(index !=numCourses ) return new int[0];
        return result;
    }
}
