<a name="index">**Index**</a>

<a href="#0">算法</a>  
&emsp;<a href="#1">1. 哈希表</a>  
&emsp;<a href="#2">2. 数组与字符串</a>  
&emsp;&emsp;<a href="#3">2.1. 数组</a>  
&emsp;&emsp;<a href="#4">2.2. 字符串</a>  
&emsp;<a href="#5">3. 链表</a>  
&emsp;<a href="#6">4. 队列</a>  
&emsp;&emsp;<a href="#7">4.1. 广度优先搜索(BFS)</a>  
&emsp;&emsp;<a href="#8">4.2. 单调队列</a>  
&emsp;<a href="#9">5. 栈</a>  
&emsp;&emsp;<a href="#10">5.1. 深度优先搜索(DFS)</a>  
&emsp;&emsp;<a href="#11">5.2. 单调栈</a>  
&emsp;<a href="#12">6. BFS 与 DFS</a>  
&emsp;&emsp;<a href="#13">6.1. 拓扑排序</a>  
&emsp;&emsp;<a href="#14">6.2. 并查集</a>  
&emsp;<a href="#15">7. 递归</a>  
&emsp;&emsp;<a href="#16">7.1. 递归三要素</a>  
&emsp;&emsp;<a href="#17">7.2. 递归的思想</a>  
&emsp;<a href="#18">8. 树</a>  
&emsp;&emsp;<a href="#19">8.1. 二叉树</a>  
&emsp;&emsp;&emsp;<a href="#20">8.1.1. 树的遍历</a>  
&emsp;&emsp;&emsp;<a href="#21">8.1.2. 递归思想</a>  
&emsp;&emsp;&emsp;<a href="#22">8.1.3. 构造及修改二叉树问题</a>  
&emsp;&emsp;&emsp;<a href="#23">8.1.4. 公共祖先问题</a>  
&emsp;&emsp;<a href="#24">8.2. 二叉搜索树</a>  
&emsp;&emsp;<a href="#25">8.3. 算法</a>  
&emsp;&emsp;&emsp;<a href="#26">8.3.1. 树的遍历</a>  
&emsp;&emsp;&emsp;<a href="#27">8.3.2. 树的属性</a>  
&emsp;&emsp;&emsp;<a href="#28">8.3.3. 子树问题</a>  
&emsp;&emsp;&emsp;<a href="#29">8.3.4. 树的高度</a>  
&emsp;&emsp;&emsp;<a href="#30">8.3.5. 树的构建与修改</a>  
&emsp;&emsp;&emsp;<a href="#31">8.3.6. 树的基本操作</a>  
&emsp;&emsp;&emsp;<a href="#32">8.3.7. 公共祖先问题</a>  
&emsp;<a href="#33">9. 回溯</a>  
&emsp;&emsp;<a href="#34">9.1. 伪代码模版</a>  
&emsp;&emsp;&emsp;<a href="#35">9.1.1. 回溯三部曲</a>  
&emsp;&emsp;&emsp;<a href="#36">9.1.2. startIndex使用</a>  
&emsp;&emsp;<a href="#37">9.2. 问题场景</a>  
&emsp;&emsp;<a href="#38">9.3. 重复问题</a>  
&emsp;&emsp;<a href="#39">9.4. 组合问题</a>  
&emsp;&emsp;<a href="#40">9.5. 切割问题</a>  
&emsp;&emsp;<a href="#41">9.6. 排列问题</a>  
&emsp;&emsp;<a href="#42">9.7. 子集问题</a>  
&emsp;<a href="#43">10. 贪心</a>  
&emsp;&emsp;<a href="#44">10.1. 指针与区间局部最优</a>  
&emsp;&emsp;<a href="#45">10.2. 区间问题</a>  
&emsp;&emsp;<a href="#46">10.3. 其他</a>  
&emsp;<a href="#47">11. 动态规划</a>  
&emsp;&emsp;<a href="#48">11.1. 基本思想</a>  
&emsp;&emsp;<a href="#49">11.2. 相关问题</a>  
&emsp;&emsp;<a href="#50">11.3. 字符串问题</a>  
&emsp;&emsp;&emsp;<a href="#51">11.3.1. 字符操作</a>  
&emsp;&emsp;&emsp;<a href="#52">11.3.2. 子序列问题</a>  
&emsp;&emsp;&emsp;<a href="#53">11.3.3. 子数组问题</a>  
&emsp;&emsp;&emsp;<a href="#54">11.3.4. 回文问题</a>  
&emsp;&emsp;<a href="#55">11.4. 股票问题</a>  
&emsp;&emsp;<a href="#56">11.5. 背包问题</a>  
&emsp;&emsp;&emsp;<a href="#57">11.5.1. 常见求解方式及疑难点</a>  
&emsp;&emsp;&emsp;<a href="#58">11.5.2. 典型背包问题</a>  
&emsp;&emsp;&emsp;<a href="#59">11.5.3. 背包场景问题</a>  
&emsp;&emsp;<a href="#60">11.6. 扔鸡蛋问题</a>  
&emsp;<a href="#61">12. 二分法</a>  
&emsp;<a href="#62">13. 前缀树</a>  
&emsp;<a href="#63">14. 滑动窗口</a>  
&emsp;<a href="#64">15. TODO 二进制应用</a>  
&emsp;<a href="#65">16. 常用操作</a>  
&emsp;&emsp;<a href="#66">16.1. Kanade 算法</a>  
# <a name="0">算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">哈希表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
哈希表的关键思想是使用哈希函数将键映射到存储桶。


HashMap常见操作
```java

public class Solution{
    
    public static void main(String[] args ) {

      Map<Integer, Integer> hashmap = new HashMap<>();
      // 2. insert a new (key, value) pair 
      //putIfAbsent()保存数据的时候，如果该链表中保存的有相同key的值，那么就不会对我们当前的value进行保存
      hashmap.putIfAbsent(0, 0);
      hashmap.putIfAbsent(2, 3);
      hashmap.putIfAbsent(1,4);
      hashmap.putIfAbsent(2,4);
      int mer = hashmap.merge(1,6,(v1, v2) -> v1+v2);
      //output 1 ->8 
      int value  =12;
      int key2 = map.computeIfAbsent(2,k -> value);
      int key3 = map.computeIfAbsent(3,k -> value);
      // output key2 = 4, key3 = 12
      int res = map.computeIfPresent(3,(key, oldVal) -> oldVal-1);
      //output res = 11
      int result1 = map.compute(3,(key, oldValue) -> oldValue-10);
      //output 10
      int result = map.compute(4,(key, oldValue) -> oldValue-10);
      //cause error nullPoint,key 4 not exist


      map.put(1, count.getOrDefault(1, 0) + 1);
      // 取1的值，如果存在则取value 否则默认为0、
    }
}
```
哈希接口判断元素存在：
- [字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)

使用哈希映射的场景：
- [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/)
- [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate-ii/)
- [剑指 Offer 50. 第一个只出现一次的字符](https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)
- [寻找重复的子树（hash表与树的结合）](https://leetcode-cn.com/problems/find-duplicate-subtrees/)
    
利用哈希表O(1)特性查找：
- [两数之和](https://leetcode-cn.com/problems/two-sum/)
- [最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/)

## <a name="2">数组与字符串</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="3">数组</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 针对于数组的索引问题，常规的操作就是用指针、搜索、hash表问题解决
- [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)
- [寻找数组的中心索引](https://leetcode-cn.com/problems/find-pivot-index/)
- [对角线遍历](https://leetcode-cn.com/problems/diagonal-traverse/)


### <a name="4">字符串</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
公共前缀问题
- [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/)
> 分治法、二分法
  
回文问题（包含子串与子序列问题）
- [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

双指针问题
- [反转字符串](https://leetcode-cn.com/problems/reverse-string/)
- [两数之和 II - 输入有序数组（最基础问题） ](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

快慢指针：
- 移除数组
- 移动零

- [反转单词顺序](https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/)

## <a name="5">链表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
链表双指针：
- [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)
- [环形链表2](https://leetcode-cn.com/problems/linked-list-cycle-ii/)
- [相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)
- [剑指 Offer 22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)
- [旋转链表](https://leetcode-cn.com/problems/rotate-list/)
- [奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/)

虚拟节点：
- [移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/)


经典问题：

- [剑指 Offer 24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/) 
> 栈、头插法、递归
- [合并两有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)
- [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)

  
反转链表递归写法:
```java
public class Solution{

  public ListNode reverseList(ListNode head) {
    if(head ==null || head.next == null ) {
      return head;
    }
    ListNode res = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return res;
  }
}
```



- [合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)




## <a name="6">队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

队列是典型的 FIFO 数据结构。
- 插入（insert）操作也称作入队（enqueue），新元素始终被添加在队列的末尾。 
- 删除（delete）操作也被称为出队（dequeue)。 你只能移除第一个元素。

- [用队列实现栈](https://leetcode-cn.com/problems/implement-stack-using-queues/)
- [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)


### <a name="7">广度优先搜索(BFS)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
广度优先搜索（BFS）的一个常见应用是找出从根结点到目标结点的最短路径。
注意点：
1. 初始入队列。
2. 是否需要层级访问。
3. 记录已访问节点的信息防止重复访问。


树的遍历代码模版：
```java

class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue =new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()) {
      int size = queue.size();
      for(int i = 0;i<size;i++) {
        TreeNode node = queue.poll();
        if(node.left!= null) {
          queue.add(node.left);
        }
      }
    }
  }
}
```


图遍历代码模板：
```java
public class Solution {
  int BFS2(Node root, Node target) {
    Queue<Node> queue = new LinkedList<>();  // store all nodes which are waiting to be processed
    Set<Node> used = new HashSet<>();     // store all the used nodes
    int step = 0;       // number of steps neeeded from root to current node
    // initialize
    queue.add(root);
    used.add(root);
    // BFS
    while (!queue.isEmpty()) {
      step = step + 1;
      // iterate the nodes which are already in the queue
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        Node cur = queue.poll();
        if (cur == target) {
          return step;
        }
        for (Node next : cur.neighbors) {
          if (!used.contains(next)) {
            queue.add(root);
            used.add(root);
          }
        }
      }
    }
    return -1;          // there is no path from root to target
  }
}
```


### <a name="8">单调队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

单调队列，即单调递减或单调递增的队列。
需要使用 双向队列 ，假设队列已经有若干元素：
- 当执行入队 push_back() 时： 若入队一个比队列某些元素更大的数字 xx ，则为了保持此列表递减，需要将双向队列 尾部所有小于 xx 的元素 弹出。
- 当执行出队 pop_front() 时： 若出队的元素是最大元素，则 双向队列 需要同时 将首元素出队 ，以保持队列和双向队列的元素一致性。


```java
class MaxQueue {
    Queue<Integer> queue;
    Deque<Integer> deque;
    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }
    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }
    public void push_back(int value) {
        queue.offer(value);
        // 元素入队逻辑，队尾小元素处队。新的大元素入队尾
        while(!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }
    public int pop_front() {
        if(queue.isEmpty()) return -1;
        // 元素出队逻辑，出队元素为队头则需要出队。
        if(queue.peek().equals(deque.peekFirst())){
            deque.pollFirst();
        }
        return queue.poll();
    }
}

```
- [剑指 Offer 59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)
- **[剑指 Offer 59 - I. 滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

## <a name="9">栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 栈具有记忆的功能，由其数据的特殊性可以用来DFS搜索

- [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)
- [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/ )
- *[最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/)
- *[字符串解码](https://leetcode-cn.com/problems/decode-string/)
- [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
- [二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)


### <a name="10">深度优先搜索(DFS)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
深度优先搜索（DFS）是用于 在树/图中遍历/搜索 的另一种重要算法。也可以在更抽象的场景中使用。\
正如树的遍历中所提到的，我们可以用 DFS 进行 前序遍历，中序遍历 和 后序遍历。在这三个遍历顺序中有一个共同的特性：除非我们到达最深的结点，否则我们永远不会回溯 。\
这也是 DFS 和 BFS 之间最大的区别，BFS永远不会深入探索，除非它已经在当前层级访问了所有结点。\
通常，我们使用递归实现 DFS。栈在递归中起着重要的作用。


递归遍历：
当我们递归地实现 DFS 时，似乎不需要使用任何栈。但实际上，我们使用的是由系统提供的**隐式栈**，也称为调用栈（Call Stack）。
```java

public class Solution {
  /*
   * Return true if there is a path from cur to target.
   */
  boolean DFS(Node cur, Node target, Set<Node> visited) {
    if(cur == target) {
      return true;
    }
    for (Node each : cur.neighbor){
      if (!visited.contains(each)){
        visted.add(each);
        boolean result = DFS(next, target, visited);
        if(result) {
            return true;
        }
      }
    }
    return false;
  }
}
```



对于图论，若使用DFS递归搜索时，在遇到数据量过大的情况。则要注意堆栈溢出的情况。因为递归使用的是系统的**隐式栈**.\
此时就可以使用栈来模拟系统**隐式栈**的调用过程，避免出现堆栈溢出。
```java

public class Solution {


  /*
   * Return true if there is a path from cur to target.
   */
  boolean DFS(Node root, int target) {
    Set<Node> visited;
    Stack<Node> s;
    s.push(root);
    while (!s.isEmpty()) {
      Node cur = s.pop();
      if(cur == target) {  
          return true;
      }
      for (Node next : cur.neighbors) {
        if (!visited.contains(next)) {
            s.add(next);
            visited.add(next);
        }
      }
    }
    return false;
  }
    
}
```


### <a name="11">单调栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
单调栈： 单调栈实际上就是栈， 只是利⽤了⼀些巧妙的逻辑， 使得每次新元素⼊栈后， 栈内的元素都保持有序（单调递增或单调递减） 。
```java
class Solution {
  public int[] dailyTemperatures(int[] T) {
    Deque<Integer> stack = new LinkedList<>();
    int len = T.length;
    int[] res = new int[len];
    // 从尾到头遍历
    for (int i = len - 1; i >= 0; i--) {
      while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
        stack.pop();
      }
      // 判断位置差值
      res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
      stack.push(i);
    }
    return res;
  }
}
```

- [最小栈](https://leetcode-cn.com/problems/min-stack/)
- [每日温度](https://leetcode-cn.com/problems/daily-temperatures/)
- [下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i/)
- [最短无序连续子数组](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/)
> 单调栈类似思想


- *[接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)
> 单调栈解法，递减栈，每次计算增量

- *[柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)
- [最大矩形](https://leetcode-cn.com/problems/maximal-rectangle/)
> 最大矩形计算，获取索引i的左右小于`height[i]`的最高点索引



## <a name="12">BFS 与 DFS</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
BFS与DFS相关的问题，经常都可以用两种方式求解，因此把相关问题放一起。

- [剑指 Offer 13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)
- [岛屿的周长](https://leetcode-cn.com/problems/island-perimeter/)
- [01 矩阵](https://leetcode-cn.com/problems/01-matrix/)
- [朋友圈](https://leetcode-cn.com/problems/friend-circles/)
- [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)
- [省份数量](https://leetcode-cn.com/problems/number-of-provinces/)
- [岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)
- [打开转盘锁（经典问题）](https://leetcode-cn.com/problems/open-the-lock/)
- [太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)
- [二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/)
- [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)
- [钥匙和房间](https://leetcode-cn.com/problems/keys-and-rooms/)

- *[判断二分图](https://leetcode-cn.com/problems/is-graph-bipartite/)
> 判断方法很特别，通过节点染色


### <a name="13">拓扑排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [课程表](https://leetcode-cn.com/problems/course-schedule/)
- [课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/)


### <a name="14">并查集</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```java
class UnionFindSet {
        int[] rank;
        int[] parent;

        public UnionFindSet(int n) {
            rank = new int[n];
            parent = new int[n];
        }

       public int find(int x) {
           if (parent[x] == 0) return x;
           return parent[x] = find(parent[x]); // Path compression by halving.
       }

        public boolean union(int x, int y) {
           int rootX = find(x);
           int rootY = find(y);
           if(rootX == rootY) return true;
           if(rank[rootX]>rank[rootY]) {
               parent[rootY] = rootX;
           } else if(rank[rootX]<rank[rootY]) {
               parent[rootX] = rootY;
           } else {
               parent[rootX] = rootY;
               rank[rootY]++;
           }
           return false;
       }
    }
```

- [冗余连接](https://leetcode-cn.com/problems/redundant-connection/)
- [除法求值](https://leetcode-cn.com/problems/evaluate-division/)


```
「力扣」第 1319 题：连通网络的操作次数（中等）；
「力扣」第 1631 题：最小体力消耗路径（中等）；
「力扣」第 959 题：由斜杠划分区域（中等）；
「力扣」第 1202 题：交换字符串中的元素（中等）；
「力扣」第 947 题：移除最多的同行或同列石头（中等）；
「力扣」第 721 题：账户合并（中等）；
「力扣」第 803 题：打砖块（困难）；
「力扣」第 1579 题：保证图可完全遍历（困难）;
「力扣」第 778 题：水位上升的泳池中游泳（困难）。
```

## <a name="15">递归</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="16">递归三要素</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
每次写递归，都按照这三要素思考：
1. 确定递归函数的参数和返回值\
确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数， 并且还要明确每次递归的返回值是什么进而确定递归函数的返回类型。
2. 确定终止条件：\
写完了递归算法,  运行的时候，经常会遇到栈溢出的错误，就是没写终止条件或者终止条件写的不对，操作系统也是用一个栈的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。
3. 确定单层递归的逻辑：\
确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。

### <a name="17">递归的思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 自顶而下：通过全局变量传递递归值
- 自底而上：带返回值的递归，依次叠加


## <a name="18">树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="19">二叉树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="20">树的遍历</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
- [中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

> 前中后序遍历递归与迭代写法
```java
public class Solution {
    //前序遍历 迭代写法
    public List<Integer> preorderTraversal1(TreeNode root) {
      LinkedList<TreeNode> stack = new LinkedList<>();
      LinkedList<Integer> output = new LinkedList<>();
      if (root == null) {
        return output;
      }
      stack.add(root);
      while (!stack.isEmpty()) {
        //每次获得第一个元素，理解成 栈的pop就行。
        TreeNode node = stack.pollLast();
        output.add(node.val);
        // 由于栈的属性 右节点先入栈
        if (node.right != null) {
          stack.add(node.right);
        }
        if (node.left != null) {
          stack.add(node.left);
        }
      }
      return output;
    }
  
    /**
     * 基于迭代的中序遍历
     * 1. 根据根节点把所有的左节点全部入栈，
     * 2. 出栈后的节点均为左节点，因此中序的下一个节点为右节点的左节点，继续找右节点的左节点入栈
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      TreeNode curr = root;
      while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
          stack.push(curr);
          curr = curr.left;
        }
        curr = stack.pop();
        res.add(curr.val);
        curr = curr.right;
      }
      return res;
    }
  
    // 后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
      LinkedList<TreeNode> stack = new LinkedList<>();
      LinkedList<Integer> output = new LinkedList<>();
      if (root == null) {
        return output;
      }
      stack.add(root);
      while (!stack.isEmpty()) {
        TreeNode node = stack.pollLast();
        // 关键点 每次添加都添加到输出的第一个
        output.addFirst(node.val);
        // 关键点2 左节点先入栈
        if (node.left != null) {
          stack.add(node.left);
        }
        if (node.right != null) {
          stack.add(node.right);
        }
      }
      return output;
    }
}
```

#### <a name="21">递归思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
以二叉树深度为例：
[二叉树的深度](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/)
```java
// 自顶而下 通过全局变量传递值
public class Solution {
  private int answer;

  private void maximum_depth(TreeNode root, int depth) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      answer = Math.max(answer, depth);
    }
    maximum_depth(root.left, depth + 1);
    maximum_depth(root.right, depth + 1);
  }

  // 自底而上 
  private int maximum_depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    return 1 + Math.max(maximum_depth(root.left), maximum_depth(root.right));
  }
}
```

#### <a name="22">构造及修改二叉树问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
涉及到⼆叉树的构造，⽆论普通⼆叉树还是⼆叉搜索树⼀定前序，都是先构造中节点。
> 理解TreeNode作为返回值的递归方法的含义，问题可以拆解成小问题到下层递归中。

常用的修改二叉树递归代码模版：
```java
public class Solution{
    
    public TreeNode solution(TreeNode node) {
        if(root == null){
            return null;
        }
        // .... 修改逻辑，修改的子节点返回
        root.left = solution(node.left);
        root.right = solution(node.right);
        
        
        // 第一层程序循环,会返回根结点
        return root;
    }
}

```

常用的构造二叉树模版：
```java
public class Solution{
    
    public TreeNode solution(int[] nums, int left, int right) {
        if(left> right) {
            return null;
        }
        TreeNode node = new TreeNode(); // 构建符合节点
        // 子节点构造
        node.left = solution(nums, xx, xx);
        node.right = solution(nums, xx, xx);
        
        // 返回构造节点
        return node;
    }
}


```

相关问题：
- [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

层序遍历构建二叉树思路： 根节点区分两个区间，获取中序左区间内的Set。遍历层序数组，第一个节点即为根节点。
```
ori:
  in[]    = {4, 8, 10, 12, 14, 20, 22};
  level[] = {20, 8, 22, 4, 12, 10, 14};
                20
               /  \
              /    \ 
    {4,8,10,12,14} {22}   

next:  
  In[]    = {4, 8, 10, 12, 14}
  level[] = {8, 4, 12, 10, 14} 
      
```



#### <a name="23">公共祖先问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
公共祖先问题，如何判断一个节点是公共祖先，如果该节点的左子树及右子树均找到要寻找的节点，那么该节点为公共祖先。
- [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)
 
### <a name="24">二叉搜索树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
二叉搜索树相关问题核心思想：
1. **中序遍历**（利用其二叉搜索树的结构）
2. 递归利用二叉搜索树属性进行处理。

- [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)
- [二叉搜索树中的搜索](https://leetcode-cn.com/problems/search-in-a-binary-search-tree/)
- [二叉搜索树中的插入操作](https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/)
- [把二叉搜索树转换为累加树（review）](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/)

[删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)
- 如果目标节点没有子节点，我们可以直接移除该目标节点。
- 如果目标节只有一个子节点，我们可以用其子节点作为替换。
- 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
 ```java
public class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (root.val > key) root.left = deleteNode(root.left, key);
    else if (root.val < key) root.right = deleteNode(root.right, key);
    else {
      if (root.left == null && root.right == null) {
        root = null;
      } else if (root.right != null) {
        root.val = successor(root);
        root.right = deleteNode(root.right, root.val);
      } else {
        root.val = predecessor(root);
        root.left = deleteNode(root.left, root.val);
      }
    }
    return root;
  }

  private int successor(TreeNode root) {
    root = root.right;
    while (root.left != null) root = root.left;
    return root.val;
  }

  private int predecessor(TreeNode root) {
    root = root.left;
    while (root.right != null) root = root.right;
    return root.val;
  }
}
```
    
二叉搜索树的最近公共祖先(与树的公共祖先有区别) ：使用了二叉搜索树的特点
- [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

二叉搜索树构建：
- [将有序数组转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)

### <a name="25">算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="26">树的遍历</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
- [二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
- [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
- [二叉树的层平均值](https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/)
- [二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
- [二叉树的层次遍历 II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/  )
- [二叉树的锯齿形层次遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)

#### <a name="27">树的属性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)
- [二叉树中第二小的节点](https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/)
- [左叶子之和](https://leetcode-cn.com/problems/sum-of-left-leaves/)
- [二叉树的右视图](https://leetcode-cn.com/problems/binary-tree-right-side-view/)
- [树左下⻆的值](https://leetcode-cn.com/problems/find-bottom-left-tree-value/)
- [填充每个节点的下⼀个右侧节点指针](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)
- [填充每个节点的下⼀个右侧节点指针II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/)
- [路径总和](https://leetcode-cn.com/problems/path-sum/)
- [路径总和 II（review）](https://leetcode-cn.com/problems/path-sum-ii/)

二叉搜索树：
- [二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)
- [二叉搜索树的最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)
- [二叉搜索树中的众数](https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/)
- [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/   )
- [两数之和 IV - 输入 BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)
- [⼆叉搜索树中的搜索](https://leetcode-cn.com/problems/search-in-a-binary-search-tree/)
- [把⼆叉搜索树转换为累加树](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/)

完全二叉树：
- [完全⼆叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes/)

#### <a name="28">子树问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
子树问题，为了避免重复的遍历判断是否同一子数可以使用序列化的方式解决
- [另一个树的子树](https://leetcode-cn.com/problems/subtree-of-another-tree/)
- [寻找重复的子树](https://leetcode-cn.com/problems/find-duplicate-subtrees/solution/)

#### <a name="29">树的高度</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/)
- [二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/)
- [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
- [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

#### <a name="30">树的构建与修改</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)
- [对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/)
- [合并二叉树](https://leetcode-cn.com/problems/merge-two-binary-trees/)
- [从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)
- [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [最大二叉树](https://leetcode-cn.com/problems/maximum-binary-tree/)
- [二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)


二叉搜索树：
- [不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)
- [不同的二叉搜索树 II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/)
- [将有序数组转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/  )
- [修剪二叉搜索树](https://leetcode-cn.com/problems/trim-a-binary-search-tree/)
- [⼆叉搜索树中的插⼊操作](https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/)

#### <a name="31">树的基本操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)
- [删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)

#### <a name="32">公共祖先问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
- [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)



## <a name="33">回溯</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
回溯法⼀般是在集合中递归搜索，集合的⼤⼩构成了树的宽度，递归的深度构成的树的深度。

![image](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/basic/backTracking.png)

### <a name="34">伪代码模版</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
void backtracking(参数) {
    if (终⽌条件) {
        存放结果;
        return;
    }
    for (选择：本层集合中元素（树中节点孩⼦的数量就是集合的⼤⼩）) {
        处理节点;
        backtracking(路径，选择列表); // 递归
        回溯，撤销处理结果
    }
}
```

#### <a name="35">回溯三部曲</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 回溯函数模板返回值以及参数。回溯算法中函数返回值⼀般为void。
```
void backtracking(参数)
```
2. 回溯函数终⽌条件。
```
if (终⽌条件) {
    存放结果;
    return;
}
```
3. 回溯搜索的遍历过程。
```
for (选择：本层集合中元素（树中节点孩⼦的数量就是集合的⼤⼩）) {
    处理节点;
    backtracking(路径，选择列表); // 递归
    回溯，撤销处理结果
}
```

#### <a name="36">startIndex使用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
需要startIndex来控制for循环的起始位置，对于组合问题，什么时候需要startIndex呢？
1. 如果是⼀个集合来求组合的话，就需要startIndex
2. 如果是多个集合取组合，各个集合之间相互不影响，那么就不⽤startIndex，例如：回溯算法：电话号 码的字⺟组合

对于排列问题：
1. 每层都是从0开始搜索⽽不是startIndex
2. 需要used数组记录path⾥都放了哪些元素了

### <a name="37">问题场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
回溯算法能解决如下问题：
- 组合问题：N个数⾥⾯按⼀定规则找出k个数的集合
- 排列问题：N个数按⼀定规则全排列，有⼏种排列⽅式
- 切割问题：⼀个字符串按⼀定规则有⼏种切割⽅式
- ⼦集问题：⼀个N个数的集合⾥有多少符合条件的⼦集
- 棋盘问题：N皇后，解数独等等

### <a name="38">重复问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
“树枝去重”和“树层去重”

组合问题可以抽象为树形结构，那么“使⽤过”在这个树形结构上是有两个维度的，⼀个维度是同⼀树枝上“使⽤过”，⼀个维度是同⼀树层上“使⽤过”。\
常规使用树层去重，树枝去重会导致过多无谓的查找，而树层去重对于无用的查找可以及时的中断break

![image](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/basic/backTrackingDuplicate.png)


树层去重
1. 数据无序
```
public void backTracking() {
    
    HashSet<Integer> set = new HashSet<>();
    for(int i = startIndex ;i< end;i ++){
        if(set.contains(i)){
            continue;
        }
        // 回溯逻辑
        set.add(i);
        backTracking();
        set.remove(i);
    }
}

```
2. 数据有序
```
public void backTracking() {
    
    for(int i = startIndex ;i< end;i ++){
        //回溯中常常使用的避免重复解的条件
        if(i>startIndex && nums[i-1]=nums[i]) {
            continue;
        }
        // 回溯逻辑
        backTracking();
    }
}

```

### <a name="39">组合问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [组合](https://leetcode-cn.com/problems/combinations/submissions/)
- [组合总和](https://leetcode-cn.com/problems/combination-sum/)
- [组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/submissions/)
- [组合总和 III](https://leetcode-cn.com/problems/combination-sum-iii/)
- [组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)

- [递增子序列](https://leetcode-cn.com/problems/increasing-subsequences/)

### <a name="40">切割问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
- [字符串的排列]( https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
- [复原IP地址](https://leetcode-cn.com/problems/restore-ip-addresses/)
- [分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)


### <a name="41">排列问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [全排列](https://leetcode-cn.com/problems/permutations/)
- [全排列 II](https://leetcode-cn.com/problems/permutations-ii/)


### <a name="42">子集问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [子集（review）](https://leetcode-cn.com/problems/subsets/)
- [子集 II（review）](https://leetcode-cn.com/problems/subsets-ii/)


- [括号生成（review）](https://leetcode-cn.com/problems/generate-parentheses/)
    - > 动态规划 + dfs + 回溯 或者 dfs + 回溯
- [解数独](https://leetcode-cn.com/problems/sudoku-solver/)
- [单词搜索](https://leetcode-cn.com/problems/word-search/)
- [二叉树路径](https://leetcode-cn.com/problems/binary-tree-paths/)
- [重新安排行程](https://leetcode-cn.com/problems/reconstruct-itinerary/)

```java   
public class Soluction {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      boolean[] used = new boolean[nums.length];
      Arrays.sort(nums);
      dfs(nums, new ArrayList<>(), used, res);
      return res;
    }
  
  
    public void dfs(int[]nums, List<Integer>path,boolean[] used, List<List<Integer>> res ){
      if(path.size() == nums.length) {
          res.add(new ArrayList<>(path));
          return;
      }

      for (int i = 0; i < nums.length; i++) {
          if (used[i]|| i>0&& nums[i] == nums[i-1] && !used[i-1]) continue;
          path.add(nums[i]);
          used[i] = true;
          dfs(nums,path,used,res);
          path.remove(path.size()-1);
          used[i] = false;
      }
    }
}
```

## <a name="43">贪心</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
贪⼼的本质是选择每⼀阶段的局部最优，从⽽达到全局最优。
> 例如，有⼀堆钞票，你可以拿⾛⼗张，如果想达到最⼤的⾦额，你要怎么拿？\
指定每次拿最⼤的，最终结果就是拿⾛最⼤数额的钱。

如何验证可不可以⽤贪⼼算法呢？
> 最好⽤的策略就是举反例，如果想不到反例，那么就试⼀试贪⼼吧。

贪⼼算法⼀般分为如下四步：
- 将问题分解为若⼲个⼦问题
- 找出适合的贪⼼策略
- 求解每⼀个⼦问题的最优解
- 将局部最优解堆叠成全局最优解

### <a name="44">指针与区间局部最优</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [跳跃游戏](https://leetcode-cn.com/problems/jump-game/)
- [跳跃游戏II](https://leetcode-cn.com/problems/jump-game-II/)
- [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)
- [加油站](https://leetcode-cn.com/problems/gas-station/)
- [划分字⺟区间](https://leetcode-cn.com/problems/partition-labels/)
- [摆动序列](https://leetcode-cn.com/problems/wiggle-subsequence/)

### <a name="45">区间问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [⽤最少数量的箭引爆⽓球](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)
- [合并区间](https://leetcode-cn.com/problems/merge-intervals/)
- [无重叠区间](https://leetcode-cn.com/problems/non-overlapping-intervals/)

- [根据身⾼重建队列](https://leetcode-cn.com/problems/queue-reconstruction-by-height/)

### <a name="46">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [分配饼干](https://leetcode-cn.com/problems/assign-cookies/description/)
- [单调递增的数字](https://leetcode-cn.com/problems/monotone-increasing-digits/)
- [分发糖果](https://leetcode-cn.com/problems/candy/)
- [救生艇](https://leetcode-cn.com/problems/boats-to-save-people/)


- [监控⼆叉树](https://leetcode-cn.com/problems/binary-tree-cameras/)
- [K次取反后最⼤化的数组和](https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/)
- [修改一个数成为非递减数组](https://leetcode-cn.com/problems/non-decreasing-array/)
- [柠檬⽔找零](https://leetcode-cn.com/problems/lemonade-change/)


- [买卖股票的最佳时机含⼿续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)
- [买卖股票的最佳时机II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)


## <a name="47">动态规划</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="48">基本思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
动态规划的⼀般流程优化三步：
1. 暴⼒的递归解法 -> 
2. 带备忘录的 递归解法 -> 
3. 迭代的动态规划解法。

动态规划思想流程：
1. 【状态】和【选择】，明确问题存在哪几种状态；问题场景如何做状态选择，进而转换状态。
2. 确定dp数组以及下标的含义
3. 根据【选择】的过程，确定递推公式
4. dp数组如何初始化
5. 确定遍历顺序
6. 举例推导dp数组，进行问题模拟
7. 出错的情况，将dp数组打印出来，保证程序处理流程如设想运行。




如果能写出暴力的递归方法，就回发现在递归过程中，进行了太多重复计算，此时可以使用备忘录的方法进行优化。

斐波那契数列备忘录优化：
```java
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int fib(int n) {
        if(n <2 ) {
            return n;
        }
        if(map.containsKey(n)) {
            return map.get(n);
        }
        int result = fib(n-1) + fib(n-2);
        map.put(n, result);
        return result;
    }
}
```

动态规划思想优化：
```java
class Solution {
    public int fib(int n) {
        if(n <2 ) {
            return n;
        }
        int prv = 1;
        int pprv = 0;
        int result = 0;
        for(int i =2;i<=n;i++) {
            result = prv+pprv;
            pprv = prv;
            prv = result;
        }
        return result;
    }
}
```


### <a name="49">相关问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- [斐波那契数](https://leetcode-cn.com/problems/fibonacci-number/)
- [爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)
- [使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

- [打家劫舍](https://leetcode-cn.com/problems/house-robber/)
- [打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii/)
- [打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii/)


- [不同路径](https://leetcode-cn.com/problems/unique-paths/)
- [不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)
- *[整数拆分](https://leetcode-cn.com/problems/integer-break/)
- [不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)
- [数字翻译字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)



### <a name="50">字符串问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
第⼀种思路模板是⼀个⼀维的 dp 数组

第二个思路模版是建立一个二维的dp数组
1. 定义dp：两字符串的动态规划问题，经常的就是以字符串1与字符串2的长度组成二维数组。
2. 定义下标及含义：dp的含义经常就是求解的结果，下标为从0～i、j的两字符串的子问题
3. 状态转移公式：主要考虑以下三个的状态转移
  - `dp[i-1][j-1]`: 匹配条件的，结果顺推
  - `dp[i-1][j]`
  - `dp[i][j-1]`
4. 初始化

#### <a name="51">字符操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [两个字符串的删除操作](https://leetcode-cn.com/problems/delete-operation-for-two-strings/)
- [编辑距离](https://leetcode-cn.com/problems/edit-distance/)

#### <a name="52">子序列问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- [最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)
- [最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/)
- [剑指 Offer II 095. 最长公共子序列](https://leetcode-cn.com/problems/qJnOS7/)
- [不相交的线](https://leetcode-cn.com/problems/uncrossed-lines/)
- [判断子序列](https://leetcode-cn.com/problems/is-subsequence/)
- [环形子数组的最大和](https://leetcode-cn.com/problems/maximum-sum-circular-subarray/)
- *[不同的子序列](https://leetcode-cn.com/problems/distinct-subsequences/)
- *[最长递增子序列的个数](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)

#### <a name="53">子数组问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- *[最长重复子数组](https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/)
- [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

#### <a name="54">回文问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
回文问题demo：
```java
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][]dp = new boolean[len][len];
        int result = 0;
        // 初始化0值
        for(int i =0;i<len;i++) {
            dp[i][i] = true;
            result++;
        }
        // 自底而上，自左而右，遍历右上部分三角区域
        for(int i = len-2;i>=0;i--) {
            for(int j = i+1;j<len;j++) {
                // 状态转移
                if(s.charAt(i) == s.charAt(j)) {
                    // i与j相邻情形
                    if(j-i ==1) {
                        dp[i][j] = true;
                        result++;
                        // 判断子内容是否为回文
                    } else if(dp[i+1][j-1]) {
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
```

- *[回文子串](https://leetcode-cn.com/problems/palindromic-substrings/)
- [最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)


### <a name="55">股票问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```java
public class StockTrading {

    public static void main(String[] args) {
//        int[] prices = new int[]{3,3,5,0,0,3,1,4};
        int[] prices = new int[]{1,2,3,4,5};
        StockTrading st = new StockTrading();
        System.out.println(st.maxProfit(2, prices));
    }


    /**
     * 1. 定义dp及下标
     * dp[i][j][k]含义为第i天所能获取的最大利润
     * 下标i：天数
     * 下标j：第j次交易
     * 下标k：0买入、1卖出
     * 
     * 2. 初始化，由于dp代表的是第i天所能获取的最大利润，第0天卖出均要初始化成-price[0]
     * 
     * 3. 状态转移：
     *   dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
     *   dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][][] dp = new int[prices.length][k+1][2];

        // 若交易数超过天数一半，转化为不限交易次数问题
        if(k> len/2)  return maxProfitNoLimit(prices);
        for(int i =1;i<=k; i++ ){
            dp[0][i][1] = -prices[0];
        }
        for(int i = 1;i<prices.length;i++) {
            for(int j = 1; j<=k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }

        return dp[len-1][k][0];
    }

    public int maxProfitNoLimit(int[] prices) {
        int n = prices.length;
        int buy = -prices[0];
        int empty = 0;
        for(int i =1;i<n;i++) {
            int curEmpty = Math.max(empty, buy+prices[i]);
            int curBuy = Math.max(buy, empty-prices[i]);
            empty = curEmpty;
            buy = curBuy;
        }
        return empty;
    }
}

```

股票问题中，需要区分的一个点是，代表卖出买入的 `dp[i][0]` 与 `dp[i][1]`的状态转移问题
1. 若买入`dp[i][1]`从 卖出`dp[i-1][0]` 转移而来说明，可以多次交易
2. 若买入`dp[i][1]`仅从 `dp[i-1][1]` 转移而来，说明仅能进行一次交易

> 对于含冷冻期的股票，也可以表示为一个状态\
> 对于限制次数的股票交易，在k数值较小的情况，可以将交易次数k打平，如用数字4表示第二次的卖出

- [买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)
- [买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)
- [买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)
- [买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)
- [最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
- [买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

### <a name="56">背包问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="57">常见求解方式及疑难点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

01背包问题
```java
public class ZeroOnePackage {

  public static void main(String[] args) {
    ZeroOnePackage zeroOnePackage = new ZeroOnePackage();
    int[] v = {1, 2, 3, 4};
    int[] val = {2, 4, 4, 5};
    System.out.println(zeroOnePackage.getMaxValueOfPackage2(4, 5, v, val));
  }

  /**
   * 二维矩阵 01 背包
   * n个物品  m的体积的背包
   * 先遍历物品再遍历背包，测试每个物品放进背包与不放进背包的最大价值
   *
   * f[n][m] = max( f[n-1][m], f[n-1][m-v[i]] + val[i] )
   *
   * @param m
   * @param n
   * @param v
   * @param val
   * @return
   */
  public int getMaxValueOfPackage(int n, int m, int[] v, int[] val) {
    // 这边定义为n+1 与 m+1 个二维矩阵，表示i个物品的j体积的最大价值。下面注意 数组-1问题
    int[][] dp = new int[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        // 默认第i个物品不放进背包
        dp[i][j] = dp[i - 1][j];
        // 第i个物品放进背包， 前提条件体积j大于物品i的体积，取最大价值
        if (j >= v[i - 1]) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i - 1]] + val[i - 1]);
        }
      }
    }
    return dp[n][m];
  }

  /**
   * 01 背包一维数组的解法
   * 注意需要逆序处理，因为原先的二维压缩成一维，在开始第i个物品的遍历时，dp存储的为i-1个物品的最大价值。
   * 若使用顺序遍历，则i-1个物品的最大价值会被覆盖，每次放进物品取 dp[j-v[i-1]] 的数据是往前取，因此需要逆序遍历
   * f[j] = max(f[j], f[j-v[i]])
   * @param n
   * @param m
   * @param v
   * @param val
   * @return
   */
  public int getMaxValueOfPackage2(int n, int m, int[] v, int[] val) {
    int[] dp = new int[m + 1];
    for (int i = 1; i <= n; i++) {
      //优化使用j>=v[i-1], 仅需要查看 v[i-1] ~ m 这个体积区间的最大价值是否需要更新
      for (int j = m; j >=v[i-1]; j--) {
        dp[j] = Math.max(dp[j], dp[j-v[i-1]] + val[i-1]);
      }
    }
    return dp[m];
  }
}

```

完全背包问题
```java
public class FullPackage {

    public static void main(String[] args) {
        FullPackage fullPackage = new FullPackage();
        int[] v = {1, 2, 3, 4};
        int[] val = {2, 4, 4, 5};
        System.out.println(fullPackage.getMaxPackageValue2(4, 5, v, val));

    }

    /**
     * 二维数组完全背包
     * 按硬币和体积的顺序二维遍历，每次物品只有放与不放两种情况，第i个物品不放则总价值与i-1个物品一致
     * 第i个物品放进背包，则价值为 f[i][j-v[i]+ val[i] 注意这边同样为第i行，因为物品可以放多次。
     * f[i][j] = max( f[i-1][j] + f[i][j-v[i]+ val[i])
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public int getMaxPackageValue(int n,int m ,int[]v, int [] val) {
        int [][]dp = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 默认第i个物品不放进背包
                dp[i][j] = dp[i-1][j];
                // 第i个物品放进背包，前提条件体积j大于物品i的体积，取最大价值
                if (j>=v[i-1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-v[i-1]] +val[i-1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 一维压缩 完全背包
     * 此处不需要从大到小遍历，因为物品可以多次放入背包，因此取得状态为第i个物品遍历体积的前序状态。
     * @param n
     * @param m
     * @param v
     * @param val
     * @return
     */
    public int getMaxPackageValue2(int n,int m ,int[]v, int [] val) {
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = v[i-1]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j-v[i-1]] + val [i-1]);
            }
        }
        return dp[m];
    }
}
```


除了上述背包问题的常见解法，还需**区分先遍历物品再遍历背包，与先遍历背包再遍历物品的区别**。
- 如果求组合数就是外层for循环遍历物品，内层for遍历背包。组合不强调顺序，如(1,5)和(5,1)是同⼀个组合。
- 如果求排列数就是外层for遍历背包，内层for循环遍历物品。排列强调顺序，如(1,5)和(5,1)是两个不同的排列。

> 如果把遍历nums（物品）放在外循环，遍历target的作为内循环的话，举⼀个例⼦：计算`dp[4]`的时
候，结果集只有 {1,3} 这样的集合，不会有{3,1}这样的集合，因为nums遍历放在外层，3只能出现在1后⾯！
```

for(int i =1; i< 物品.length;i++) {
  for(int j = 1;j< 背包.length;j++) {
  
  
  }
}


for(int i =1; i< 背包.length;i++) {
  for(int j = 1;j< 物品.length;j++) {
  
  
  }
}

```


#### <a name="58">典型背包问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [01背包问题](https://www.acwing.com/problem/content/2/)
- [完全背包问题](https://www.acwing.com/problem/content/3/)
- [多重背包问题 I](https://www.acwing.com/problem/content/4/)
- [多重背包问题 II](https://www.acwing.com/problem/content/5/) :对多重背包的数量进行压缩
- [混合背包问题](https://www.acwing.com/problem/content/7/)
- [二维费用的背包问题](https://www.acwing.com/problem/content/8/)


#### <a name="59">背包场景问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
01背包：
- [分割等和子集](https://leetcode-cn.com/problems/partition-equal-subset-sum/)
- [最后一块石头的重量 II](https://leetcode-cn.com/problems/last-stone-weight-ii/)
- [目标和](https://leetcode-cn.com/problems/target-sum/)
- [一和零](https://leetcode-cn.com/problems/ones-and-zeroes/)

完全背包：
- [零钱兑换 II](https://leetcode-cn.com/problems/coin-change-2/)
- [组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)
> 求排列问题，**理解先遍历物品再遍历背包，与先遍历背包再遍历物品的区别**！！！
- [零钱兑换](https://leetcode-cn.com/problems/coin-change/)
- [完全平方数](https://leetcode-cn.com/problems/perfect-squares/)


- *[单词拆分](https://leetcode-cn.com/problems/word-break/)


### <a name="60">扔鸡蛋问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[鸡蛋掉落](https://leetcode-cn.com/problems/super-egg-drop/)

该问题理解的关键为：因为我们要求的是**最坏情况下扔鸡蛋的次数**，所以鸡蛋在第 i 层楼碎没碎，最后搜索的取决于那种情况的结果更⼤。
```java
class Solution {
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int superEggDrop(int k, int n) {
        return dp(k, n);
    }

    public int dp(int k, int n) {
        if (!memo.containsKey(n * 100 + k)) {
            int ans = Integer.MAX_VALUE;
            // 层数为0，不需要尝试
            if (n == 0) {
                ans = 0;
            // 只剩一个鸡蛋，最坏得试n次
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo  <=  hi) {
                    int x = (hi -lo) / 2 + lo;
                    // 鸡蛋丢坏的区域搜索
                    int t1 = dp(k - 1, x - 1);
                    // 鸡蛋未对坏的区域搜索
                    int t2 = dp(k, n - x);
                    
                    // 鸡蛋丢坏的次数与鸡蛋未丢坏的次数对比，搜索次数多的区域，表示搜索最坏情况要丢多少次
                    if (t1 <=  t2) {
                        lo = x+1;
                        ans = Math.min(ans, t2+1);
                    } else {
                        hi = x-1;
                        ans = Math.min(ans, t1+1);
                    }
                }
            }
            // 备忘录
            memo.put(n * 100 + k, ans);
        }

        return memo.get(n * 100 + k);
    }
}

```



## <a name="61">二分法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
- [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
- [寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)
- [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)
- [有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)
- [Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)

- [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)





## <a name="62">前缀树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
前缀树又名字典树，单词查找树，Trie树，是一种多路树形结构，是哈希树的变种，和hash效率有一拼，是一种用于快速检索的多叉树结构。

典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计\
它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。


## <a name="63">滑动窗口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)




## <a name="64">TODO 二进制应用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
计算1的个数

## <a name="65">常用操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
求余数常见操作
```java
public class Solution {
  private int getNext(int n) {
    int totalSum = 0;
    while (n > 0) {
      // 余数
      int d = n % 10;
      // 整除进位
      n = n / 10;
      // 余数操作
      totalSum += d * d;
    }
    return totalSum;
  }
}
```

### <a name="66">Kanade 算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于一个给定数组 A，Kadane 算法可以用来找到 A 的最大子段和。
- [最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)
```
class Solution {
    public int maxSubArray(int[] nums) {
        int dp = 0, res = Integer.MIN_VALUE;
        for(int num : nums) {
            dp = num + Math.max(dp, 0);
            res = Math.max(dp, res);
        }
        return res;
    }
}
```