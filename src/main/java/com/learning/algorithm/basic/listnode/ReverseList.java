package com.learning.algorithm.basic.listnode;

/**
 * <pre>
 * @Description:
 * 迭代法反转链表
 * </pre>
 *
 * @version v1.0
 * @ClassName: ReverseList
 * @Author: sanwu
 * @Date: 2020/8/2 22:36
 */
public class ReverseList {


    /**
     * 迭代法反转链表
     * newHead 表示取到最后一个节点
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head ==null || head.next == null ) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        // 链表反装  假设 head -> 4, 实际 4->5 ,执行下面指令之后 5->4-> null
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代法
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prefixHead = new ListNode(-1);
        while(head !=null) {
            ListNode next = head.next;
            head.next = prefixHead.next;
            prefixHead.next = head;
            head = next;
        }
        return prefixHead.next;
    }
}
