package com.learning.algorithm.unionfind;

/**
 * 联通分量算法
 *  quick-find 算法实现
 */
public abstract class UF {

    protected int[] id;
    protected int count;


    public int count() {
        return this.count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    abstract int find(int p);

    abstract void union(int p, int q);

}
