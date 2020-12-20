<a name="index">**Index**</a>

<a href="#0">elasticsearch </a>  
&emsp;<a href="#1">1. 基本操作</a>  
&emsp;<a href="#2">2. 索引创建与新增元素</a>  
&emsp;<a href="#3">3. 查询</a>  
&emsp;<a href="#4">4. 倒排索引结构</a>  
# <a name="0">elasticsearch </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
elasticsearch，基于lucene，隐藏复杂性，提供简单易用的restful api接口、java api接口（还有其他语言的api接口）
1. 分布式的文档存储引擎
2. 分布式的搜索引擎和分析引擎
3. 分布式，支持PB级数据

- docker 安装
https://www.cnblogs.com/powerbear/p/11298135.html

- 入门说明
http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html

https://www.cnblogs.com/yufeng218/p/12128538.html

## <a name="1">基本操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
[root@VM-0-10-centos ~]# curl -X GET 'http://localhost:9200/_cat/indices?v'

[root@VM-0-10-centos ~]# curl -X PUT 'localhost:9200/accounts'

[root@VM-0-10-centos ~]# curl -X DELETE 'localhost:9200/account'

```

## <a name="2">索引创建与新增元素</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
[root@VM-0-10-centos ~]# curl -H 'content-Type:application/json'  -X PUT 'localhost:9200/test' -d '
> 
> {
>   "settings":{
>     "number_of_shards":3,
>     "number_of_replicas":2
>   },
>   "mappings":{
>     "properties":{
>       "id":{"type":"long"},
>       "name":{"type":"text"},
>       "text":{"type":"text"}
>     }
>   }
>  
> }';

[root@VM-0-10-centos ~]# curl -H 'content-Type:application/json' -X POST 'localhost:9200/test/_doc/1' -d '
{
  "name": "zhangsan",
  "desc": "databaseManager"
}' ;
{"_index":"test","_type":"_doc","_id":"1","_version":1,"result":"created","_shards":{"total":3,"successful":1,"failed":0},"_seq_no":0,"_primary_term":1}


[root@VM-0-10-centos ~]# curl -X GET 'http://localhost:9200/test/_doc/1'
{"_index":"test","_type":"_doc","_id":"1","_version":1,"_seq_no":0,"_primary_term":1,"found":true,"_source":
{
  "name": "zhangsan",
  "desc": "databaseManager"
}}

// 不带主键新增，默认es添加主键
[root@VM-0-10-centos ~]# cH 'content-Type:application/json' -X POST 'localhost:9200/test/_doc' -d '
{
  "name": "asibi",
  "desc": "soul"
}' ;
{"_index":"test","_type":"_doc","_id":"UPmoeXYBI1Dq1Op9wJWu","_version":1,"result":"created","_shards":{"total":3,"successful":1,"failed":0},"_seq_no":0,"_primary_term":1}

```


## <a name="3">查询</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
[root@VM-0-10-centos ~]# curl -X GET 'http://localhost:9200/test/_search'
{"took":391,"timed_out":false,"_shards":{"total":3,"successful":3,"skipped":0,"failed":0},"hits":{"total":{"value":3,"relation":"eq"},"max_score":1.0,"hits":[{"_index":"test","_type":"_doc","_id":"UPmoeXYBI1Dq1Op9wJWu","_score":1.0,"_source":
{
  "name": "asibi",
  "desc": "soul"
}},{"_index":"test","_type":"_doc","_id":"2","_score":1.0,"_source":
{
  "name": "lisan",
  "desc": "zooManager"
}},{"_index":"test","_type":"_doc","_id":"1","_score":1.0,"_source":
{
  "name": "zhangsan",
  "desc": "databaseManager"
}}]}}


[root@VM-0-10-centos ~]# curl -H 'content-Type:application/json' -X GET 'http://localhost:9200/test/_search' -d  '
{
"query":{
    "match": {"name":"lisan"}
  } 
}';       
{"took":21,"timed_out":false,"_shards":{"total":3,"successful":3,"skipped":0,"failed":0},"hits":{"total":{"value":1,"relation":"eq"},"max_score":0.2876821,"hits":[{"_index":"test","_type":"_doc","_id":"2","_score":0.2876821,"_source":
{
  "name": "lisan",
  "desc": "zooManager"
}}]}}


[root@VM-0-10-centos ~]# curl -H 'content-Type:application/json' -X GET 'http://localhost:9200/test/_search' -d  '
{
"query":{
    "match": {"name":"lisan zhangsan"}
  }
}';
{"took":6,"timed_out":false,"_shards":{"total":3,"successful":3,"skipped":0,"failed":0},"hits":{"total":{"value":2,"relation":"eq"},"max_score":0.2876821,"hits":[{"_index":"test","_type":"_doc","_id":"2","_score":0.2876821,"_source":
{
  "name": "lisan",
  "desc": "zooManager"
}},{"_index":"test","_type":"_doc","_id":"1","_score":0.2876821,"_source":
{
  "name": "zhangsan",
  "desc": "databaseManager"
}}]}}
```

- [官网查询说明](https://www.elastic.co/guide/cn/elasticsearch/guide/current/query-dsl-intro.html)

## <a name="4">倒排索引结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Elasticsearch分别为每个字段都建立了一个倒排索引。比如，在“张三”、“北京市”、22 这些都是Term，而`[1，3]`就是Posting List。Posting list就是一个数组，存储了所有符合某个Term的文档ID。
