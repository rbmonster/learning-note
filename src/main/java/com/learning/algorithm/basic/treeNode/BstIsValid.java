package com.learning.algorithm.basic.treeNode;

import com.learning.algorithm.basic.treeNode.TreeNode;

/**
 * <pre>
 * @Description:
 * 验证是否为二叉搜索树
 * </pre>
 *
 * @version v1.0
 * @ClassName: IsVaild
 * @Author: sanwu
 * @Date: 2020/8/8 23:23
 */
public class BstIsValid {


    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    /**
     * 通过 上下限的界定 来判断节点是否符合
     * @param root
     * @param lower
     * @param upper
     * @return
     */
    private boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (root == null ) {
            return true;
        }
        if( lower!= null &&root.val<=lower) return false;
        if( upper!=null && root.val>=upper) return false;
        if(!helper(root.left, lower, root.val)) return false;
        if(!helper(root.right, root.val, upper)) return false;
        return true;
    }

    /**
     * 判断是否为平衡的二叉树
     * 常规方法为求两个子树的高度<1,但是会造成多次的重复计算
     *
     * 该方法为自底而上的计算方式，通过先判断子树的高度是否为平衡二叉树，并返回高度+1
     * 进而依次向上判断是否为平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if(left == -1) return -1;
        int right = recur(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
