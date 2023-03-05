package com.learning.algorithm.unionfind;

public class WeightedQuickUnionUF extends UF{

    private int[] sz;

    public WeightedQuickUnionUF(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }
    @Override
    int find(int p) {
        // 跟随链接找到根节点
        while (p!=id[p]) {
            p = id[p];
        }
        return p;
    }


    @Override
    void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        // 将小树的根节点链接到大树的根节点
        if(sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        }else {
            id[j] = i;
            sz[i] +=sz[j];
        }
        count--;
    }
}
