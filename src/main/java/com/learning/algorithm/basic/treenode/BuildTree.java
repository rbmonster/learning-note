package com.learning.algorithm.basic.treenode;

import java.util.*;

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


    // ================================================================================================================================

    Map<Integer, Integer> postIndexMap = new HashMap<>();

    /**
     * 前序后序构造二叉树
     * 1. 前序后序如何区分左右子树？
     *    主要通过preorder[index+1]节点，在后序中的位置确定左右子树的大小
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for(int i =0;i< postorder.length;i++) {
            postIndexMap.put(postorder[i], i);
        }
        int len = preorder.length;
        return buildTree(preorder, 0,len-1, 0, len-1);
    }

    public TreeNode buildTree(int[] preorder, int pl, int pr, int pol, int por) {
        if(pl>pr) {
            return null;
        }
        TreeNode node =  new TreeNode(preorder[pl]);
        if(pl == pr) {
            return node;
        }
        int index = postIndexMap.get(preorder[pl+1]);
        int len = index - pol;
        node.left = buildTree(preorder, pl+1, pl+1+len, pol, index);
        node.right = buildTree(preorder, pl+1+len+1, pr, index+1, por-1);
        return node;
    }

    // ================================================================================================================================

    /**
     * 非递归实现
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode build(int[] preorder, int[] inorder) {
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        Deque<BuildRequest> deque= new LinkedList<>();
        List<TreeNode> list = new LinkedList<>();

        deque.offer(new BuildRequest(0,len-1, 0,len-1));
        while(!deque.isEmpty()) {
            BuildRequest request = deque.poll();
            int pl = request.pl;
            int pr = request.pr;
            int il = request.il;
            int ir = request.ir;
            if (pl>pr) {
                list.add(null);
                System.out.println("--");
                continue;
            }
            TreeNode root = new TreeNode(preorder[pl]);
            System.out.println(root.val);
            list.add(root);
            int inRootIndex = indexMap.get(preorder[pl]);
            int inLen = inRootIndex - il;
            deque.offer(new BuildRequest(pl+inLen+1, pr,inRootIndex+1, ir));
            deque.offer(new BuildRequest(pl+1, pl+inLen, il, inRootIndex-1));
        }
        int child = 0;
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            TreeNode treeNode = list.get(i);
            if (treeNode == null) {
                continue;
            }
            if (child+1< list.size()&&list.get(child+1)!= null) {
                treeNode.left = list.get(child+1);
            }
            if (child+2< list.size()&&list.get(child+2)!= null) {
                treeNode.right = list.get(child+2);
            }
            child = child+2;
        }

        return list.get(0);
    }
}

class BuildRequest {
    int pl;
    int pr;
    int il;
    int ir;
    public BuildRequest(int pl, int pr, int il, int ir) {
        this.pl = pl;
        this.pr = pr;
        this.il = il;
        this.ir = ir;
    }
}
