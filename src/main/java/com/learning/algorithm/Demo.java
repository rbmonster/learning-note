package com.learning.algorithm;

import com.learning.algorithm.basic.treeNode.TreeNode;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Demo
 * @Author: sanwu
 * @Date: 2020/8/2 15:25
 */
public class Demo {

    public static void main(String[] args) {
        TreeNode node1 =new TreeNode(5);
        TreeNode node2 =new TreeNode(13);
        TreeNode node3 =new TreeNode(2);
        node1.left = node3;
        node1.right = node2;
        System.out.println(new Demo().convertBST(node1));
    }

    public TreeNode convertBST(TreeNode root) {
        helper(root, new Integer(0));
        return root;
    }

    private void helper(TreeNode root, Integer upper) {
        if (root == null) {
            return;
        }
        helper(root.right, upper);
        upper = upper + root.val;
        if(root.val<= upper) {
            root.val = upper;
        }
        helper(root.left, upper);
    }
}
