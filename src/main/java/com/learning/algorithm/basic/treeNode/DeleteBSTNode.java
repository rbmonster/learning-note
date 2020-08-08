package com.learning.algorithm.basic.treeNode;

/**
 * <pre>
 * @Description:
 * 二叉搜索树 删除节点
 * </pre>
 *
 * @version v1.0
 * @ClassName: DeleteBSTNode
 * @Author: sanwu
 * @Date: 2020/8/8 23:32
 */
public class DeleteBSTNode {

    /**
     * 删除二叉搜索树的节点
     * 这个分为三种情况：
     * 1. 如果目标节点没有子节点，我们可以直接移除该目标节点。
     * 2. 如果目标节只有一个子节点，我们可以用其子节点作为替换。
     * 3. 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root ==null) {
            return null;
        }
        if(root.val > key) root.left = deleteNode(root.left, key);
        else if(root.val <key) root.right = deleteNode(root.right, key);
        else {
            if(root.left == null && root.right == null) {
                root = null;
            }
            else if(root.right !=null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val );
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left ,root.val);
            }
        }
        return root;
    }

    /**
     * 这个方法要熟记
     * 查找root的后继节点
     * Successor 代表的是中序遍历序列的下一个节点。
     * 即比当前节点大的最小节点，简称后继节点。 先取当前节点的右节点，然后一直取该节点的左节点，直到左节点为空，则最后指向的节点为后继节点。
     * @param root
     * @return
     */
    private int successor(TreeNode root) {
        root = root.right;
        while(root.left != null) root = root.left;
        return root.val;
    }

    /**
     * 寻找root的前继节点
     * Predecessor 代表的是中序遍历序列的前一个节点。
     * 即比当前节点小的最大节点，简称前驱节点。先取当前节点的左节点，然后取该节点的右节点，直到右节点为空，则最后指向的节点为前驱节点。

     * @param root
     * @return
     */
    private int predecessor(TreeNode root) {
        root = root.left;
        while(root.right != null) root = root.right;
        return root.val;
    }
}
