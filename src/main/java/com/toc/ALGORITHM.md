<a name="index">**Index**</a>

<a href="#0">算法</a>  
&emsp;<a href="#1">1. 哈希表</a>  
&emsp;<a href="#2">2. 数组与字符串</a>  
&emsp;&emsp;<a href="#3">2.1. 数组</a>  
&emsp;&emsp;<a href="#4">2.2. 字符串</a>  
&emsp;<a href="#5">3. 链表</a>  
&emsp;<a href="#6">4. 队列与栈</a>  
&emsp;&emsp;<a href="#7">4.1. 广度优先搜索</a>  
&emsp;&emsp;<a href="#8">4.2. 栈</a>  
&emsp;<a href="#9">5. 树</a>  
&emsp;&emsp;<a href="#10">5.1. 二叉树</a>  
&emsp;&emsp;<a href="#11">5.2. 二叉搜索树</a>  
&emsp;<a href="#12">6. 算法归类</a>  
&emsp;&emsp;<a href="#13">6.1. 树</a>  
&emsp;&emsp;&emsp;<a href="#14">6.1.1. 树的遍历</a>  
&emsp;&emsp;&emsp;<a href="#15">6.1.2. 树的高度</a>  
&emsp;&emsp;&emsp;<a href="#16">6.1.3. 树的构建</a>  
&emsp;&emsp;&emsp;<a href="#17">6.1.4. 树的基本操作</a>  
&emsp;&emsp;&emsp;<a href="#18">6.1.5. 其他</a>  
&emsp;<a href="#19">7. 二分法</a>  
&emsp;<a href="#20">8. DFS & BFS</a>  
&emsp;&emsp;<a href="#21">8.1. BFS & DFS：</a>  
&emsp;&emsp;<a href="#22">8.2. 拓扑排序</a>  
&emsp;&emsp;<a href="#23">8.3. dfs+回溯</a>  
&emsp;<a href="#24">9. 动态规划</a>  
&emsp;<a href="#25">10. 并查集</a>  
&emsp;<a href="#26">11. 单调栈</a>  
&emsp;<a href="#27">12. 单调队列</a>  
&emsp;<a href="#28">13. 前缀树</a>  
&emsp;<a href="#29">14. 贪心算法</a>  
&emsp;<a href="#30">15. TODO List</a>  
# <a name="0">算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">哈希表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
哈希表的关键思想是使用哈希函数将键映射到存储桶。
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

使用哈希映射的第一个场景是，我们需要更多的信息，而不仅仅是键。然后通过哈希映射建立密钥与信息之间的映射关系。
- [同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/)

另一个常见的场景是按键聚合所有信息，我们也可以使用哈希映射来实现这一目标。
- [存在重复元素](https://leetcode-cn.com/problems/contains-duplicate-ii/)
- [字符串中的第一个唯一字符](https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/)
- [寻找重复的子树（hash表与树的结合）](https://leetcode-cn.com/problems/find-duplicate-subtrees/)
    
## <a name="2">数组与字符串</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="3">数组</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 针对于数组的索引问题，常规的操作就是用指针、搜索、hash表问题解决
- [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)
- [寻找数组的中心索引](https://leetcode-cn.com/problems/find-pivot-index/)
- [对角线遍历](https://leetcode-cn.com/problems/diagonal-traverse/)


### <a name="4">字符串</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
公共前缀问题
- [最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/)
    - 分治法、二分法
  
回文问题（包含子串与子序列问题）
- [最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

双指针问题
- [反转字符串](https://leetcode-cn.com/problems/reverse-string/)
- [两数之和 II - 输入有序数组（最基础问题） ](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)
- 快慢指针：
    - 移除数组
    - 移动零

- [反转单词顺序](https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/)

## <a name="5">链表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
链表双指针：
- [环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)
- [环形链表2](https://leetcode-cn.com/problems/linked-list-cycle-ii/)
- [相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)
    - > 两链表相接最后一段common
- 删除链表的倒数第N个节点
- 移除链表元素
- [旋转链表](https://leetcode-cn.com/problems/rotate-list/)
  
经典问题：
- 反转链表：栈、头插法、递归
- [回文链表](https://leetcode-cn.com/problems/palindrome-linked-list/)
- [合并两有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

  
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

## <a name="6">队列与栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="7">广度优先搜索</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
注意点： 
1. 初始入队列。 
2. 是否需要层级访问。
3. 记录已访问节点的信息防止重复访问。

相关例题：
- [打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)
- [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

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

### <a name="8">栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
TODO 待补充代码模板

- 栈具有记忆的功能，由其数据的特殊性可以用来DFS搜索

- [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/ )
- [每日温度（单调栈）](https://leetcode-cn.com/problems/daily-temperatures/)

## <a name="9">树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="10">二叉树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
- [中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
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

递归的运用
- 自顶而下：通过全局变量传递递归值
- 自底而上：带返回值的递归，依次叠加

下面以二叉树深度为例：
[二叉树的深度](https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/)
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

构造二叉树问题：
- [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)
- 层序遍历构建二叉树思路： 根节点区分两个区间，获取中序左区间内的Set。遍历层序数组，第一个节点即为根节点。
    - ```
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

公共祖先问题：
- [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)
 
### <a name="11">二叉搜索树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
二叉搜索树相关问题基本思想：
1. 中序遍历
2. 递归利用二叉搜索树属性进行处理。

基本操作：
- [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)
- [二叉搜索树中的搜索](https://leetcode-cn.com/problems/search-in-a-binary-search-tree/)
- [二叉搜索树中的插入操作](https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/)
- [把二叉搜索树转换为累加树（review）](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/)
- 删除二叉搜索树中的节点
  - 如果目标节点没有子节点，我们可以直接移除该目标节点。
  - 如果目标节只有一个子节点，我们可以用其子节点作为替换。
  - 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
  - [例题](https://leetcode-cn.com/problems/delete-node-in-a-bst/)
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
    
二叉搜索树的最近公共祖先(与树的公共祖先有区别) ：使用了二叉搜索树的特点
- https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

二叉搜索树构建：
- [将有序数组转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)

    
## <a name="12">算法归类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="13">树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="14">树的遍历</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
- [二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
- [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
- [二叉树的层平均值](https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/)
- [二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
- [二叉树的层次遍历 II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/  )
- [二叉树的锯齿形层次遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)
  
- [二叉搜索树的最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)
- [翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)
- [对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/)
- [二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)
- [二叉树中第二小的节点](https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/)
- [另一个树的子树](https://leetcode-cn.com/problems/subtree-of-another-tree/)
- [修剪二叉搜索树（review）](https://leetcode-cn.com/problems/trim-a-binary-search-tree/)
- [左叶子之和（review）](https://leetcode-cn.com/problems/sum-of-left-leaves/)
- [二叉树的右视图（review）](https://leetcode-cn.com/problems/binary-tree-right-side-view/)
- [找树左下角的值（review）](https://leetcode-cn.com/problems/find-bottom-left-tree-value/)
- [寻找重复的子树（review）](https://leetcode-cn.com/problems/find-duplicate-subtrees/solution/)
- [二叉搜索树中的众数](https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/)
- [打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii/)
- [二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)
- [合并二叉树（review）](https://leetcode-cn.com/problems/merge-two-binary-trees/)
- [路径总和](https://leetcode-cn.com/problems/path-sum/)
- [路径总和 II（review）](https://leetcode-cn.com/problems/path-sum-ii/)
- [填充每个节点的下一个右侧节点指针（review）](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)
- [两数之和 IV - 输入 BST（review）](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)
- [验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/   )

  
#### <a name="15">树的高度</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/)
- [二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/)
- [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
- [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)
  
#### <a name="16">树的构建</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - [从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)
  - [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
  - [将有序数组转换为二叉搜索树](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/  )
  - [最大二叉树](https://leetcode-cn.com/problems/maximum-binary-tree/)
  - [二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)
  - [不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)
  - [不同的二叉搜索树 II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/)
  
#### <a name="17">树的基本操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - [删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)
  - [二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)
  
#### <a name="18">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
  - [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)
  
## <a name="19">二分法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
- [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
- [寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)
- [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)
- [有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)
- [Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)

- [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)


## <a name="20">DFS & BFS</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="21">BFS & DFS：</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [01 矩阵](https://leetcode-cn.com/problems/01-matrix/)
- [朋友圈](https://leetcode-cn.com/problems/friend-circles/)
- [判断二分图（review）](https://leetcode-cn.com/problems/is-graph-bipartite/)
    - > 判断方法很特别，通过节点染色
- [岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)
- [打开转盘锁（经典问题）](https://leetcode-cn.com/problems/open-the-lock/)
- [太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)
- [二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/)
- [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)
  
  
### <a name="22">拓扑排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [课程表](https://leetcode-cn.com/problems/course-schedule/)
- [课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/)

### <a name="23">dfs+回溯</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [全排列【review】](https://leetcode-cn.com/problems/permutations/)
- [全排列 II【review】](https://leetcode-cn.com/problems/permutations-ii/)
- [字符串的排列]( https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
    - 重点看区别重复元素的条件
    - ```   public List<List<Integer>> permute(int[] nums) {
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
      ```
- [组合](https://leetcode-cn.com/problems/combinations/submissions/)
- [组合总和（review）](https://leetcode-cn.com/problems/combination-sum/)
- [组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/submissions/)
    - > 通过传递每次迭代的索引，避免重复记录
- [组合总和 III](https://leetcode-cn.com/problems/combination-sum-iii/)
- [组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)
- [括号生成（review）](https://leetcode-cn.com/problems/generate-parentheses/)
- [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
- [分割回文串（review）](https://leetcode-cn.com/problems/palindrome-partitioning/)
    - > 动态规划 + dfs + 回溯 或者 dfs + 回溯 
- [复原IP地址](https://leetcode-cn.com/problems/restore-ip-addresses/)
- [子集（review）](https://leetcode-cn.com/problems/subsets/)
- [子集 II（review）](https://leetcode-cn.com/problems/subsets-ii/)
- [解数独](https://leetcode-cn.com/problems/sudoku-solver/)
- [单词搜索](https://leetcode-cn.com/problems/word-search/)
- [二叉树路径](https://leetcode-cn.com/problems/binary-tree-paths/)
  
  ```
  回溯中常常使用的避免重复解的条件：
   if (i>start  && nums[i] == nums[i - 1]) {
      continue;
   }
  ```

## <a name="24">动态规划</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [数字翻译字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)



## <a name="25">并查集</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
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

[冗余连接](https://leetcode-cn.com/problems/redundant-connection/)



## <a name="26">单调栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
单调栈： 单调栈实际上就是栈， 只是利⽤了⼀些巧妙的逻辑， 使得每次新元素⼊栈后， 栈内的元素都保持有序（单调递增或单调递减） 。

     
- [每日温度](https://leetcode-cn.com/problems/daily-temperatures/)
- [下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i/)
```
public int[] dailyTemperatures(int[] T) {
    Deque<Integer> stack = new LinkedList<>();
    int len = T.length;
    int[] res = new int[len];
    // 从尾到头遍历
    for(int i = len-1; i>=0 ;i--) {
        while(!stack.isEmpty() && T[stack.peek()] <= T[i]) {
            stack.pop();
        }
        // 判断位置差值
        res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
        stack.push(i);
    }
    return res;
}
```

## <a name="27">单调队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- [剑指 Offer 59 - I. 滑动窗口的最大值]( https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)
```
public int[] maxSlidingWindow(int[] nums, int k) {
     if(nums.length == 0 || k == 0) return new int[0];
    Deque<Integer> deque = new LinkedList<>();
    int len = nums.length;
    int[] res=  new int[len -k +1];
    for(int i =0;i<k ;i++) {
        // 新元素入队，比较队尾元素，小的元素全部移除，保证队列的单调性
        while(!deque.isEmpty()&& deque.peekLast() <nums[i]) {
            deque.removeLast();
        }
        deque.addLast(nums[i]);
    }
    res[0] = deque.peekFirst();
    for(int i =k;i<len;i++) {
        // 滑动窗口的比较
        if(nums[i-k] == deque.peekFirst()) {
            deque.removeFirst();
        }
        while(!deque.isEmpty()&& deque.peekLast() <nums[i]) {
            deque.removeLast();
        }
        deque.addLast(nums[i]);
        res[i-k+1] = deque.peekFirst();
    }
    return res;
}
```

## <a name="28">前缀树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
前缀树又名字典树，单词查找树，Trie树，是一种多路树形结构，是哈希树的变种，和hash效率有一拼，是一种用于快速检索的多叉树结构。

典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计
它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。


## <a name="29">贪心算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[CS-Note](http://www.cyc2018.xyz/%E7%AE%97%E6%B3%95/Leetcode%20%E9%A2%98%E8%A7%A3/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E8%B4%AA%E5%BF%83%E6%80%9D%E6%83%B3.html#_1-%E5%88%86%E9%85%8D%E9%A5%BC%E5%B9%B2)

1. [分配饼干](https://leetcode-cn.com/problems/assign-cookies/description/)
    - 每次分配给满足孩子的最小的饼干
2. [不重叠的区间个数](https://leetcode-cn.com/problems/non-overlapping-intervals/)
    - 每次取结尾最小的区间，留给后面选择的区间更大，移除的区间越小。按区间结尾排序。
3. [投飞镖刺破气球](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)
    - 与上述类似，每次取结尾最小的区间，
    

## <a name="30">TODO List</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
计算1的个数