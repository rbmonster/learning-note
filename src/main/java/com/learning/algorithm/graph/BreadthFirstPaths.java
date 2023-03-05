package com.learning.algorithm.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 广度优先搜索
 */
public class BreadthFirstPaths {

    private boolean[] marked;
    private  int[] edgeTo; // 边

    private  int s;  // 起点

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Deque<Integer> queue= new LinkedList<>();
        queue.offer(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w: G.adj(v)) {
                edgeTo[w] = v;
                marked[w]= true;
                queue.offer(w);
            }
        }
    }


    public boolean hasPathTo(int v) {
        return marked[v];
    }


    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x !=s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
