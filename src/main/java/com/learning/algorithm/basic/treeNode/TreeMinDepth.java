package com.learning.algorithm.basic.treeNode;


import javafx.util.Pair;

import java.util.LinkedList;

/**
 * <pre>
 * @Description:
 * 树的最小深度
 * </pre>
 *
 * @version v1.0
 * @ClassName: TreeMinDepth
 * @Author: sanwu
 * @Date: 2020/8/9 14:04
 */
public class TreeMinDepth {

    /**
     * 迭代方案
     * 以层序遍历的思想 判断如果是叶节点直接跳出
     * @param root
     * @return
     */
    public int minDepth12(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            stack.add(new Pair(root, 1));
        }

        int current_depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }

//    =====================================================================================================================
    /**
     * 自底而上
     * 注意叶子节点的判断
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

    //==================================================================================================================
    private int minDep = Integer.MAX_VALUE;

    /**
     * 自上而下 最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if (root.left ==null && root.right == null) {
            return 1;
        }
        minDepth(root.left, 2);
        minDepth(root.right, 2);
        return minDep;
    }

    private void minDepth(TreeNode root, int deep) {
        if(root ==null) {
            return;
        }
        if(root.left ==null && root.right == null) {
            minDep = Math.min(deep, minDep);
            return;
        }
        minDepth(root.left,deep+1);
        minDepth(root.right, deep+1);
    }
}
