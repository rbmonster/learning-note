# elasticsearch 
elasticsearch，基于lucene，隐藏复杂性，提供简单易用的restful api接口、java api接口（还有其他语言的api接口）
1. 分布式的文档存储引擎
2. 分布式的搜索引擎和分析引擎
3. 分布式，支持PB级数据


- 入门说明
http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html

https://www.cnblogs.com/yufeng218/p/12128538.html

## docker 安装

### elasticsearch 安装
```

[root@VM-0-16-centos ~]# docker pull elasticsearch:7.13.1
[root@VM-0-16-centos ~]# docker images

[root@VM-0-16-centos ~]# docker pull kibana:7.13.1
[root@VM-0-16-centos ~]# docker images

// 创建自定义的网络(用于连接到连接到同一网络的其他服务(例如Kibana))
[root@VM-0-16-centos ~]# docker network create elknetwork
ea5897232c9daad0c00b4b47c240ff513177a42ae0b48b770068691a99949798


[root@VM-0-16-centos ~]# docker run -it --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" -d --net elknetwork elasticsearch:7.13.1
604ee6bb8b84fc5b3f6bf590fd57f04505fcdda8539d17493b87d6d4e8272b63

// 检查es是否启动
[root@VM-0-16-centos ~]# curl 127.0.0.1:9200
{
  "name" : "604ee6bb8b84",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "tGGUtTAjSYiBu6hMSa_GzQ",
  "version" : {
    "number" : "7.13.1",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "9a7758028e4ea59bcab41c12004603c5a7dd84a9",
    "build_date" : "2021-05-28T17:40:59.346932922Z",
    "build_snapshot" : false,
    "lucene_version" : "8.8.2",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}

[root@VM-0-16-centos ~]# docker run -d --name kibana -p 5601:5601 --net elknetwork kibana:7.13.1
2dcdd5bc87bd2f6fcb2b020bd866174b760d7e43e1788c2ca7205848d7a46074

```

![avatar](https://gitee.com/rbmon/file-storage/raw/main/learning-note/other/ES.jpg)
  
参考资料： [Docker安装部署ELK教程](https://www.cnblogs.com/fbtop/p/11005469.html)
### ik分词器安装

#### 在线安装
```
// 进入容器
docker exec -it elasticsearch /bin/bash

// 在线下载并安装
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.4/elasticsearch-analysis-ik-7.13.1.zip
```

#### 离线安装

```
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.13.1/elasticsearch-analysis-ik-7.13.1.zip

docker exec -it elasticsearch /bin/bash

// 创建目录
mkdir /usr/share/elasticsearch/plugins/ik

// 将文件压缩包移动到ik中
mv /usr/share/elasticsearch/plugins/elasticsearch-analysis-ik-7.13.1.zip /usr/share/elasticsearch/plugins/ik

// 进入目录
cd /usr/share/elasticsearch/plugins/ik

// 解压
unzip elasticsearch-analysis-ik-7.13.1.zip

// 删除压缩包
rm -rf elasticsearch-analysis-ik-7.13.1.zip

exit
// 重启进行
docker restart elasticsearch
```

#### 分词器测试
```
POST _analyze 
{
  "analyzer": "ik_smart",
  "text": "的说法是的发送到"
}


result:
{
  "tokens" : [
    {
      "token" : "的",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "说法",
      "start_offset" : 1,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "是的",
      "start_offset" : 3,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "发送到",
      "start_offset" : 5,
      "end_offset" : 8,
      "type" : "CN_WORD",
      "position" : 3
    }
  ]
}

```
参考资料：[docker 安装ElasticSearch的中文分词器IK](https://blog.csdn.net/weixin_34015566/article/details/93554240)
## linux http基本操作命令
### 基本操作
```
[root@VM-0-10-centos ~]# curl -X GET 'http://localhost:9200/_cat/indices?v'

[root@VM-0-10-centos ~]# curl -X PUT 'localhost:9200/accounts'

[root@VM-0-10-centos ~]# curl -X DELETE 'localhost:9200/account'

```

### 索引创建与新增元素
```
[root@VM-0-10-centos ~]# curl -H 'content-Type:application/json'  -X PUT 'localhost:9200/test' -d '

  {
    "settings":{
      "number_of_shards":3,
      "number_of_replicas":2
    },
    "mappings":{
      "properties":{
        "id":{"type":"long"},
        "name":{"type":"text"},
        "text":{"type":"text"}
      }
    }
   
  }';

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


### 查询
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

## kibana 命令行操作

### 创建索引
```
PUT sw_test.trade_contract_v1
{
  "settings": {
    "index": {
      "number_of_shards": 2,
      "number_of_replicas": 1
    },
    "analysis": {
      "analyzer": {
        "default": {
          "type": "ik_max_word"
        },
        "default_search": {
          "type": "ik_smart"
        }
      }
    }
  },
  "aliases": {
    "sw_test.trade_contract": {}
  },
  "mappings": {
    "properties": {
      "created_time": {
        "type": "date"
      },
      "modified_time": {
        "type": "date"
      },
      "status": {
        "type": "keyword"
      },
      "contract_id": {
        "type": "keyword"
      },
      "invoice_no": {
        "type": "keyword"
      },
      "export_country": {
        "type": "keyword"
      },
      "exp_currency": {
        "type": "keyword"
      },
      "amount": {
        "type": "double"
      },
      "deleted": {
        "type": "boolean"
      },
      "extra": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword"
          }
        }
      },
      "product_name": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword"
          }
        }
      },
      "product_quantity": {
        "type": "long"
      },
      "product_quantity_unit": {
        "type": "keyword"
      },
      "note": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword"
          }
        }
      }
    }
  }
}
```

### 中文分词

[参见](https://zhuanlan.zhihu.com/p/52543633)

analysis-ik分两种模式：ik_max_word和ik_smart模式

#### ik_max_word
会将文本做最细粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为“中华人民共和国、中华人民、中华、华人、人民共和国、人民、共和国、大会堂、大会、会堂等词语。

#### ik_smart
会做最粗粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为中华人民共和国、人民大会堂。

#### 最佳实践

两种分词器使用的最佳实践是：索引时用ik_max_word，在搜索时用ik_smart。
即：索引时最大化的将文章内容分词，搜索时更精确的搜索到想要的结果。

```json
{
  "mappings": {
    "_doc": {
      "properties": {
        "firm_name": {
          "type": "text",
          "analyzer": "ik_max_word", 
          "search_analyzer": "ik_smart" 
        }
      }
    }
  }
}
```

### 手动插入数据
```
POST sw_test.trade_contract/_doc
{
    "amount":3344.00,
    "contract_id":"01990100018000018070300001149",
    "created_time":"2021-01-01",
    "custom_id":"123123",
    "deleted": false,
    "exp_currency":"USD",
    "export_country":"澳大利亚",
    "export_country_code":"CN",
    "extra":"小小",
    "invoice_no":"qa_order_20210526194123288",
    "modified_time":"2021-01-01",
    "note":"1212",
    "product_name":"apple",
    "product_quantity":1200,
    "product_quantity_unit":"kg",
    "status":"Closed",
    "test_add": "时代峰峻卡上的福建省地方"
}
```

### 查询
```
POST sw_test.trade_contract/_search
{
   "query": { 
    "bool": { 
      "must": [
        { "match": { "exp_currency":   "USD" }},
        { "match": { "product_name": "apple" }}
      ],
      "filter": [ 
        { "term":  { "export_country": "澳大利亚" }},
        { "range": { "created_time": { "gte": "2015-01-01",
                "lte" : "2022-01-01" }}}
      ],
      "should": [{
					"match": {
						"test_add": "卡上的"
					}
				}
			]
    }
  }
}
```



#### 字段类型
[Field datatypes](https://www.elastic.co/guide/en/elasticsearch/reference/7.13/mapping-types.html)

#### filter and query
[Bool Query](https://www.elastic.co/guide/en/elasticsearch/reference/6.8/query-dsl-bool-query.html)

[Query and Filter context](https://www.elastic.co/guide/en/elasticsearch/reference/7.13/query-filter-context.html#query-filter-context)

[Different with Filter and Must Not](https://stackoverflow.com/questions/47226479/difference-between-must-not-and-filter-in-elasticsearch)

Basically, filter = must but without scoring.

**filter:**

    It is written in Filter context.
    It does not affect the score of the result.
    The matched query results will appear in the result.
    Exact match based, not partial match.

**must_not:**

    It is written again on the same filter context.
    Which means it will not affect the score of the result.
    The documents matched with this condition will NOT appear in the result.
    Exact match based.

| bool     |  similar |     context      | 影响评分 | 出现在结果集 | 精确匹配 |
| :------: | :------: |      :----:      | :---: | :-----: | :-----: |
| must     | AND      | query  context   | Y | Y | Y |
| filter   | AND      | filter context   | N | Y | Y |
| should   | OR       | query context    | Y | Y | N |
| must_not | AND NOT  | filter context   | N | N | Y  |



### 索引新增字段

```
POST sw_test.trade_contract_v1/_mapping
{
  "properties": {
     "test_add":{
        "type":"text"
     }
  }
}
```
### 更改字段类型为 multi_field
创建 mapping 时，可以为keyword指定ignore_above ，用来限定字符长度。\
超过 ignore_above 的字符会被存储，但不会被全文索引。

```
PUT /sw_test.trade_contract_v1/_mapping/
{
  "properties": {
     "test_add":{
        "type":"text",
        "fields": {
          "keyword": {
            "type": "keyword",
            "ignore_above": 256
          }
        }
     }
  }
}
```
### 其他
- object类型自动映射，无需手动新增
- int、long、date等类型自动映射，可以不手动新增
- string类型会自动映射成multi_field，并使用默认分词器，建议手动修改ES mapping


### 重建索引、修改Mapping的方式

[Index Aliases](https://www.elastic.co/guide/en/elasticsearch/reference/6.8/indices-aliases.html)
[Elasticsearch如何修改Mapping结构并实现业务零停机](https://juejin.im/post/5e2d32c95188254d9032a7dd)


#### 步骤1: 建立新索引
```
PUT sw_test.trade_contract_v2
```

#### 步骤2: 复制数据
```
POST _reindex
{
    "source": {
        "index": "sw_test.trade_contract_v1"
    },
    "dest": {
        "index": "sw_test.trade_contract_v2"
    }
}
```
#### 步骤3: 修改别名关联
```
POST /_aliases
{
    "actions": [
        { "remove": { "index": " sw_test.trade_contract_v1", "alias": " sw_test.trade_contract" }},
        { "add":    { "index": " sw_test.trade_contract_v2", "alias": " sw_test.trade_contract" }}
    ]
}

```
#### 步骤4: 删除旧索引
```
DELETE  sw_test.trade_contract_v1
```

## shard & replica

```
PUT sw_test.trade_contract_v1
{
  "settings": {
    "index": {
      "number_of_shards": 2,
      "number_of_replicas": 1
    },
    ....
}
```
参考文章：
1. [shards-and-replicas-in-elasticsearch](https://stackoverflow.com/questions/15694724/shards-and-replicas-in-elasticsearch)
2. [es-glossary](https://www.elastic.co/guide/en/elasticsearch/reference/current/glossary.html)


### primary shard 主分片

When you create an index (an index is automatically created when you index the first document as well) you can define how many shards it will be composed of.\
If you don't specify a number it will have the default number of shards: 5 primaries. What does it mean?

It means that elasticsearch will create 5 primary shards that will contain your data:

```
 ____    ____    ____    ____    ____
| 1  |  | 2  |  | 3  |  | 4  |  | 5  |
|____|  |____|  |____|  |____|  |____|
```

Every time you index a document, elasticsearch will decide which primary shard is supposed to hold that document and will index it there.\
`Primary shards are not a copy of the data, they are the data!` Having multiple shards does help taking advantage of parallel processing on a single machine,\
but the whole point is that if we start another elasticsearch instance on the same cluster, `the shards will be distributed in an even way over the cluster`.

Node 1 will then hold for example only three shards:

```
 ____    ____    ____ 
| 1  |  | 2  |  | 3  |
|____|  |____|  |____|
```

Since the remaining two shards have been moved to the newly started node:

```
 ____    ____
| 4  |  | 5  |
|____|  |____|
```

Why does this happen? Because elasticsearch is a distributed search engine and this way you can make use of multiple
nodes/machines to manage big amounts of data.

Every elasticsearch index is composed of at least one primary shard since that's where the data is stored.
Every shard comes at a cost, though, therefore if you have a single node and no foreseeable growth,
just stick with a single primary shard.


### replica shard 副本分片

Another type of shard is a replica. The default is 1, meaning that every `primary shard will be copied to another shard that will contain the same data`.\
Replicas are used to increase search performance and for fail-over.\
`A replica shard is never going to be allocated on the same node where the related primary is`\
(it would pretty much be like putting a backup on the same disk as the original data).

Back to our example, with 1 replica we'll have the whole index on each node,
since 2 replica shards will be allocated on the first node, and they will contain exactly the same data as the primary shards on the second node:

`Node1`
```
 ____    ____    ____    ____    ____
| 1  |  | 2  |  | 3  |  | 4R |  | 5R |
|____|  |____|  |____|  |____|  |____|
```

Same for the second node, which will contain a copy of the primary shards on the first node:


`Node2`
```
 ____    ____    ____    ____    ____
| 1R |  | 2R |  | 3R |  | 4  |  | 5  |
|____|  |____|  |____|  |____|  |____|
```

With a setup like this, if a node `goes down`, you still have the whole index.
The replica shards will automatically become primaries, and the cluster will work properly despite the node failure, as follows:

```
 ____    ____    ____    ____    ____
| 1  |  | 2  |  | 3  |  | 4  |  | 5  |
|____|  |____|  |____|  |____|  |____|
```

## 倒排索引结构

Elasticsearch分别为每个字段都建立了一个倒排索引。比如，在“张三”、“北京市”、22 这些都是Term，而`[1，3]`就是Posting List。Posting list就是一个数组，存储了所有符合某个Term的文档ID。


## spring 集成
[spring data与elasticsearch版本对应](https://docs.spring.io/spring-data/elasticsearch/docs/4.1.9/reference/html/#preface.requirements)

[spring data 官网文档](https://docs.spring.io/spring-data/elasticsearch/docs/4.1.9/reference/html/#elasticsearch.clients)