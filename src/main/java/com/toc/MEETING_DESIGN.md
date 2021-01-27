<a name="index">**Index**</a>

<a href="#0">会议室预约系统</a>  
&emsp;<a href="#1">1. 常规思路</a>  
&emsp;&emsp;<a href="#2">1.1. 数据库设计</a>  
&emsp;&emsp;<a href="#3">1.2. 处理逻辑</a>  
&emsp;&emsp;&emsp;<a href="#4">1.2.1. 查询逻辑</a>  
&emsp;&emsp;&emsp;<a href="#5">1.2.2. 会议室预约路基</a>  
&emsp;&emsp;&emsp;<a href="#6">1.2.3. 并发控制</a>  
&emsp;&emsp;&emsp;<a href="#7">1.2.4. 问题点</a>  
&emsp;<a href="#8">2. 改进思路</a>  
&emsp;&emsp;<a href="#9">2.1. 查询优化</a>  
&emsp;&emsp;<a href="#10">2.2. 并发优化</a>  
# <a name="0">会议室预约系统</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

会议室预约基本元素: 
1. 会议室：会议室ID、可容纳人数、是否多媒体等等
2. 日期
3. 基本信息：预约人等

## <a name="1">常规思路</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
会议室预约是一个时间段，包括开始时间、结束时间。

### <a name="2">数据库设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 会议室信息主表。
2. 人员信息表
3. 预约信息主表、子表


- 以下为预约信息主表`t_reserve`及子表 `t_reserve_detail`：

| 列 | 类型 | 描述 |
| --- | --- | ---|
|id|int64|自增键|
|meeting_id|int64|索引，会议表的id|
|date_month|date|索引，会议室日期，形如2019-02-18|

| 列 | 类型 | 描述 |
| --- | --- | ---|
|id|int64|自增键|
|reserve_id|int64|索引，预约主表ID|
|start_time|date|开始时间，形如2019-02-18 12:00|
|end_time|date|开始时间，形如2019-02-18 12:00|

### <a name="3">处理逻辑</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="4">查询逻辑</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
查询某天某个时间段空闲会议室：
```
select t.meeting_id from t_reserve left t join t_reserve_detail td on t.id = t.reserve_id
where t.date_month = xxx and not exists 
(
select reserve_id from t_reserve_detail td1 
where start_time between  '2019-07-27 00:00:00' and '2019-07-29 00:00:00'  and  td1.reserve_id = t.id
union
select reserve_id from t_reserve_detail td2
where end_time between  '2019-07-27 00:00:00' and '2019-07-29 00:00:00' 
 and  td2.reserve_id = t.id
)
```

#### <a name="5">会议室预约路基</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 查询选定时间段有的会议室
2. 选定会议室
3. 选定时间段
4. 进行主子表的信息插入。

#### <a name="6">并发控制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 单体应用使用java锁进行控制
2. 集群或者分布式应用使用数据库乐观锁或者redis 分布式锁控制

#### <a name="7">问题点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 查询的性能问题
2. 并发控制的性能问题：
    1. 使用数据库乐观锁，用户体验问题。
    2. redis分布式锁，key：会议ID+日期，一个会议室不同时间段预约不冲突的情况，也需要获取锁。
    
## <a name="8">改进思路</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

1. 查询效率优化
2. 并发控制优化

实际的会议室预约中，不开始时间及结束时间会精细到具体的分秒。可以与产品沟通将需求确定到以5min、10min、15min的粒度。

### <a name="9">查询优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 根据5min将一天24h进行划分
    - 24*60/5 = 288 
2. 主表上新增区间字段，代表当前区间是否有会议室预约
    - 假设以2h为刻度，那么新增12个字段区间 T1 ~ T12。
    - 按`位0、1` 代表当前5min刻度是否有人预约


实际查询过程：根据起始时间和结束时间换算对应的区间
```
场景一： 同一个区间
startTime = 12:00 
endTime = 12:40

区间换算 => 第7个区间(T7)

select  meeting_id  from demo where SUBSTR(T7 FROM 0 FOR 8) = 0

场景二：跨区间
startTime = 11:30   -> T6
endTime = 12:40     -> T7


select  meeting_id  from demo where T6 < xxx and SUBSTR(T7 FROM 0 FOR 8) = 0

场景三： 跨天 TODO
```

### <a name="10">并发优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用redis分布式锁锁对应的时间区间，减小锁粒度。