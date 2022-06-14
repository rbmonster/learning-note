<a name="index">**Index**</a>

<a href="#0">Mongodb</a>  
&emsp;<a href="#1">1. 基本概念</a>  
&emsp;&emsp;<a href="#2">1.1. 文档</a>  
&emsp;&emsp;<a href="#3">1.2. 集合</a>  
&emsp;&emsp;<a href="#4">1.3. 数据类型</a>  
&emsp;&emsp;<a href="#5">1.4. _id 和 ObjectId</a>  
&emsp;<a href="#6">2. 基本操作</a>  
&emsp;<a href="#7">3. 索引介绍</a>  
&emsp;<a href="#8">4. 应用场景</a>  
&emsp;&emsp;<a href="#9">4.1. MySQL VS MongoDB</a>  
&emsp;&emsp;<a href="#10">4.2. 应用场景</a>  
&emsp;&emsp;<a href="#11">4.3. 压测结果</a>  
&emsp;&emsp;<a href="#12">4.4. 常见架构</a>  
&emsp;<a href="#13">5. 其他</a>  
&emsp;&emsp;<a href="#14">5.1. oplog</a>  
# <a name="0">Mongodb</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

[使用Docker安装MongoDB](https://www.cnblogs.com/yunquan/p/11174265.html)

[MongoDB用户角色配置](https://www.cnblogs.com/out-of-memory/p/6810411.html)


## <a name="1">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MongoDB是面向文档的数据库，不是关系型数据库。MongoDB的设计采用横向扩展的设计，能自动处理跨集群的数据和负载，自动中心分配文档，以及将用户的请求路由到正确的机器上。
> 基本的思路就是将原来"行"的概念换成更加灵活的"文档"模型。

独特功能：
- 索引：支持通用二级索引，切提供唯一索引、复合索引、地理空间索引及全文索引。
- 聚合：聚合管道。
- 特殊的集合类型：适用于将在某个时刻过期的数据，如会话(session)。支持固定大小的集合。
- 文件存储：支持一种非常易用的协议，用于存储大文件和文件元数据。

### <a name="2">文档</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
多个键及其关联的值有序地放置在一起便是文档。


### <a name="3">集合</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
集合就是一组文档。如果是MongoDB中的文档类似于关系型数据库的行，那么集合就如同表。
> 动态模式：一个集合里面的文档可以是各式各样的。正常会放相关类型的文档

子集合： 惯例是使用"." 分隔不同命名空间的子集合（没有任何特别的属性，只是用来命名区分）

### <a name="4">数据类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- null: 用于表示空值或者不存在的字段
- boolean
- 数值： 64位浮点数
- 字符串
- 日期：不存储时区
- 正则表达式
- 数组
- 内嵌文档：可嵌套其他文档
- 对象id：12字节的ID，唯一标识
- 二进制数据
- 代码：JavaScript代码

> 内嵌文档可以将比如地址文档嵌入到人员文档中
### <a name="5">_id 和 ObjectId</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

MongoDB中存储的文档必须有一个_id键，这个键的值可以是任何类型的，默认是个ObjectId对象。

ObjectId的组成：
- 4位时间戳
- 3位机器码: 主机唯一标识符,机器主机名的散列值
- 2位PID: 进程标识符
- 3位计数器：自动增加的计数器，确保相同进程同一秒产生的ObjectId不一样


**自动生成的_id**：通常在客户端由驱动程序完成，减轻了数据库的负担


## <a name="6">基本操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
TODO
[官网文档](https://docs.mongodb.com/v4.4/tutorial/iterate-a-cursor/)

## <a name="7">索引介绍</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
插入10000条测试数据
```text
for(i=0;i<10000;i++) {
    db.helloworld.insert(
        {
            "i":i,
            "username":"username"+i,
            "age":Math.floor(Math.random()*100),
            "created":new Date()
        });
}
```
`db.COLLECTION_NAME.ensureIndex({KEY:1})`\
语法中 Key 值为你要创建的索引字段，1为指定按升序创建索引，如果你想按降序来创建索引指定为-1即可\
1. background: 建索引过程会阻塞其它数据库操作，background可指定以后台方式创建索引，即增加 "background" 可选参数。 "background" 默认值为false。
2. unique: 建立的索引是否唯一。指定为true创建唯一索引。默认值为false.
3. name: 索引的名称。如果未指定，MongoDB的通过连接索引的字段名和排序顺序生成一个索引名称。


复合索引： `db.col.ensureIndex({"title":1,"description":-1}) `


覆盖索引：一个数据库查询，索引覆盖了所有查询的字段，概念与Mysql类似。

隐式索引：针对复合索引，索引具有最左前缀的特，同mysql

索引失效：$nin、$ne 等取反的查询，会影响使用索引 与 mysql类似
```text
db.learning.find({
	"username" : { "$nin" : ["username110"]}
})

db.learning.find({
	"username" : { "$ne" : "^suse.*"}
})
```

唯一索引： null也是一个唯一索引的值，不可重复
```text
db.helloworld.ensureIndex({
    "username": 
    1
}, {
    "unique": 
    true
});
```

稀疏索引： 与唯一索引一起使用可以解决null值可为重复的，但是如果字段有值则必须为唯一。
```text
db.helloworld.ensureIndex({
    "username": 
    1
}, {
    "unique": true, 
		"sparse":true
});
```

```text
db.helloworld.dropIndex("username_1")
```


## <a name="8">应用场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
设计的两个关键，一个重要问题：
- 范式化：将数据分散到不同的集合中，数据类似与关系型数据一样，使用主键关联。
  > 缺点：需要两次查询组装数据
- 反范式化：将一个文档所需的数据都嵌入到该文档中。
  > 缺点：数据更新需要遍历所有文档进行更新，更新过程存在部分数据为旧值的情况
- 问题：数据是否频繁更新？

![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbCompare.png)


### <a name="9">MySQL VS MongoDB</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. Mysql是关系型数据库，而MongoDB是非关系型
2. MongoDB文档自然地映射我们的Model，而Mysql通常需要多表关联进行数据映射。
3. 横向拓展能力MongoDB可以通过原生分片完善支持，而Mysql只能通过数据分区或者应用侵入式的访问实现分区
4. Mongodb的文档字段可以是动态的，而Mysql新增字段则需要写sql进行添加
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbvs.png)


![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbSummary.png)
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbFunction.png)


### <a name="10">应用场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbApplication.png)
除了上述场景，还有:
1. 元数据（配置）管理：比如常见的Java Spring中经常需要配置数据，而随着相同类型的数据越来越多，就适合转移到MongoDB中。该类型数据变化快，且经常以点查为主。
2. 内容管理：对于营销的邮件短信，通常为存在占位符的大文本。该类型的数据也适合存储在MongoDB中。
3. 草稿功能：对于用户认证过程，经常需要分几步填写用户的信息，应用通常会保存用户的草稿信息。该草稿信息在正式提交前除了记忆外没有任何意义，而且经常为一个JSON类型的数据。


### <a name="11">压测结果</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbPresure.png)


### <a name="12">常见架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/mongodb/mongodbdesign.png)


## <a name="13">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

发布订阅：MongoDB提供API接口用于订阅整个数据库中的修改操作
> 如Java中MongoDBClient提供了Watch()方法用来接收修改的事件

Geo地理位置的数据类型

GridFS：为Mongodb的一种存储机制，可以用来存储大型的二进制文件
> 1. 性能比较低，与文件服务器相比
> 2. 修改GridFS的文档只能先删除再新增。

聚合框架：可以对集合中的文档进行变换和组合

MapReduce：同样用于数据的聚合、映射、归约


### <a name="14">oplog</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在MongoDB中，有一个系统库“Local”，库里有一个集合“oplog.rs”，这个集合类似于binlog文件，里面记录了MongoDB的所有操作。从节点通过读取oplog.rs里的数据做到数据同步。
> oplog是local库下的一个固定集合，Secondary就是通过查看Primary 的oplog这个集合来进行复制的。每个节点都有oplog，记录这从主节点复制过来的信息，这样每个成员都可以作为同步源给其他节点。 Oplog 可以说是Mongodb Replication的纽带了。


oplog的相关字段：
- ts: 8字节的时间戳，由4字节unix timestamp + 4字节自增计数表示。这个值很重要，在选举(如master宕机时)新primary时，会选择ts最大的那个secondary作为新primary
- op：1字节的操作类型
> "i"： insert\
"u"： update\
"d"： delete\
"c"： db cmd
- "db"：声明当前数据库 (其中ns 被设置成为=>数据库名称+ '.')
- "n": no op,即空操作，其会定期执行以确保时效性
- ns：操作所在的namespace
- o：操作所对应的document，即当前操作的内容（比如更新操作时要更新的的字段和值）
- o2: 在执行更新操作时的where条件，仅限于update时才有该属性