package com.learning.algorithm.basic.listNode;

/**
 * <pre>
 * @Description:
 * 链表节点
 * </pre>
 *
 * @version v1.0
 * @ClassName: ListNode
 * @Author: sanwu
 * @Date: 2020/8/2 16:40
 */
public class ListNode {
      public int val;
      public ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }

    /**
     * 迭代访问
     * @param head
     */
    void traverse(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
        // 迭代访问 p.val
        }
    }

    /**
     * 递归访问
     * @param head
     */
    void traverse1(ListNode head) {
        // 递归访问 head.val
        traverse1(head.next);
    }
}
