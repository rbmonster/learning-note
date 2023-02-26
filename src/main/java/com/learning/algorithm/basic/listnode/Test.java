package com.learning.algorithm.basic.listnode;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Test
 * @Author: sanwu
 * @Date: 2020/8/4 23:34
 */
public class Test {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        listNode1.next = listNode2;
        ListNode res = new Test().reverseBetween(listNode1, 2,3);
        System.out.println(res);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode res = new ListNode(0);
        ListNode p1 = head, p2 = res, p3 = res;
        int position = 1;
        while(p1 != null) {
            if( position >= m && position<=n) {
                ListNode tmp = p1.next;
                p1.next = p2.next;
                p2.next = p1;
                p1 = tmp;
            } else {
                p2 = p3;
                p2.next = p1;
                p1 = p1.next;
            }
            p3 = p3.next;
            ++position;
        }
        return res.next;
    }
}
