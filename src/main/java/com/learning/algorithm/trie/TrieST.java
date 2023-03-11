package com.learning.algorithm.trie;

import java.util.Deque;
import java.util.LinkedList;

/**
 * R向字典树
 *  R 越大占用的空间越大
 *  (处理由大小为R的字母表构造的N个字符串, w为单词平均长度)
 *  一棵R向单词查找树的链接总数在RN ～ RNw
 * @param <T>
 */
public class TrieST<T> {

    private static int R = 256;  // 基数
    private Node<T> root;  // 单词查找树 根节点

    private static class Node<T> {
        private T val;
        private Node<T>[] next = new Node[R];
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public T get(String key) {
        Node<T> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node<T> get(Node<T> x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * 添加
     *
     * @param key
     * @param val
     */
    public void put(String key, T val) {
        root = put(root, key, val, 0);
    }

    private Node<T> put(Node<T> node, String key, T val, int d) {
        if (node == null) {
            node = new Node<>();
        }
        if (d == key.length()) {
            node.val = val;
            return node;
        }
        char c = key.charAt(d);
        node.next[c] = put(node.next[c], key, val, d + 1);
        return node;
    }


    public Iterable<String> keys() {
        return keyWithPrefix("");
    }

    /**
     * 前缀匹配
     *
     * @param pre
     * @return
     */
    public Iterable<String> keyWithPrefix(String pre) {
        Deque<String> q = new LinkedList<>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    public void collect(Node<T> x, String pre, Deque<String> q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.offer(pre);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    /**
     * 最长前缀
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node<T> x, String s, int d, int length) {
        if (x == null) {
            return length;
        }
        if (x.val != null) {
            length = d;
        }
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }
}
