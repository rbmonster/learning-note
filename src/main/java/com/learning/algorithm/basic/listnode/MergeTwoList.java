package com.learning.algorithm.basic.listnode;

/**
 * <pre>
 * @Description:
 * 合并两个有序链表
 * </pre>
 *
 * @version v1.0
 * @ClassName: MergeTwoList
 * @Author: sanwu
 * @Date: 2020/8/4 15:27
 */
public class MergeTwoList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if( l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode newHead = new ListNode(-1);
        ListNode newPoint = newHead;
        while( p1!=null && p2 !=null) {
            if( p1.val <p2.val ) {
                newPoint.next = p1;
                newPoint = newPoint.next;
                p1 = p1.next;
            } else {
                newPoint.next = p2;
                newPoint = newPoint.next;
                p2 = p2.next;
            }
        }
        while(p1 != null) {
            newPoint.next = p1;
            newPoint = newPoint.next;
            p1 =p1.next;
        }
        while(p2 != null) {
            newPoint.next = p2;
            newPoint = newPoint.next;
            p2 =p2.next;
        }
        return newHead.next;
    }
}
