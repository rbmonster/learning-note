package com.learning.algorithm.basic.treeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * @Description:
 * 构建二叉树
 * </pre>
 *
 * @version v1.0
 * @ClassName: BuildTree
 * @Author: sanwu
 * @Date: 2020/8/8 23:00
 */
public class BuildTree {

    Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    /**
     * 根据前序遍历和中序遍历构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTreeByInorderIndex(preorder, 0, n - 1, 0, n - 1);
    }

    /**
     * 通过前序遍历的根节点位置，确定中序遍历的根节点位置，以及位置长度
     *
     * @param preorder
     * @param preLeft
     * @param preRight
     * @param inLeft
     * @param inRight
     * @return
     */
    public TreeNode buildTreeByInorderIndex(int[] preorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        TreeNode root = new TreeNode(preorder[preLeft]);
        int inRootIndex = inorderIndexMap.get(preorder[preLeft]);
        int leftTreeLen = inRootIndex - inLeft;
        root.left = buildTreeByInorderIndex(preorder, preLeft + 1, preLeft + leftTreeLen, inLeft, inRootIndex - 1);
        root.right = buildTreeByInorderIndex(preorder, preLeft + leftTreeLen + 1, preRight, inRootIndex + 1, inRight);
        return root;
    }

    // ================================================================================================================================

    Map<Integer, Integer> indexMap = new HashMap<>();

    /**
     * 根据中序遍历和 后序遍历构建二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTreeByIndex(postorder, 0, len - 1, 0, len - 1);
    }

    /**
     * 根据后序遍历的根节点位置，确定中序遍历二叉树的子树长度
     * 递归的传入 中序遍历中的左子树和右子树的位置
     *
     * @param postorder
     * @param inLeft
     * @param inRight
     * @param postLeft
     * @param postRight
     * @return
     */
    private TreeNode buildTreeByIndex(int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postRight]);
        int rootIndex = indexMap.get(postorder[postRight]);
        int leftLen = rootIndex - inLeft;
        root.left = buildTreeByIndex(postorder, inLeft, rootIndex - 1, postLeft, postLeft + leftLen - 1);
        root.right = buildTreeByIndex(postorder, rootIndex + 1, inRight, postLeft + leftLen, postRight - 1);
        return root;
    }
}
