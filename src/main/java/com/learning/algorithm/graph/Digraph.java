package com.learning.algorithm.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 有向图
 */
public class Digraph {
    private final int V; // 顶点数目
    private int E; // 边的数目

    private Set<Integer>[] adj;  // 邻接表

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    /**
     * 有向图添加边
     * @param v
     * @param w
     */
    public void addEdge(int v,int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V(){
        return V;
    }

    public int E() {
        return E;
    }


}
