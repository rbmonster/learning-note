# Mongodb

https://www.cnblogs.com/out-of-memory/p/6810411.html

https://www.cnblogs.com/yunquan/p/11174265.html

## 基本概念
MongoDB是面向文档的数据库，不是关系型数据库/。
> 基本的思路就是将原来"行"的概念换成更加灵活的"文档"模型。

### 文档
多个键及其关联的值有序地放置在一起便是文档。


### 集合
集合就是一组文档。如果是MongoDB中的文档类似于关系型数据库的行，那么集合就如同表。
> 无模式


### 数据类型

### _id和ObjectId

MongoDB中存储的文档必须有一个_id键，这个键的值可以是任何类型的，默认是个ObjectId对象。
