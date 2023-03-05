package com.learning.algorithm.graph;

/**
 * 深度优先搜索-二分法
 */
public class DFSTwoColor {

    private boolean[] marked;

    private boolean[] color;
    private boolean isTwoColor = true;

    public DFSTwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {

            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                // 节点已经访问过，但是颜色与连通节点颜色相同，说明不是二分图
                this.isTwoColor = false;
            }
        }
    }
}
