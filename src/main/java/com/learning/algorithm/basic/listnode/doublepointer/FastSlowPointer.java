package com.learning.algorithm.basic.listnode.doublepointer;

import com.learning.algorithm.basic.listnode.ListNode;

public class FastSlowPointer {

    /**
     * 查找链表中点
     * @param head
     * @return
     */
    public ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while(fast != null && fast.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 查找倒数第N个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode findLastN(ListNode head, int n) {
        ListNode fast = head;
        while(fast != null && n >0) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return null;
        }
        ListNode slow = head;
        while(fast !=null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
