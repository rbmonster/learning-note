package com.learning.algorithm.basic.treeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * @Description:
 * 树的中序遍历
 * </pre>
 *
 * @version v1.0
 * @ClassName: InOrder
 * @Author: sanwu
 * @Date: 2020/8/8 17:55
 */
public class InOrder {

    /**
     * 中序遍历 基于递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        preorder(root.left, list);
        list.add(root.val);
        preorder(root.right, list);
    }


    /**
     * 基于迭代的中序遍历
     * 1. 根据根节点把所有的左节点全部入栈，
     * 2. 出栈后的节点均为左节点，因此中序的下一个节点为右节点的左节点，继续找右节点的左节点入栈
     *
     * @param root
     * @return
     */
    public List < Integer > inorderTraversal2(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack< >();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

}
