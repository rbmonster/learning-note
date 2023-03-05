package com.learning.algorithm.graph;

/**
 * 有向图 DFS
 */
public class DirectedDFS {

    private boolean[]marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];

    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }


}
