package com.learning.algorithm.unionfind;

/**
 * 联通分量算法
 * quick-find 算法实现
 */
public class QuickUnionUF extends UF {


    public QuickUnionUF(int N) {
        this.count = N;
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }


    @Override
    public int find(int p) {
        return id[p];
    }

    /**
     * quick-union的缺陷为
     *   假设全部节点都连通，那么连接的操作次数为 ～ 近似N2
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (qID == pID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }


    public static void main(String[] args) {
        QuickUnionUF uf = new QuickUnionUF(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);
    }
}
