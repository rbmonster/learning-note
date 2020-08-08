package com.learning.algorithm.basic.treeNode;

/**
 * <pre>
 * @Description:
 * 树的最大深度
 * </pre>
 *
 * @version v1.0
 * @ClassName: TreeMaxDepth
 * @Author: sanwu
 * @Date: 2020/8/8 22:54
 */
public class TreeMaxDepth {

    /**
     * 自上而下的递归遍历
     * 需要一个局部变量 ， 同时需要把当前的树深度进行传递
     */
    private int answer;		// don't forget to initialize answer before call maximum_depth
    private void maximum_depth(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            answer = Math.max(answer, depth);
        }
        maximum_depth(root.left, depth + 1);
        maximum_depth(root.right, depth + 1);
    }

    /**
     * 自底而上的遍历
     * 从最低层依次传递树的深度
     * @param root
     * @return
     */
    public int maximum_depth(TreeNode root) {
        if (root == null) {
            return 0;                                   // return 0 for null node
        }
        int left_depth = maximum_depth(root.left);
        int right_depth = maximum_depth(root.right);
        return Math.max(left_depth, right_depth) + 1;	// return depth of the subtree rooted at root
    }
}
