package com.learning.algorithm.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 加权有向图
 */
public class EdgeWeightedDigraph {
    private final int V;
    private  int E;
    private Set<DirectEdge>[] adj;  // 邻接表

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectEdge> edges() {
        Set<DirectEdge> edges = new HashSet<>();
        for (int v = 0; v < V; v++) {
            for (DirectEdge edge: adj[v]) {
                edges.add(edge);
            }
        }
        return edges;
    }
}
