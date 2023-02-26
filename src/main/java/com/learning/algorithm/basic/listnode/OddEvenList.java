package com.learning.algorithm.basic.listnode;

/**
 * <pre>
 * @Description:
 * 奇偶链表
 * </pre>
 *
 * @version v1.0
 * @ClassName: OddEvenList
 * @Author: sanwu
 * @Date: 2020/8/3 0:37
 */
public class OddEvenList {

    /**
     * 常规方法
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if( head == null || head.next ==null) {
            return head;
        }
        ListNode oriEven = head.next;
        ListNode oddPoint = head,evenPoint = head.next;
        while(evenPoint != null && evenPoint.next !=null) {
            oddPoint.next = evenPoint.next;
            oddPoint = oddPoint.next;
            evenPoint.next = oddPoint.next;
            evenPoint = evenPoint.next;
        }
        oddPoint.next = oriEven;
        return head;
    }
}
