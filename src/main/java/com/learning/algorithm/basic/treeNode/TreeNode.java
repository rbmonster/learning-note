package com.learning.algorithm.basic.treeNode;

/**
 * <pre>
 * @Description:
 * 树节点
 * </pre>
 *
 * @version v1.0
 * @ClassName: TreeNode
 * @Author: sanwu
 * @Date: 2020/8/8 17:22
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
