package com.learning.algorithm.basic.listnode;

/**
 * <pre>
 * @Description:
 * 交换链表两两之间的元素
 * </pre>
 *
 * @version v1.0
 * @ClassName: SwapPairs
 * @Author: sanwu
 * @Date: 2020/8/4 17:42
 */
public class SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 =head, p2 = head.next;
        ListNode resHead= new ListNode(-1);
        ListNode p3 = resHead;
        while(p2 != null) {
            ListNode tmp = p2.next;
            p2.next = p1;
            p1.next =  tmp;
            p3.next = p2;
            p1 = tmp;
            if( tmp == null) {
                break;
            }
            p2 = tmp.next;
            p3 = p3.next.next;
        }
        return resHead.next;
    }
}
