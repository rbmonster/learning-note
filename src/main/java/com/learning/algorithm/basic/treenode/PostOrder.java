package com.learning.algorithm.basic.treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 后序遍历
 * </pre>
 *
 * @version v1.0
 * @ClassName: PostOrder
 * @Author: sanwu
 * @Date: 2020/8/8 18:08
 */
public class PostOrder {

    /**
     * 迭代版本
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            // 关键点 每次添加都添加到输出的第一个
            output.addFirst(node.val);
            // 关键点2 左节点先入栈
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }

    /**
     * 后序递归版本
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        preorder(root.left, list);
        preorder(root.right, list);
        list.add(root.val);
    }


}
