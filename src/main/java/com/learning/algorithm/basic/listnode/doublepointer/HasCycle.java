package com.learning.algorithm.basic.listnode.doublepointer;

import com.learning.algorithm.basic.listnode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * @Description:
 * 环形链表
 * </pre>
 *
 * @version v1.0
 * @ClassName: HasCycle
 * @Author: sanwu
 * @Date: 2020/8/2 16:51
 */
public class HasCycle {

    /**
     * 判断是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断环入口的节点
     * 1.第一阶段 寻找是否有环 返回第一次快慢指针的交点，交点为 = 环长度- （环前节点数 % 环长度）
     * 2.第二阶段  使用一个新指针，当新指针和和重合点依次往后遍历重合时，变为入口。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head, slow = head, cross = head;
        boolean hasCyc = false;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCyc = true;
                break;
            }
        }
        if (!hasCyc) {
            return null;
        }
        while (cross != slow) {
            cross = cross.next;
            slow = slow.next;
        }
        return cross;
    }

    /**
     * 使用哈希表的方式快速判断是否有环
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }
}
