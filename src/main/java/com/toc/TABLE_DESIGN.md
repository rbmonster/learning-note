<a name="index">**Index**</a>

<a href="#0">表结构设计</a>  
&emsp;<a href="#1">1. 部门表设计</a>  
&emsp;<a href="#2">2. 签到表设计</a>  
# <a name="0">表结构设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">部门表设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
单表的设计方案，使用parent_id做关系连接，形成级联结构。例子：
```
id | parent_id | deparement_name | department_desc| ...
```

问题点： \
如何根据一个部门查询对应的父部门及子部门？
1. 使用java内存操作，一次性读取所有部门信息，进行级联组装。再通过id查询子节点的所有部门。
2. 添加表字段，添加一个path字段，用于表示父部门的所有路径。

## <a name="2">签到表设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
用户签到只有两种状态，因此可以使用`位 0 1` 表示签到的状态。使用mask 32位的int 表示每个月签到的次数
1. 获取用户当月的签到状态：
    - 根据服务器时间判断当前的月份month，根据传入的user_id和month去数据库中查找用户签到的数据mask。
2. 签到：
    - 假设当日是本月第i天（这个可以计算得出），更新数据库中mask: mask = mask | (1 << i)，更新连续签到天数，若i是1或第i-1天没有签到，本月连续签到天数置为1；其他情况则更新连续签到天数+1。
3. 判断当日是否签到：
    - 如果mask & (1 << i)大于0，说明签到了，如果为0，说明未签到。
4. 本月补签：
    - 补签某个日期，假设是第j天，更新数据库中mask: mask = mask | (1 << j)，重新统计本月连续签到天数，从第i位开始逆序遍历到第1天统计连续签到天数。


| 列 | 类型 | 描述 |
| --- | --- | ---|
|id|int64|自增键|
|user_id|int64|索引，用户表的id|
|date_month|date|索引，月份，形如2019-02|
|mask|int32|用户签到的数据|
|continue_sign_month|int32|用户本月连续签到的天数|

- 相关资料： [签到设计](https://blog.csdn.net/liyunlong41/article/details/86739134)