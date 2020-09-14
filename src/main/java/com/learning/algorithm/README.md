# 算法

## 哈希表
- 哈希表的关键思想是使用哈希函数将键映射到存储桶。
- HashMap常见操作
```
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

int result1 =map.compute(3,(key, oldValue) -> oldValue-10);
//output 10
int result = map.compute(4,(key, oldValue) -> oldValue-10);
//cause error nullPoint,key 4 not exist


map.put(1, count.getOrDefault(1, 0) + 1);
// 取1的值，如果存在则取value 否则默认为0、
```

- 求余数常见操作
```
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
```

- 使用哈希映射的第一个场景是，我们需要更多的信息，而不仅仅是键。然后通过哈希映射建立密钥与信息之间的映射关系。
  - 例题：
    - 同构字符串：https://leetcode-cn.com/problems/isomorphic-strings/
- 另一个常见的场景是按键聚合所有信息。我们也可以使用哈希映射来实现这一目标。
  - 例题：
    - 存在重复元素：https://leetcode-cn.com/problems/contains-duplicate-ii/
    - 字符串中的第一个唯一字符;https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
    - 寻找重复的子树（hash表与树的结合）：https://leetcode-cn.com/problems/find-duplicate-subtrees/
    
## 数组与字符串
### 数组
- 针对于数组的索引问题，常规的操作就是用指针、搜索、hash表问题解决
- 搜索插入位置：https://leetcode-cn.com/problems/search-insert-position/
- 寻找数组的中心索引：https://leetcode-cn.com/problems/find-pivot-index/
- 对角线遍历：https://leetcode-cn.com/problems/diagonal-traverse/


### 字符串
- 公共前缀问题
  - 最长公共前缀：https://leetcode-cn.com/problems/longest-common-prefix/
  
- 回文问题（包含子串与子序列问题）
  - 最长回文子串：https://leetcode-cn.com/problems/longest-palindromic-substring/

- 双指针问题
  - 反转字符串
  - 两数之和 II - 输入有序数组（最基础问题） ：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
  - 快慢指针：
    - 移除数组
    - 移动零
    

## 链表
- 链表双指针：
  - 环形链表：https://leetcode-cn.com/problems/linked-list-cycle/
  - 环形链表2：https://leetcode-cn.com/problems/linked-list-cycle-ii/
  - 相交链表：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
    - 两链表相接最后一段common
  - 删除链表的倒数第N个节点
  - 移除链表元素
  - 旋转链表：https://leetcode-cn.com/problems/rotate-list/
  
- 经典问题：
  - 反转链表：栈、头插法、递归
  - 回文链表：https://leetcode-cn.com/problems/palindrome-linked-list/
  - 合并两有序链表：https://leetcode-cn.com/problems/merge-two-sorted-lists/

  
反转链表递归写法:
```
public ListNode reverseList(ListNode head) {
        if(head ==null || head.next == null ) {
            return head;
        }
        ListNode res = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }
```

## 队列与栈
### 广度优先搜索
 - 注意点： 1.初始入队列。 2.是否需要层级访问。3.记录已访问节点的信息防止重复访问。
 - 相关例题：
   - 打开转盘锁：https://leetcode-cn.com/problems/open-the-lock/
   - 岛屿数量；https://leetcode-cn.com/problems/number-of-islands/

代码模板：
```
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
            if(cur == target) {
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
```

### 栈
TODO 待补充代码模板
- 栈具有记忆的功能，由其数据的特殊性可以用来DFS搜索

- 20. 有效的括号：https://leetcode-cn.com/problems/valid-parentheses/ 
- 739. 每日温度（单调栈）：https://leetcode-cn.com/problems/daily-temperatures/

## 树
### 二叉树

- 前中后序遍历递归与迭代写法
```
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
public List < Integer > inorderTraversal2(TreeNode root) {
    List < Integer > res = new ArrayList < > ();
    Stack < TreeNode > stack = new Stack< >();
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
```

- 递归的运用
  - 自顶而下：通过全局变量传递递归值
  - 自底而上：带返回值的递归，依次叠加
  - 下面以二叉树深度为例
```
// 自顶而下 通过全局变量传递值
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
        return 0 ;
    }
    if (root.left == null && root.right == null) {
        return 1;
    }
    return 1+Math.max(maximum_depth(root.left), maximum_depth(root.right));
}
```

- 构造二叉树问题：
  - 105. 从前序与中序遍历序列构造二叉树:https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
  - 106. 从中序与后序遍历序列构造二叉树:https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
  
- 公共祖先问题：
  - 236. 二叉树的最近公共祖先：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 
### 二叉搜索树
  - 基本操作：
    - 验证二叉搜索树：https://leetcode-cn.com/problems/validate-binary-search-tree/
    - 二叉搜索树中的搜索：https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
    - 二叉搜索树中的插入操作：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
    - 删除二叉搜索树中的节点
      - 如果目标节点没有子节点，我们可以直接移除该目标节点。
      - 如果目标节只有一个子节点，我们可以用其子节点作为替换。
      - 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
      - https://leetcode-cn.com/problems/delete-node-in-a-bst/
      - ```
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root ==null) {
                return null;
            }
            if(root.val > key) root.left = deleteNode(root.left, key);
            else if(root.val <key) root.right = deleteNode(root.right, key);
            else {
                if(root.left == null && root.right == null) {
                    root = null;
                }
                else if(root.right !=null) {
                    root.val = successor(root);
                    root.right = deleteNode(root.right, root.val );
                } else {
                    root.val = predecessor(root);
                    root.left = deleteNode(root.left ,root.val);
                }
            }
            return root;
        }
    
        private int successor(TreeNode root) {
            root = root.right;
            while(root.left != null) root = root.left;
            return root.val;
        }
    
        private int predecessor(TreeNode root) {
            root = root.left;
            while(root.right != null) root = root.right;
            return root.val;
        }
        ```
  - 二叉搜索树的最近公共祖先(与树的公共祖先有区别) ：使用了二叉搜索树的特点
    - https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
  - 二叉搜索树构建：
    - 将有序数组转换为二叉搜索树：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
    