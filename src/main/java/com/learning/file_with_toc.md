<a name="index">**Index**</a>
<a href="#0">1. 算法</a>  
&emsp;<a href="#1">1.1. 链表</a>  
&emsp;&emsp;<a href="#2">1.1.1. 单链表</a>  
&emsp;&emsp;<a href="#3">1.1.2. 树</a>  
&emsp;<a href="#4">1.2. 数组</a>  
&emsp;<a href="#5">1.3. 动态规划</a>  
&emsp;&emsp;<a href="#6">1.3.1. 常规思想</a>  
&emsp;&emsp;<a href="#7">1.3.2. 子序列</a>  
&emsp;&emsp;<a href="#8">1.3.3. 数组与数</a>  
&emsp;&emsp;<a href="#9">1.3.4. 背包问题</a>  
&emsp;&emsp;<a href="#10">1.3.5. 其他</a>  
# <a name="0">1. 算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

[TOC]

## <a name="1">1.1. 链表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">1.1.1. 单链表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 双指针问题

	- 环形链表
	- 删除链表的倒数第N个节点
	- 链表中点

- 递归

	- 反转链表
	- 排序链表

- 其他

	- 头插法和尾插法
	- 合并链表

### <a name="3">1.1.2. 树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 二叉树

	- 遍历

		- 前序遍历
		- 中序遍历
		- 后序遍历
		- 层序遍历
		- remark：熟悉递归和迭代的写法

	- 递归

		- 自顶向下递归

		  通常会使用全局变量或者在方法中传递对象，来存储遍历时的值。
		  比如求树的高度，用一个全局变量存储高度，每次遍历的时候带入高度。
		  
		  private int answer;		// don't forget to initialize answer before call maximum_depth
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

		- 自底向上递归

		  递归的时候，直接递归到底部，再一步步传值回去。
		  
		  public int maximum_depth(TreeNode root) {
		  ​	if (root == null) {
		  ​		return 0;                  // return 0 for null node
		  ​	}
		  ​	int left_depth = maximum_depth(root.left);
		  ​	int right_depth = maximum_depth(root.right);
		  ​	return Math.max(left_depth, right_depth) + 1;	// return depth of the subtree rooted at root
		  }

		- 带返回值的递归
		- 不带返回值的递归

	- 其他

		- 使用数组指针构建树

			- 从前序和中序构建树
			- 从中心和后序构建树

- 二叉搜索树

	- 重要的中序遍历
	- 锻炼思维的--删除节点操作
	- 重要算法

		- 验证二叉搜索树有效
		- 寻找前继节点

		  /**
		   * 寻找root的前继节点
		   * Predecessor 代表的是中序遍历序列的前一个节点。
		   * 即比当前节点小的最大节点，简称前驱节点。先取当前节点的左节点，然后取该节点的右节点，直到右节点为空，则最后指向的节点为前驱节点。
		  
		   * @param root
		   * @return
		   */
		  private int predecessor(TreeNode root) {
		      root = root.left;
		      while(root.right != null) root = root.right;
		      return root.val;
		  }

		- 寻找后继节点

		  /**
		   * 这个方法要熟记
		   * 查找root的后继节点
		   * Successor 代表的是中序遍历序列的下一个节点。
		   * 即比当前节点大的最小节点，简称后继节点。 先取当前节点的右节点，然后一直取该节点的左节点，直到左节点为空，则最后指向的节点为后继节点。
		   * @param root
		   * @return
		   */
		  private int successor(TreeNode root) {
		      root = root.right;
		      while(root.left != null) root = root.left;
		      return root.val;
		  }

	- 高度平衡二叉树

		- AVL（自平衡二叉查找树）
		- 红黑树

- N叉树

	- 基本都是二叉树的思想

## <a name="4">1.2. 数组</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="5">1.3. 动态规划</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="6">1.3.1. 常规思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 暴力穷举
- 在暴力基础建立备忘录：自顶而下
- 建立DP：自底而上

	- 分析问题的状态
	- 分析子问题结构
	- 定义DP的维度与值的含义
	- 列举基础case分析状态转移
	- 初始化Base Case

- 完成全状态DP后，在考虑优化
- 部分题目答案不是要最后的状态

### <a name="7">1.3.2. 子序列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 最长上升子序列

	- 一维DP，根据base case 拓展

- 最长公共子序列

	- 常规两字符串DP，值为当前索引最长公共子序列

- 最长回文序列

	- DP为字符串索引，值为序列长度，与(i,j-1)、(i+1,j)有关

- 正则匹配

	- 难点为状态转移分析很容易漏情况

- 最长回文串

	- 中心拓展法，DP与上述一致，状态转移仅考虑dp(i+1,j-1)的情况
	- 回文串（计算数目）

		- 中心拓展法或DP标记是否为回文再统计数量

- 类似：两字符串编辑距离

	- 定义索引dp，从1开始分析转移方程

- 思路：

	- 一维数组状态状态遍历
	- dp定义为两字符串的二维数据
	- 定义为字符串索引的二维数组

### <a name="8">1.3.3. 数组与数</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 完全平方数

	- 确定子问题dp[n] = dp[n-j*j]+1

- 整数拆分

	- 子问题dp[n]=max(dp[n-j]*j,...)

- 等差序列

	- 使用最小问题遍历，记录距离的常数级DP

- 摆动序列

	- 根据摆动特性建立up，down的dp

- 数组问题

	-  最长重复子数组

		- 数组连续，状态转移与i+1、j-1有关
		- 或滑动窗口解法

	- 乘积最大子数组

		- 子数组连续，每次保存最大同时记录负case用于转移

### <a name="9">1.3.4. 背包问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 0-1背包

	- n个商品装进背包的最大价值
	- leetcode：416、474、494

		- 474. 一和零 核心例题

	- 与完全背包区别：转移方程i-1个DP有关，保证一个商品仅适用一次。

- 完全背包

	- n种商品装满背包的装法

		- 变形体：377. 组合总和 Ⅳ，相同的结果排序不同算不同结果

		  nums = [1, 2, 3]
		  target = 4
		  
		  所有可能的组合为：
		  (1, 1, 1, 1)
		  (1, 1, 2)
		  (1, 2, 1)
		  (1, 3)
		  (2, 1, 1)
		  (2, 2)
		  (3, 1)
		  
		  public int combinationSum4(int[] nums, int target) {
		          int len = nums.length;
		          int[][]dp = new int[target+1][len +1];
		          Arrays.fill(dp[0], 1);
		          for(int i =1;i<= target;i++) {
		              for(int j= 1;j <= len;j++) {
		                   dp[i][j] = dp[i][j-1];
		                   if(i - nums[j-1]>=0) {
		                      dp[i][j] += dp[i-nums[j-1]][len] ;
		                  } 
		              }
		          }
		          return dp[target][len];
		      }

	- n种零钱找出m金额的所有方法
	- n种零钱找出m金额的最少数量

- 思路：                                                             
1.状态——背包重量、商品是否放进背包。 
2.dp——x背包重量，y商品种类                       3.状态转移——价值取向、数量取向、方法取向4.base case
- 空间优化：0-1背包逆向填表、完全背包正向填表

### <a name="10">1.3.5. 其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 股票买卖

	- 定义状态：持有股票状态、天数、买卖次数
	- 一次买卖的最大收益
	- 天数内买卖次数不限的最大收益
	- 限制买卖次数的最大收益

- 博弈问题

	- 石头选堆问题

		- 数组先选后选差值
		- 基于数组索引建立DP
		- 反着思考，基础扩展至实际问题

- 区间问题

	- 排序区间根据首尾索引排序区间最小为最前，再用最小区间case依次对比

- 打家劫舍

	- 从尾到头子问题base case拓展
	- 环形屋子

		- 分情况讨论回到基础情况

	- 树形屋子

		- 递归讨论

- 鸡蛋掉落问题

*XMind - Trial Version*