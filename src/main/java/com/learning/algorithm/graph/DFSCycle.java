package com.learning.algorithm.graph;

/**
 * 深度优先搜索检测是否存在环
 */
public class DFSCycle {

    private boolean[] marked;

    private boolean hasCycle;

    public DFSCycle(Graph G) {
        this.marked = new boolean[G.V()];

    }

    /**
     * dfs 判断是否存在环
     * @param G
     * @param v
     * @param u
     */
    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else {
                if (w != u) {
                    this.hasCycle = true;
                }
            }
        }
    }
}
