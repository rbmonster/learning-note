package com.learning.algorithm.basic.listNode;

/**
 * <pre>
 * @Description:
 * 移除链表元素
 * </pre>
 *
 * @version v1.0
 * @ClassName: RemoveElement
 * @Author: sanwu
 * @Date: 2020/8/3 0:10
 */
public class RemoveElement {
    /**
     * 移除链表元素
     * 如下 移除1
     * 1->1->3->5->1->1
     * 使用哨兵 作为前置的访问访问点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }
}
