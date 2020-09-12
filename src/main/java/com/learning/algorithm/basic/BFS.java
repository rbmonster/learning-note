package com.learning.algorithm.basic;

import java.util.*;

/**
 * <pre>
 * @Description:
 * 队列结合广度优先搜索的模板
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: BFS
 * @Author: sanwu
 * @Date: 2020/8/12 22:47
 */
public class BFS {

    /**
     * 通用模板
     * @param root
     * @param target
     * @return
     */
    int BFS(Node root, Node target) {
        Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
        int step = 0;       // number of steps neeeded from root to current node
        // initialize
        queue.add(root);
        // BFS
        while (!queue.isEmpty()) {
            step = step + 1;
            // iterate the nodes which are already in the queue
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.poll();
                if(cur == target) {
                    return step;
                }
                for (Node next : cur.neighbors) {
                    queue.add(next);
                }
            }
        }
        return -1;          // there is no path from root to target
    }

    /**
     * 通用模板加上访问节点
     * @param root
     * @param target
     * @return
     */
    int BFS2(Node root, Node target) {
        Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
        Set<Node> used = new HashSet<>();     // store all the used nodes
        int step = 0;       // number of steps neeeded from root to current node
        // initialize
        queue.add(root);
        used.add(root);
        // BFS
        while (!queue.isEmpty()) {
            step = step + 1;
            // iterate the nodes which are already in the queue
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Node cur = queue.poll();
                if(cur == target) {
                    return step;
                }
                for (Node next : cur.neighbors) {
                    if (!used.contains(next)) {
                        queue.add(root);
                        used.add(root);
                    }
                }
            }
        }
        return -1;          // there is no path from root to target
    }
}

class Node{
    int val;
    Node next;
    List<Node> neighbors;
}
