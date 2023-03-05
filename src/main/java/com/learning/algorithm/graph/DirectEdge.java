package com.learning.algorithm.graph;

import java.util.Objects;

/**
 * 加权有向边
 */
public class DirectEdge implements Comparable<DirectEdge>{
    private final int v;
    private final  int w;
    private final double weight; // 权重

    public DirectEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    @Override
    public int compareTo(DirectEdge that) {
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
        DirectEdge edge = (DirectEdge) o;
        return v == edge.v && w == edge.w && Double.compare(edge.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v, w, weight);
    }
}
