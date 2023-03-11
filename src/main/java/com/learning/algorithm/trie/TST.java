package com.learning.algorithm.trie;

/**
 * 三向单词查找树
 *
 *
 * @param <T>
 */
public class TST<T> {

    private Node<T> root;  // 单词查找树 根节点

    private static class Node<T> {
        private T val;

        char c;

        private Node<T> mid, left, right;
    }

    public T get(String str) {
        Node<T> x = get(root, str, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node<T> get(Node<T> node, String str, int d) {
        if (node == null) {
            return null;
        }
        char ch = str.charAt(d);
        if (ch < node.c) {
            return get(node.right, str, d);
        } else if (ch < node.c) {
            return get(node.left, str, d);
        } else {
            if (d < str.length() - 1) {
                return get(node.mid, str, d + 1);
            } else {
                return node;
            }
        }
    }

    public void put(String str, T value) {
        this.root = put(root, str, 0, value);
    }

    private Node<T> put(Node<T> node, String str, int d, T value) {
        char ch = str.charAt(d);
        if (node == null) {
            node = new Node<T>();
            node.c = ch;
        }
        if (ch < node.c) {
            node.left = put(node.left, str, d, value);
        } else if (ch > node.c) {
            node.right = put(node.right, str, d, value);
        } else {
            if (d < str.length() - 1) {
                node.mid = put(node.mid, str, d + 1, value);
            } else {
                node.val = value;
            }
        }
        return node;
    }

}
