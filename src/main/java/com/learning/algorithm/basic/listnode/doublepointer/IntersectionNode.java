package com.learning.algorithm.basic.listnode.doublepointer;

import com.learning.algorithm.basic.listnode.ListNode;

/**
 * <pre>
 * @Description:
 * 相交链表
 *
 *  法二： 使用hash表存储 需要空间复杂度 O(m) 时间复杂度 O(m+n)
 * </pre>
 *
 * @version v1.0
 * @ClassName: IntersectionNode
 * @Author: sanwu
 * @Date: 2020/8/2 17:34
 */
public class IntersectionNode {

    /**
     * 快慢指针 + 两链表叠加
     *  因为链表队尾一定是公共的
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode pointA = headA, pointB = headB;
        //相同的条件 要么出现交点 要么到队尾都是null
        while (pointA != pointB) {
            pointA = pointA == null ? headB : pointA.next;
            pointB = pointB == null ? headA : pointB.next;
        }
        return pointA;
    }
}
