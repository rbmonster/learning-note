package com.learning.algorithm.basic.treeNode;

/**
 * <pre>
 * @Description:
 * 数组转成二叉平和二叉树
 * </pre>
 *
 * @version v1.0
 * @ClassName: SortArrayToBST
 * @Author: sanwu
 * @Date: 2020/8/8 23:51
 */
public class SortArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0 , nums.length-1);
    }

    /**
     * 每次使用中点来构造二叉树的节点。
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode helper(int[] nums, int left, int right) {
        if( left >right) {
            return null;
        }
        int mid = (right+ left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid -1);
        root.right = helper(nums, mid +1, right);
        return root;
    }
}
