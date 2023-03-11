package com.learning.algorithm;

/**
 * KMP算法本质上是模拟指针的回退
 * 适用于重复性很高的文本 中查找重复性很高的"模式"(即字符串)
 */
public class KMP {

    private String pat;

    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // 字母表的大小R
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        // j = 1 忽略首字母 表示模式需要右移动一位
        for (int j = 1, X = 0; j < M; j++) {
            for (int c = 0; c < R; c++) {
                // 复制模式失败情况下的值
                dfa[c][j] = dfa[c][X];
            }
            // 设置匹配成功情况下的值, dfa[pat.charAt(j)][j] 总是  j+1 表示匹配上往前进一位
            dfa[pat.charAt(j)][j] = j+1;
            // 更新重启状态
            X = dfa[pat.charAt(j)][X];
        }
    }


    public int search(String txt) {
        int i, j , N = txt.length(), M = pat.length();
        for (i = 0, j = 0;  i < N && j<M ; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i-M; // 找到匹配
        }
        else {
            return N;  // 未找到匹配
        }
    }


    public static void main(String[] args) {
        KMP kmp = new KMP("sadfjweasdfasd");
        kmp.search("qwejriasdnfzxc,mvnz,xmcnvasdf");

    }
}
