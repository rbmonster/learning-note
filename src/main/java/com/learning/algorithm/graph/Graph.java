package com.learning.algorithm.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 无向图
 */
public class Graph {
    private int V; // 顶点数目
    private int E; // 边的数目

    private Set<Integer>[] adj;  // 邻接表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    /**
     * 无线图 添加边
     *  两个顶点都加入
     * @param v
     * @param w
     */
    public void addEdge(int v,int w) {
        adj[v].add(w);
        adj[w].add(v);
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
