package com.learning.collection;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 * @Description:
 * 基础的工具方法
 * </pre>
 *
 * @version v1.0
 * @ClassName: CollectionDemo
 * @Author: sanwu
 * @Date: 2020/7/14 21:14
 */
public class ToolDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // 填充容器
        list = new ArrayList<>(Collections.nCopies(4, new Integer(12)));
        Collections.fill(list, new Integer(123));
        // 排序
        Collections.sort(list); // In-place sort
        // 随机打乱
        Collections.shuffle(list, new Random()); // Mix it up
        List<Integer> sub = Arrays.asList(list.get(1), list.get(4));
        Collections.addAll(list, 11, 12, 13, 14, 15);
        Integer[] array = (Integer[]) list.toArray(new Integer[]{});
        // 取交集
        list.retainAll(new ArrayList<>());
        List<Integer> subList = list.subList(0,12);

        Collections.max(list);
        Collections.min(list);
        Collection<Integer> list2 = Collections.unmodifiableCollection(list);
        List list1 = Collections.synchronizedList(list);
        // 二分查找
        Arrays.binarySearch(list.toArray(), 11);
        // 交换位置
        Collections.swap(list, 1, 2);
        // 当两集合没有共同元素返回 true
        Collections.disjoint(list1, list2);
        // 不可变的
        Collections.emptyList();
        Collections.singletonList(11);

        Collections.sort(list);
        Collections.sort(list, (o1, o2) -> 0);


    }
}
