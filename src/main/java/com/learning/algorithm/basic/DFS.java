package com.learning.algorithm.basic;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DFS
 * @Author: sanwu
 * @Date: 2020/8/13 0:08
 */
public class DFS {

    /**
     * DFS 使用栈的调用方法
     * @param root
     * @param target
     * @return
     */
    boolean DFS(Node root, int target) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> s = new Stack<>();
        s.push(root);
        visited.add(root);
        while (!s.isEmpty()) {
            Node cur = s.peek();
            if(cur.val == target) {
                return true;
            }
            s.pop();
            for (Node next : cur.neighbors) {
                if (!visited.contains(next)) {
                    s.push(next);
                    visited.add(next);
                }
            }
        }
        return false;
    }
}
