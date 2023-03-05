package com.learning.algorithm.graph;

import java.util.Objects;

/**
 * 加权无向边
 */
public class Edge implements Comparable<Edge>{

     private final int v;
     private final  int w;
     private final double weight; // 权重

     public Edge(int v, int w, double weight) {
         this.v = v;
         this.w = w;
         this.weight = weight;
     }

     public double weight() {
         return weight;
     }

    /**
     * 两边其中一个端点之一
     * @return
     */
    public int either() {
         return v;
     }

    /**
     * 获取边 顶点vertex 连通的另一个顶点
     * @param vertex
     * @return
     */
     public int other(int vertex) {
         if (vertex == v) {
             return w;
         } else if (vertex == w) {
             return v;
         } else {
             throw new RuntimeException("Inconsistent edge");
         }
     }

    @Override
    public int compareTo(Edge that) {
         if (this.weight() <that.weight()) {
             return -1;
         } else if (this.weight() > that.weight()) {
             return 1;
         } else {
             return 0;
         }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return v == edge.v && w == edge.w && Double.compare(edge.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, w, weight);
    }
}
