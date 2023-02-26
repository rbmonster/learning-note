package com.learning.algorithm.basic.treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * @Description:
 * 树的前序遍历
 * </pre>
 *
 * @version v1.0
 * @ClassName: Preorder
 * @Author: sanwu
 * @Date: 2020/8/8 17:23
 */
public class PreOrder {
    public static void main(String[] args) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(new TreeNode(1));
        stack.push(new TreeNode(2));
        stack.push(new TreeNode(3));
        stack.push(new TreeNode(4));
        stack.push(new TreeNode(5));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack = new LinkedList<>();
        stack.add(new TreeNode(1));
        stack.add(new TreeNode(2));
        stack.add(new TreeNode(3));
        stack.add(new TreeNode(4));
        stack.add(new TreeNode(5));
        System.out.println(stack.pollLast());
        System.out.println(stack.pollLast());
    }

    /**
     * 基于递归的遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    public void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /**
     * 基于迭代的遍历
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal1(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            //每次获得第一个元素，理解成 栈的pop就行。
            TreeNode node = stack.pollLast();
            output.add(node.val);
            // 由于栈的属性 右节点先入栈
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }
}
