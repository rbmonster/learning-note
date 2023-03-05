package com.learning.algorithm.graph;

import java.util.Stack;

/**
 * 深度优先搜索
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
    private  int s;  // 起点

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * 是否与起点s 连通
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 返回路径
     * @param v
     * @return
     */
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
