package com.learning.algorithm.basic.treeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * @Description:
 * 层序遍历
 * </pre>
 *
 * @version v1.0
 * @ClassName: LevelOrder
 * @Author: sanwu
 * @Date: 2020/8/8 18:11
 */
public class LevelOrder {

    /**
     * 使用队列辅助 记录层序的顺序
     * 每次迭代开始时，记录队列个数，表示当层的元素个数。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            List<Integer> curList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                curList.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            res.add(curList);
        }

        return res;
    }

    /**
     * 层序遍历 递归版本
     *  + 锯齿形输出
     *  本质为先序遍历，通过传入层数，实现每层的顺序输出
     * @param node
     * @param level
     * @param results
     */
        protected void DFS(TreeNode node, int level, List<List<Integer>> results) {
            if (level >= results.size()) {
                LinkedList<Integer> newLevel = new LinkedList<Integer>();
                newLevel.add(node.val);
                results.add(newLevel);
            } else {
                // 锯齿形输出判断
                if (level % 2 == 0)
                    results.get(level).add(node.val);
                else
                    results.get(level).add(0, node.val);
            }

            if (node.left != null) DFS(node.left, level + 1, results);
            if (node.right != null) DFS(node.right, level + 1, results);
        }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        DFS(root, 0, results);
        return results;
    }
}
