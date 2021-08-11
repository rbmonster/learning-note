# 算法

## 哈希表
哈希表的关键思想是使用哈希函数将键映射到存储桶。
- HashMap常见操作
```java
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
```java
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
    
## 数组与字符串
### 数组
- 针对于数组的索引问题，常规的操作就是用指针、搜索、hash表问题解决
- [搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)
- [寻找数组的中心索引](https://leetcode-cn.com/problems/find-pivot-index/)
- [对角线遍历](https://leetcode-cn.com/problems/diagonal-traverse/)


### 字符串
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

## 链表
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
```java
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
注意点： 
1. 初始入队列。 
2. 是否需要层级访问。
3. 记录已访问节点的信息防止重复访问。

相关例题：
- [打开转盘锁](https://leetcode-cn.com/problems/open-the-lock/)
- [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

代码模板：
```java
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

- [有效的括号](https://leetcode-cn.com/problems/valid-parentheses/ )
- [每日温度（单调栈）](https://leetcode-cn.com/problems/daily-temperatures/)

## 递归
### 递归三要素
每次写递归，都按照这三要素思考：
1. 确定递归函数的参数和返回值\
确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数， 并且还要明确每次递归的返回值是什么进而确定递归函数的返回类型。
2. 确定终止条件：\
写完了递归算法,  运行的时候，经常会遇到栈溢出的错误，就是没写终止条件或者终止条件写的不对，操作系统也是用一个栈的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。
3. 确定单层递归的逻辑：\
确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。

### 递归的思想
- 自顶而下：通过全局变量传递递归值
- 自底而上：带返回值的递归，依次叠加


## 树
### 二叉树

#### 树的遍历
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

#### 递归思想
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

#### 构造及修改二叉树问题
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



#### 公共祖先问题
公共祖先问题，如何判断一个节点是公共祖先，如果该节点的左子树及右子树均找到要寻找的节点，那么该节点为公共祖先。
- [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)
 
### 二叉搜索树
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

### 算法

#### 树的遍历
- [二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)
- [二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
- [二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
- [二叉树的层平均值](https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/)
- [二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
- [二叉树的层次遍历 II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/  )
- [二叉树的锯齿形层次遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)

#### 树的属性
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

#### 子树问题
子树问题，为了避免重复的遍历判断是否同一子数可以使用序列化的方式解决
- [另一个树的子树](https://leetcode-cn.com/problems/subtree-of-another-tree/)
- [寻找重复的子树](https://leetcode-cn.com/problems/find-duplicate-subtrees/solution/)

#### 树的高度
- [平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/)
- [二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/)
- [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
- [二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

#### 树的构建与修改
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

#### 树的基本操作
- [二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)
- [删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)

#### 公共祖先问题
- [二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
- [二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)



## 回溯
回溯法⼀般是在集合中递归搜索，集合的⼤⼩构成了树的宽度，递归的深度构成的树的深度。

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/backTracking.png)

### 伪代码模版
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

#### 回溯三部曲
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

#### startIndex使用
需要startIndex来控制for循环的起始位置，对于组合问题，什么时候需要startIndex呢？
1. 如果是⼀个集合来求组合的话，就需要startIndex
2. 如果是多个集合取组合，各个集合之间相互不影响，那么就不⽤startIndex，例如：回溯算法：电话号 码的字⺟组合

对于排列问题：
1. 每层都是从0开始搜索⽽不是startIndex
2. 需要used数组记录path⾥都放了哪些元素了

### 问题场景
回溯算法能解决如下问题：
- 组合问题：N个数⾥⾯按⼀定规则找出k个数的集合
- 排列问题：N个数按⼀定规则全排列，有⼏种排列⽅式
- 切割问题：⼀个字符串按⼀定规则有⼏种切割⽅式
- ⼦集问题：⼀个N个数的集合⾥有多少符合条件的⼦集
- 棋盘问题：N皇后，解数独等等

### 重复问题
“树枝去重”和“树层去重”

组合问题可以抽象为树形结构，那么“使⽤过”在这个树形结构上是有两个维度的，⼀个维度是同⼀树枝上“使⽤过”，⼀个维度是同⼀树层上“使⽤过”。\
常规使用树层去重，树枝去重会导致过多无谓的查找，而树层去重对于无用的查找可以及时的中断break

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/backTrackingDuplicate.png)


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

### 组合问题
- [组合](https://leetcode-cn.com/problems/combinations/submissions/)
- [组合总和](https://leetcode-cn.com/problems/combination-sum/)
- [组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/submissions/)
- [组合总和 III](https://leetcode-cn.com/problems/combination-sum-iii/)
- [组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)

- [递增子序列](https://leetcode-cn.com/problems/increasing-subsequences/)

### 切割问题
- [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
- [字符串的排列]( https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
- [复原IP地址](https://leetcode-cn.com/problems/restore-ip-addresses/)
- [分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)


### 排列问题
- [全排列](https://leetcode-cn.com/problems/permutations/)
- [全排列 II](https://leetcode-cn.com/problems/permutations-ii/)


### 子集问题
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



## 算法归类

### 二分法
- [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
- [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
- [寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)
- [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)
- [有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)
- [Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)

- [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)


### DFS & BFS

#### BFS & DFS：
- [01 矩阵](https://leetcode-cn.com/problems/01-matrix/)
- [朋友圈](https://leetcode-cn.com/problems/friend-circles/)
- [判断二分图（review）](https://leetcode-cn.com/problems/is-graph-bipartite/)
    - > 判断方法很特别，通过节点染色
- [岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)
- [打开转盘锁（经典问题）](https://leetcode-cn.com/problems/open-the-lock/)
- [太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)
- [二进制矩阵中的最短路径](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/)
- [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)
  
  
#### 拓扑排序
- [课程表](https://leetcode-cn.com/problems/course-schedule/)
- [课程表 II](https://leetcode-cn.com/problems/course-schedule-ii/)

### 动态规划
- [数字翻译字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

- [打家劫舍 III](https://leetcode-cn.com/problems/house-robber-iii/)


### 并查集
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

[冗余连接](https://leetcode-cn.com/problems/redundant-connection/)



### 单调栈
单调栈： 单调栈实际上就是栈， 只是利⽤了⼀些巧妙的逻辑， 使得每次新元素⼊栈后， 栈内的元素都保持有序（单调递增或单调递减） 。

     
- [每日温度](https://leetcode-cn.com/problems/daily-temperatures/)
- [下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i/)
```java
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

### 单调队列

- [剑指 Offer 59 - I. 滑动窗口的最大值]( https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)
```java
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

### 前缀树
前缀树又名字典树，单词查找树，Trie树，是一种多路树形结构，是哈希树的变种，和hash效率有一拼，是一种用于快速检索的多叉树结构。

典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计
它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。


### 贪心算法
[CS-Note](http://www.cyc2018.xyz/%E7%AE%97%E6%B3%95/Leetcode%20%E9%A2%98%E8%A7%A3/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E8%B4%AA%E5%BF%83%E6%80%9D%E6%83%B3.html#_1-%E5%88%86%E9%85%8D%E9%A5%BC%E5%B9%B2)

1. [分配饼干](https://leetcode-cn.com/problems/assign-cookies/description/)
    - 每次分配给满足孩子的最小的饼干
2. [不重叠的区间个数](https://leetcode-cn.com/problems/non-overlapping-intervals/)
    - 每次取结尾最小的区间，留给后面选择的区间更大，移除的区间越小。按区间结尾排序。
3. [投飞镖刺破气球](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)
    - 与上述类似，每次取结尾最小的区间，
    

### TODO List
计算1的个数