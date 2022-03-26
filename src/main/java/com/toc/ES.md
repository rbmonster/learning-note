<a name="index">**Index**</a>

<a href="#0">elasticsearch </a>  
&emsp;<a href="#1">1. Elasticsearch 相关概念</a>  
&emsp;&emsp;<a href="#2">1.1. 文档</a>  
&emsp;&emsp;&emsp;<a href="#3">1.1.1. 更新文档</a>  
&emsp;&emsp;&emsp;<a href="#4">1.1.2. 乐观并发控制</a>  
&emsp;&emsp;<a href="#5">1.2. 集群</a>  
&emsp;&emsp;<a href="#6">1.3. 分片</a>  
&emsp;&emsp;&emsp;<a href="#7">1.3.1. 路由过程</a>  
&emsp;&emsp;&emsp;<a href="#8">1.3.2. 主分片与副本分片交互</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#9">1.3.2.1. 新建，索引和删除文档</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#10">1.3.2.2. 查询请求交互</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#11">1.3.2.3. 局部更新文档</a>  
&emsp;&emsp;&emsp;<a href="#12">1.3.3. 分页查询工作流程</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#13">1.3.3.1. 查询阶段</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#14">1.3.3.2. 取回阶段</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#15">1.3.3.3. 搜索选项</a>  
&emsp;&emsp;&emsp;<a href="#16">1.3.4. 分片内部原理</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#17">1.3.4.1. 不变性</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#18">1.3.4.2. 动态更新索引</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#19">1.3.4.3. 持久化与段合并</a>  
&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#20">1.3.4.3.1. translog</a>  
&emsp;&emsp;&emsp;&emsp;&emsp;<a href="#21">1.3.4.3.2. 段合并</a>  
&emsp;&emsp;<a href="#22">1.4. 搜索</a>  
&emsp;&emsp;&emsp;<a href="#23">1.4.1. 搜索相关的优化</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#24">1.4.1.1. 查询语句权重改变</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#25">1.4.1.2. 最佳字段查询调优</a>  
&emsp;&emsp;<a href="#26">1.5. 索引</a>  
&emsp;&emsp;&emsp;<a href="#27">1.5.1. 索引设置</a>  
&emsp;&emsp;&emsp;<a href="#28">1.5.2. 分析器</a>  
&emsp;&emsp;&emsp;<a href="#29">1.5.3. 根对象</a>  
&emsp;&emsp;&emsp;<a href="#30">1.5.4. 动态映射</a>  
&emsp;&emsp;&emsp;<a href="#31">1.5.5. 重新索引数据、修改Mapping的方式</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#32">1.5.5.1. 索引别名与零停机</a>  
&emsp;&emsp;<a href="#33">1.6. 排序</a>  
&emsp;&emsp;<a href="#34">1.7. 深度分页</a>  
&emsp;&emsp;&emsp;<a href="#35">1.7.1. 游标查询Scroll</a>  
&emsp;&emsp;&emsp;<a href="#36">1.7.2. search after</a>  
&emsp;&emsp;&emsp;<a href="#37">1.7.3. 总结</a>  
&emsp;&emsp;&emsp;<a href="#38">1.7.4. 参考资料</a>  
&emsp;&emsp;<a href="#39">1.8. 聚合</a>  
&emsp;&emsp;<a href="#40">1.9. 相关性</a>  
&emsp;&emsp;<a href="#41">1.10. 倒排索引</a>  
&emsp;&emsp;&emsp;<a href="#42">1.10.1. 倒排索引创建</a>  
&emsp;&emsp;&emsp;<a href="#43">1.10.2. 搜索过程</a>  
&emsp;&emsp;&emsp;<a href="#44">1.10.3. 倒排索引优化</a>  
&emsp;&emsp;&emsp;<a href="#45">1.10.4. ES中分词</a>  
&emsp;&emsp;<a href="#46">1.11. Doc Values</a>  
&emsp;&emsp;&emsp;<a href="#47">1.11.1. 结构</a>  
&emsp;&emsp;&emsp;<a href="#48">1.11.2. 存储优化</a>  
&emsp;<a href="#49">2. docker 安装</a>  
&emsp;&emsp;<a href="#50">2.1. elasticsearch 安装</a>  
&emsp;&emsp;<a href="#51">2.2. ik分词器安装</a>  
&emsp;&emsp;&emsp;<a href="#52">2.2.1. 在线安装</a>  
&emsp;&emsp;&emsp;<a href="#53">2.2.2. 离线安装</a>  
&emsp;&emsp;&emsp;<a href="#54">2.2.3. 分词器测试</a>  
&emsp;<a href="#55">3. linux http基本操作命令</a>  
&emsp;&emsp;<a href="#56">3.1. 基本操作</a>  
&emsp;&emsp;<a href="#57">3.2. 索引创建与新增元素</a>  
&emsp;&emsp;<a href="#58">3.3. 查询</a>  
&emsp;<a href="#59">4. kibana 命令行操作</a>  
&emsp;&emsp;<a href="#60">4.1. 创建索引</a>  
&emsp;&emsp;<a href="#61">4.2. 中文分词</a>  
&emsp;&emsp;&emsp;<a href="#62">4.2.1. ik_max_word</a>  
&emsp;&emsp;&emsp;<a href="#63">4.2.2. ik_smart</a>  
&emsp;&emsp;&emsp;<a href="#64">4.2.3. 最佳实践</a>  
&emsp;&emsp;<a href="#65">4.3. 手动插入数据</a>  
&emsp;&emsp;<a href="#66">4.4. 查询</a>  
&emsp;&emsp;&emsp;<a href="#67">4.4.1. 字段类型</a>  
&emsp;&emsp;&emsp;<a href="#68">4.4.2. filter and query</a>  
&emsp;&emsp;<a href="#69">4.5. 索引新增字段</a>  
&emsp;&emsp;<a href="#70">4.6. 更改字段类型为 multi_field</a>  
&emsp;&emsp;<a href="#71">4.7. 其他</a>  
&emsp;<a href="#72">5. shard & replica</a>  
&emsp;&emsp;<a href="#73">5.1. primary shard 主分片</a>  
&emsp;&emsp;<a href="#74">5.2. replica shard 副本分片</a>  
&emsp;<a href="#75">6. spring 集成</a>  
&emsp;<a href="#76">7. 面试题</a>  
&emsp;&emsp;<a href="#77">7.1. ES 的分布式架构原理</a>  
# <a name="0">elasticsearch </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
elasticsearch，基于lucene，隐藏复杂性，提供简单易用的restful api接口、java api接口（还有其他语言的api接口）。
Elasticsearch 是分布式的**文档**存储。它能存储和检索复杂的数据结构——以**实时**的方式。 换句话说，一旦一个文档被存储在 Elasticsearch 中，它就是可以被集群中的任意节点检索到。
1. 分布式的文档存储引擎
2. 分布式的搜索引擎和分析引擎
3. 分布式，支持PB级数据


入门说明
- [全文搜索引擎 Elasticsearch 入门教程](http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html)
- [elasticsearch 基础 ](https://www.cnblogs.com/yufeng218/p/12128538.html)
- [Elasticsearch: 权威指南(基于2.0)](https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html)
- [Shard内部原理](http://t.zoukankan.com/guoyu1-p-13755165.html) : 不变性、`Doc`写入流程、`translog`机制


## <a name="1">Elasticsearch 相关概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

与关系型数据库相关对应概念

| relational database | elasticsearch | 
| --- | --- | 
| **database**  | **Index** |
| **Table** | **Type** |
| **Row** | **Document** |
| **Column** | **Field** |
| **Schema** | **Mapping** |
| Index | Everything is Index |
| SQL | Query DSL |
| `Select * from table` | `Get http://` |
| `Update table set` | `Put http://` |

### <a name="2">文档</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Elasticsearch 是分布式的 文档 存储。它能存储和检索复杂的数据结构—序列化成为JSON文档—以 实时 的方式。
> 一个 **对象** 是基于特定语言的内存的数据结构。为了通过网络发送或者存储它，我们需要将它表示成某种标准的格式。 JSON 是一种以人可读的文本表示对象的方法。

在 Elasticsearch 中，术语 _文档_ 有着特定的含义。它是指最顶层或者根对象, 这个根对象被序列化成 JSON 并存储到 Elasticsearch 中，指定了唯一ID。

文档元数据：一个文档不仅仅包含它的数据 ，也包含 _元数据_ —— 有关 *文档* 的信息。 三个必须的元数据元素如下：
- `_index` ：文档在哪存放。
- `_type` ：文档表示的对象类别。一个 _`type` 命名可以是大写或者小写，但是不能以下划线或者句号开头，不应该包含逗号， 并且长度限制为256个字符. 我们使用 blog 作为类型名举例。
- `_id`：文档唯一标识。ID 是一个字符串，当它和 `_index` 以及 `_type` 组合就可以唯一确定 Elasticsearch 中的一个文档。ID的生成可以自己指定或者让es自动生成
> 自动生成的 ID 是 URL-safe、 基于 Base64 编码且长度为20个字符的 GUID 字符串。 这些 GUID 字符串由可修改的 FlakeID 模式生成，这种模式允许多个节点并行生成唯一 ID ，且互相之间的冲突概率几乎为零。

```
{
   "_index":    "website",
   "_type":     "blog",
   "_id":       "AVFgSgVHUP18jI2wRx0w",
   "_version":  1,
   "created":   true
}
```

#### <a name="3">更新文档</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
ElasticSearch的文档写入是以不可修改的形式写入的，一条文档记录一旦被写入其本身就不能被修改了。如果想要更新现有的文档，需要**重建索引**或者**进行替换**。

重新索引方式：
```
PUT /test/_doc/12374
{
  "id" : 12374,
  "created_time" : 1640763610000,
  "userId" : "00990100002000021122901003385",
  "deleted" : false
}

response：
{
  "_index" : "test",
  "_type" : "_doc",
  "_id" : "12374",
  "_version" : 4,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1667,
  "_primary_term" : 4
}

```

替换方式：
```
POST test/_doc/12374/_update
{
  "doc":{
    "user_id": "sdfasasdfasdfdaddf"
  }
}

```

在内部，Elasticsearch 已将旧文档标记为已删除，并增加一个全新的文档。 尽管你不能再对旧版本的文档进行访问，但它并不会立即消失。当继续索引更多的数据，Elasticsearch 会在**后台清理这些已删除文档**。
无论是重新索引还是局部更新的方式，实际上 Elasticsearch 按前述完全相同方式执行以下过程：
1. 从旧文档构建 JSON
2. 更改该 JSON
3. 删除旧文档
4. 索引一个新文档



#### <a name="4">乐观并发控制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
利用 `_version` 号来确保 应用中相互冲突的变更不会导致数据丢失。我们通过指定想要修改文档的 `version` 号来达到这个目的。 如果该版本不是当前版本号，我们的请求将会失败。

### <a name="5">集群</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当一个节点被选举成为 **主节点**时， 它将负责管理集群范围内的所有变更，例如增加、删除索引，或者增加、删除节点等。 \
而**主节点并不需要涉及到文档级别的变更和搜索**等操作，所以当集群只拥有一个主节点的情况下，即使流量的增加它也不会成为瓶颈。 任何节点都可以成为主节点。

ES 集群多个节点，会自动选举一个节点为 master 节点，这个 master 节点其实就是干一些管理的工作的，比如维护索引元数据、负责切换 primary shard 和 replica shard 身份等。要是 master 节点宕机了，那么会重新选举一个节点为 master 节点。\
如果是非 master 节点宕机了，那么会由 master 节点，让那个宕机节点上的 primary shard 的身份转移到其他机器上的 replica shard。

集群中节点的概念：
- **master节点**：主要职责是和集群操作相关的内容，如创建或删除索引，跟踪哪些节点是群集的一部分，并决定哪些分片分配给相关的节点。
- **data节点**(主分片、副本分片)：这个节点作为一个数据节点，数据节点主要是存储索引数据的节点，主要对文档进行增删改查操作，聚合操作等。数据节点对cpu，内存，io要求较高， 在优化的时候需要监控数据节点的状态，当资源不够的时候，需要在集群中添加新的节点。**节点启动后，默认就是数据节点**。
- **协调节点**：处理请求的节点，负责路由请求到正确的节点，如创建索引的请求需要路由到 Master 节点。**所有节点默认都是** Coordinating Node；


| 节点类型	|配置参数	| 默认值 |
| ---           | ---| ---|
|master eligible	| node.master	| true|
|data	          | node.data	| true |
|ingest	          | node.ingest | true|
|coordinating only |	无 |	设置上面三个参数全部为false|

### <a name="6">分片</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="7">路由过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当索引一个文档的时候，文档会被存储到一个主分片中。确定路由的分片由以下公式确定：
`shard = hash(routing) % number_of_primary_shards`
> `routing` 是一个可变值，默认是文档的 `_id` ，也可以设置成一个自定义的值。 routing 通过 hash 函数生成一个数字，然后这个数字再除以 `number_of_primary_shards` （主分片的数量）后得到 余数 。这个分布在 0 到 `number_of_primary_shards-1` 之间的余数，就是我们所寻求的文档所在分片的位置。\
> 这就解释了为什么我们要在创建索引的时候就确定好主分片的数量 并且永远不会改变这个数量：因为如果数量变化了，那么所有之前路由的值都会无效，文档也再也找不到了。

#### <a name="8">主分片与副本分片交互</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/cluster-1.png)
>  假设有一个集群由三个节点组成。 它包含一个叫 blogs 的索引，有两个主分片，每个主分片有两个副本分片。相同分片的副本不会放在同一节点

**协调节点**(coordinating node)：可以发送请求到集群中的任一节点。 每个节点都有能力处理任意请求。每个节点都知道集群中任一文档位置，所以可以直接将请求转发到需要的节点上。假如将所有的请求发送到 Node 1 ，我们将其称为**协调节点**。

##### <a name="9">新建，索引和删除文档</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/cluster-2.png)

以下是在主副分片和任何副本分片上面 成功新建，索引和删除文档所需要的步骤顺序：
1. 客户端向 `Node 1` 发送新建、索引或者删除请求。
2. 节点使用文档的 `_id` 确定文档属于分片 0 。请求会被转发到 `Node 3`，因为分片 0 的主分片目前被分配在 `Node 3` 上。
3. `Node 3` 在主分片上面执行请求。如果成功了，它将请求并行转发到 `Node 1` 和 `Node 2` 的副本分片上。**一旦所有的副本分片都报告成功**, `Node 3` 将向协调节点报告成功，协调节点向客户端报告成功。

**相关参数设置**：
> 有一些可选的请求参数允许影响整个写过程，可能以数据安全为代价提升性能。

`consistency`：在默认设置下，即使仅仅是在试图执行一个写操作之前，主分片都会要求 必须要有 _大多数(规定数量)_ 的分片副本处于活跃可用状态，才会去执行写操作。这是为了避免在发生网络分区故障（network partition）的时候进行 _写_ 操作，进而导致数据不一致。\
> 规定数量：`int( (primary + number_of_replicas) / 2 ) + 1` \
> `number_of_replicas`指的是在索引设置中的设定副本分片数，而不是指当前处理活动状态的副本分片数。
- 设置为`one`: 只要主分片状态 ok 就允许执行 _写_ 操作
- 设置为`all`: 必须要主分片和所有副本分片的状态没问题才允许执行 _写_ 操作
- 默认值为 quorum , 即大多数的分片副本状态没问题就允许执行 _写_ 操作。

`timeout`：如果没有足够的副本分片， Elasticsearch会等待，希望更多的分片出现。默认情况下，它最多等待1分钟。
>新索引默认有 1 个副本分片，这意味着为满足 **规定数量** 应该需要两个活动的分片副本。 但是，这些默认的设置会阻止我们在单一节点上做任何事情。为了避免这个问题，要求只有当 `number_of_replicas` 大于1的时候，规定数量才会执行。

##### <a name="10">查询请求交互</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

基于ID的查询：

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/cluster-3.png)

以下是从主分片或者副本分片检索文档的步骤顺序：
1. 客户端向 `Node 1` 发送获取请求。
2. 节点使用文档的 `_id` 来确定文档属于分片 0 。分片 0 的副本分片存在于所有的三个节点上。 在这种情况下，它将请求转发到 `Node 2` 。
3. `Node 2` 将文档返回给 `Node 1` ，然后将文档返回给客户端。
> 在处理读取请求时，协调结点在每次请求的时候都会**通过轮询所有的副本分片来达到负载均衡**。\
> **分区同步导致数据可能不一致**：在文档被检索时，已经被索引的文档可能已经存在于主分片上但是还没有复制到副本分片。 在这种情况下，副本分片可能会报告文档不存在，但是主分片可能成功返回文档。



##### <a name="11">局部更新文档</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/update-1.png)

部分更新一个文档的步骤：
1. 客户端向 `Node 1` 发送更新请求。
2. 它将请求转发到主分片所在的 `Node 3` 。
3. `Node 3 `从主分片检索文档，修改 `_source` 字段中的 `JSON` ，并且尝试重新索引主分片的文档。 如果文档已经被另一个进程修改，它会重试步骤 3 ，超过 `retry_on_conflict` 次后放弃。
4. 如果 `Node 3` 成功地更新文档，它将新版本的文档并行转发到 `Node 1` 和 `Node 2` 上的副本分片，重新建立索引。 一旦所有副本分片都返回成功， `Node 3` 向协调节点也返回成功，协调节点向客户端返回成功。
> 当主分片把更改转发到副本分片时， 它不会转发更新请求。 相反，它转发完整文档的新版本。请记住，这些更改将会异步转发到副本分片，并且不能保证它们以发送它们相同的顺序到达。 如果Elasticsearch仅转发更改请求，则可能以错误的顺序应用更改，导致得到损坏的文档。


#### <a name="12">分页查询工作流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

##### <a name="13">查询阶段</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/search-1.png)
> 优先队列: 一个 优先队列 仅仅是一个存有 top-n 匹配文档的有序列表。优先队列的大小取决于分页参数 from 和 size 。例如，如下搜索请求将需要足够大的优先队列来放入100条文档。
>
> ```
>   GET /_search
>   {
>       "from": 90,
>       "size": 10
>   }
> ```

查询阶段包含以下三个步骤:
1. 客户端发送一个 `search` 请求到 Node 3 ， Node 3 会创建一个大小为 `from + size` 的空优先队列。
2. Node 3 将查询请求转发到索引的每个主分片或副本分片中。每个分片在本地执行查询并添加结果到大小为 `from + size` 的本地有序优先队列中。
3. 每个分片返回各自优先队列中所有文档的 ID 和排序值给协调节点，也就是 Node 3 ，它合并这些值到自己的优先队列中来产生一个全局排序后的结果列表。
> 当一个搜索请求被发送到某个节点时，这个节点就变成了协调节点。 这个节点的任务是广播查询请求到所有相关分片并将它们的响应整合成全局排序后的结果集合，这个结果集合会返回给客户端。\
> 分片返回一个轻量级的结果列表到协调节点，它仅包含文档 ID 集合以及任何排序需要用到的值，例如 `_score` 。


##### <a name="14">取回阶段</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/search-2.png)
分布式阶段由以下步骤构成：
1. 协调节点辨别出哪些文档需要被取回并向相关的分片提交多个 GET 请求。
2. 每个分片加载并 _丰富_ 文档，如果有需要的话，接着返回文档给协调节点。
3. 一旦所有的文档都被取回了，协调节点返回结果给客户端。
> 协调节点给持有相关文档的每个分片创建一个 multi-get request ，并发送请求给同样处理查询阶段的分片副本。


##### <a name="15">搜索选项</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**偏好 preference**：允许用来控制由哪些分片或节点来处理搜索请求。 它接受像` _primary, _primary_first, _local, _only_node:xyz, _prefer_node:xyz, _shards:2,3` 这样的值
> `Bouncing Results`：每次用户刷新页面，搜索结果表现是不同的顺序。主要指定的排序字段在不同分片上可能不一致，如`timestamp`。可以指定`preference`为用户会话Id解决该问题。

**超时问题**：查询花费的时间是**最慢分片的处理时间**加 **结果合并的时间**。参数 `timeout` 告诉分片允许处理数据的最大时间

**路由问题**：定制参数 `routing` ，它能够在索引时提供来确保相关的文档，比如属于某个用户的文档被存储在某个分片上。
> 这个技术在设计大规模搜索系统时就会派上用场


#### <a name="16">分片内部原理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
传统的数据库每个字段存储单个值，但这对全文检索并不够。文本字段中的每个单词需要被搜索，对数据库意味着需要单个字段有索引多值(这里指单词)的能力。
> 最好的支持 一个字段多个值 需求的数据结构 是倒排索引。倒排索引包含一个有序列表，列表包含所有文档出现过的不重复个体，或称为 词项 ，对于每一个词项，包含了它所有曾出现过文档的列表。

倒排索引会保存每一个词项出现过的文档总数， 在对应的文档中一个**具体词项出现的总次**数，**词项在文档中的顺序**，**每个文档的长度**，**所有文档的平均长度**，等等。这些统计信息允许 Elasticsearch 决定哪些词比其它词更重要，哪些文档比其它文档更重要。即*相关度*的信息。

倒排索引包含以下几个部分：
1. 某个关键词的doc list
2. 某个关键词的所有doc的数量IDF（inverse document frequency）
3. 某个关键词在每个doc中出现的次数：TF（term frequency）
4. 某个关键词在这个doc中的次序
5. 每个doc的长度：length norm
6. 某个关键词的所有doc的平均长度
```
Term  | Doc 1 | Doc 2 | Doc 3 | ...
------------------------------------
brown |   X   |       |  X    | ...
fox   |   X   |   X   |  X    | ...
quick |   X   |   X   |       | ...
the   |   X   |       |  X    | ...
```


##### <a name="17">不变性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
倒排索引写入磁盘后就是不可变的，这样有几个好处：
1. 不需要锁，如果不更新索引，不用担心锁的问题，可以支持较高的并发能力
2. 如果cache内存足够，不更新索引的话，索引可以一直保存在os cache中，可以提升IO性能。
3. 如果数据不变，filter cache 会一直驻留在内存。
4. 索引数据可以压缩，节省 cpu 和 io 开销。

##### <a name="18">动态更新索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
倒排索引是基于不可变模式设计的，但实际Elasticsearch源源不断地有新数据进来。ES通过增加**新的补充索引来接收新的文档和修改的文档**，而不是直接用删除重建的方式重写整个索引。

doc写入过程：

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/es-working.png)

1. 新文档先写入**内存索引缓存**
2. 当间隔一定时间（默认每秒自动刷新），将缓存的数据进行提交，这个过程会创建一个Commit Point，Commit Point包含index segment的信息。
3. 缓存的数据写入新的index segment。
4. index segment的数据先写入os-cache中
5. 等待操作系统将os-cache的数据强制刷新到磁盘中
6. 写入磁盘完成后，新的index segment被打开，此时segment内的文档可以被搜索到。
7. 同时buffer的数据被清空，等待下一次新的文档写入。
> 针对源源不断新生成的`segment`，Elasticsearch会在后台对**segment进行合并**，减少文件的数量，同时，标记为deleted的文档在合并时会被丢弃（delete请求只是将文档标记为deleted状态，真正的物理删除是在段合并的过程中），合并过程不需要人工干预，让Elasticsearch自行完成即可。

> index segment翻译过来叫"段"，每秒会创建一个，ES把这个1秒内收到的、需要处理的文档都放在这个段里，可以把段认为是倒排索引的一个子集。

索引、分片、段的关系如下：索引包含多个分片，每个分片是一个Lucene索引实例，一个分片下面有多个段。如果把分片看作是一个独立的倒排索引结构，那么这个倒排索引是由多个段文件的集合。三者之间是包含关系：**索引包含多个分片，分片包含多个段**。

当一个查询被触发，所有**已知的段(包括新增的段)**按顺序被查询。词项统计会对**所有段的结果进行聚合**，以保证每个词和每个文档的关联都被准确计算。这种方式可以用相对较低的成本将新文档添加到索引。

doc删除和更新
- 删除：当文档被删除时，`Commit Point`会把信息记录在`.del`文件中，在`.del`文件中会标识哪些文档是有deleted标记的，但该文档还是存在于原先的`index segment`文件里，同样能够被检索到，只是在最终结果处理时，标记为`deleted`的文档被会过滤掉。
- 更新：更新也是类似的操作，更新会把旧版本的文档标记为`deleted`，新的文档会存储在新的`index segment`中。

实时搜索的支持：
在Elasticsearch和磁盘之间是文件系统缓存。在内存索引缓冲区中的文档会被写入到一个新的段中。Lucene 允许新段被**写入和打开—使其包含的文档在未进行一次完整提交时便对搜索可见**。 这种方式比进行一次提交代价要小得多，并且在不影响性能的前提下可以被频繁地执行。

##### <a name="19">持久化与段合并</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/es-rep-1.png)
Elasticsearch 增加了一个 translog ，或者叫事务日志，在每一次对 Elasticsearch 进行操作时均进行了日志记录。通过 translog ，整个流程看起来是下面这样：
1. 一个文档被索引之后，就会被添加到内存缓冲区，并且追加到了`translog`。
2. 刷新（refresh）使分片处于的`In-memory buffer`被清空但是事务日志不会情况的状态，分片每秒被刷新（`refresh`）一次。
3. 这个进程继续工作，更多的文档被添加到内存缓冲区和追加到事务日志.
4. 每隔一段时间，例如`translog` 变得越来越大、索引被刷新（flush）；一个新的 `translog` 被创建，并且一个全量提交被执行

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/es-rep-2.png)
执行一个提交并且截断 `translog` 的行为在 Elasticsearch 被称作一次 `flush` 。 分片每30分钟被自动刷新（`flush`），或者在 `translog` 太大的时候也会刷新。具体流程：
1. 所有在内存缓冲区的文档都被写入一个新的段
2. 缓冲区被清空
3. 一个提交点被写入硬盘
4. 文件系统缓存通过 `fsync` 被刷新（`flush`）
5. 老的 `translog` 被删除


###### <a name="20">translog</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**相关流程**：\
`translog` 提供所有还没有被刷到磁盘的操作的一个持久化纪录。当 Elasticsearch 启动的时候， 它会从磁盘中使用最后一个提交点去恢复已知的段，并且会重放 `translog` 中所有在最后一次提交后发生的变更操作。\
`translog` 也被用来提供实时 CRUD 。当你试着通过ID查询、更新、删除一个文档，它会在尝试从相应的段中检索之前， 首先检查 `translog` 任何最近的变更。这意味着它总是能够实时地获取到文档的最新版本。


`translog` 的目的是保证操作不会丢失。`Translog` 有多安全？
> 在文件被 `fsync` 到磁盘前，被写入的文件在重启之后就会丢失。默认 `translog` 是每 5 秒被 `fsync` 刷新到硬盘， 或者在每次写请求完成之后执行(`e.g. index, delete, update, bulk`)。这个过程在主分片和复制分片都会发生。最终， 基本上，这意味着在整个请求被 `fsync` 到主分片和复制分片的`translog`之前，你的客户端不会得到一个 200 OK 响应。\
> 在每次请求后都执行一个 `fsync` 会带来一些性能损失，尽管实践表明这种损失相对较小（特别是`bulk`导入，它在一次请求中平摊了大量文档的开销）

针对索引单独设置，并且可以动态进行修改。默认的参数（` "index.translog.durability": "request"` ）可以避免数据丢失。
```
PUT /my_index/_settings
{
    "index.translog.durability": "async",
    "index.translog.sync_interval": "5s"
}
```

###### <a name="21">段合并</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 背景：由于自动刷新流程每秒会创建一个新的段 ，这样会导致短时间内的段数量暴增。而段数目太多会带来较大的麻烦。 每一个段都会消耗文件句柄、内存和cpu运行周期。更重要的是，每个搜索请求都必须轮流检查每个段；所以段越多，搜索也就越慢。

Elasticsearch通过在后台进行段合并来解决这个问题。小的段被合并到大的段，然后这些大的段再被合并到更大的段。

段合并的时候会将那些**旧的已删除文档从文件系统中清除**。被删除的文档（或被更新文档的旧版本）**不会被拷贝到新的大段**中。

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/segment-merge.png)
进行索引和搜索时会自动进行段合并
1. 当索引的时候，刷新（refresh）操作会创建新的段并将段打开以供搜索使用。
2. 合并进程选择一小部分大小相似的段，并且在后台将它们合并到更大的段中。这并不会中断索引和搜索。
3. 新的段被刷新（flush）到了磁盘， 写入一个包含新段且排除旧的和较小的段的新提交点。 然后新的段被打开用来搜索。最终老的段被删除。
> 合并大的段需要消耗大量的I/O和CPU资源，如果任其发展会影响搜索性能。Elasticsearch在默认情况下会对合并流程进行资源限制，所以搜索仍然 有足够的资源很好地执行。

### <a name="22">搜索</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一个查询中常见的字段：
```
{
	"took": 2,
	"timed_out": false,
	"_shards": {
		"total": 2,
		"successful": 2,
		"skipped": 0,
		"failed": 0
	},
	"hits": {
		"total": 1088,
		"max_score": null,
		"hits": [{
			"_index": "test",
			"_type": "_doc",
			"_id": "12374",
			"_score": null,
			"_source": {
			    .....
			}
		}]
	}
}
```
- `his`: 在 hits 数组中每个结果包含文档的 `_index` 、 `_type` 、 `_id` ，加上 `_source` 字段。这意味着我们可以直接从返回的搜索结果中使用整个文档。
- `took` 值告诉我们执行整个搜索请求耗费了多少毫秒。
- `timed_out` 值告诉我们查询是否超时。默认情况下，搜索请求不会超时。该值可以手工指定。
- `_shards` 部分告诉我们在查询中参与分片的总数，以及这些分片成功了多少个失败了多少个。
> 分片失败是可能发生的。如果我们遭遇到一种灾难级别的故障，在这个故障中丢失了相同分片的原始数据和副本，那么对这个分片将没有可用副本来对搜索请求作出响应。假若这样，Elasticsearch 将报告这个分片是失败的，但是会继续返回剩余分片的结果。


**多索引多类型搜索**：
- `/gb,us/_search`：在 gb 和 us 索引中搜索所有的文档
- `/g*,u*/_search`：在任何以 g 或者 u 开头的索引中搜索所有的类型
- `/gb,us/user,tweet/_search`：在 gb 和 us 索引中搜索 user 和 tweet 类型


#### <a name="23">搜索相关的优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
##### <a name="24">查询语句权重改变</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
`boost` 参数被用来提升一个语句的相对权重（ `boost` 值大于 1 ）或降低相对权重（ `boost` 值处于 0 到 1 之间）

```
GET /_search
{
    "query": {
        "bool": {
            "must": {
                "match": {  
                    "content": {
                        "query":    "full text search",
                        "operator": "and"
                    }
                }
            },
            "should": [
                { "match": {
                    "content": {
                        "query": "Elasticsearch",
                        "boost": 3 
                    }
                }},
                { "match": {
                    "content": {
                        "query": "Lucene",
                        "boost": 2 
                    }
                }}
            ]
        }
    }
}
```

##### <a name="25">最佳字段查询调优</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
指定 `tie_breaker` 这个参数将其他匹配语句的评分也考虑其中。
- [最佳字段查询调优](https://www.elastic.co/guide/cn/elasticsearch/guide/2.x/_tuning_best_fields_queries.html)



### <a name="26">索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
创建索引的方式分为两种
1. 通过索引文档的方式直接创建一个新的索引，新的字段通过动态映射的方式被添加到类型映射中。
2. 手动创建索引

```
索引文档：
PUT /website/blog/123
{
  "title": "My first blog entry",
  "text":  "Just trying this out...",
  "date":  "2014/01/01"
}

reponse：
{
  "_index" : "website",
  "_type" : "blog",
  "_id" : "123",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

创建索引：
PUT /my_index
{
    "settings": { ... any settings ... },
    "mappings": {
        "type_one": { ... any mappings ... },
        "type_two": { ... any mappings ... },
        ...
    }
}
```

#### <a name="27">索引设置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- `number_of_shards`： 每个索引的主分片数，默认值是 5 。这个配置在**索引创建后不能修改**。
- `number_of_replicas`： 每个主分片的副本数，默认值是 1 。对于活动的索引库，这个**配置可以随时修改**。

```
PUT /my_temp_index
{
    "settings": {
        "number_of_shards" :   1,
        "number_of_replicas" : 0
    }
}

PUT /my_temp_index/_settings
{
    "number_of_replicas": 1
}
```

`analysis` 部分，用来配置已存在的分析器或针对你的索引创建新的自定义分析器。


`standard` 分析器是用于**全文字段的默认分析器**，对于大部分西方语系来说是一个不错的选择。 它包括了以下几点：
- `standard` 分词器，通过单词边界分割输入的文本。
- `standard` 语汇单元过滤器，目的是整理分词器触发的语汇单元（但是目前什么都没做）。
- `lowercase` 语汇单元过滤器，转换所有的语汇单元为小写。
- `stop` 语汇单元过滤器，删除停用词—对搜索相关性影响不大的常用词，如 `a , the , and , is `。


```
PUT /spanish_docs
{
    "settings": {
        "analysis": {
            "analyzer": {
                "es_std": {
                    "type":      "standard",
                    "stopwords": "_spanish_"
                }
            }
        }
    }
}
```


#### <a name="28">分析器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一个 分析器 就是在一个包里面组合了三种函数的一个包装器， 三种函数按照顺序被执行:
- 字符过滤器： 用来 _整理_ 一个尚未被分词的字符串。例如，如果我们的文本是HTML格式的，它会包含像 `<p>` 或者 `<div>` 这样的HTML标签。
- 分词器： 一个分析器**必须**有一个**唯一的分词器**。 分词器把字符串分解成单个词条或者词汇单元。
- 词单元过滤器：经过分词，作为结果的 词单元流 会按照指定的顺序通过指定的词单元过滤器 。词单元过滤器可以修改、添加或者移除词单元。如移除变音符、部分匹配或者自动补全的词单元。
> ES 还支持自定义分析器


#### <a name="29">根对象</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
映射的最高一层被称为 根对象 ，它可能包含下面几项：
- 一个 `properties` 节点，列出了文档中可能包含的每个字段的映射
- 各种元数据字段，它们都以一个下划线开头，例如 `_type` 、 `_id` 和 `_source`
- 设置项，控制如何动态处理新的字段，例如 `analyzer` 、 `dynamic_date_formats` 和 `dynamic_templates`
- 其他设置，可以同时应用在根对象和其他 `object` 类型的字段上，例如 `enabled` 、 `dynamic` 和 `include_in_all`


**元数据:_source字段**

默认地，Elasticsearch 在 `_source` 字段存储代表文档体的JSON字符串。和所有被存储的字段一样， `_source` 字段在被写入磁盘之前先会被压缩。
- 搜索结果包括了整个可用的文档——不需要额外的从另一个的数据仓库来取文档。
- 调试查询语句更加简单，因为你可以直接看到每个文档包括什么，而不是从一列id猜测它们的内容。

#### <a name="30">动态映射</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当 Elasticsearch 遇到文档中以前 未遇到的字段，它用 dynamic mapping 来确定字段的数据类型并自动把新的字段添加到类型映射。
> 如果Elasticsearch是作为重要的数据存储，可能就会期望遇到新字段就会抛出异常，这样能及时发现问题。 可以用 dynamic 配置来控制这种行为。true：动态添加新的字段—缺省，false：忽略新的字段，strict：如果遇到新字段抛出异常

#### <a name="31">重新索引数据、修改Mapping的方式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
尽管可以增加新的类型到索引中，或者增加新的字段到类型中，但是不能添加**新的分析器或者对现有的字段做改动**。

使用`_reindex`的原因： 
1. 是一个字段的类型进行修改之后，ES会重新建立对这个字段的索引信息，ElasticsSearch的底层是Lucene库，字段类型修改会涉及到分词方式，相关度，TF/IDF等倒排的生成，所以是没办法修改的了。
2. 当数据量过大，而索引最初创建的分片数量不足，导致数据入库较慢的情况，此时需要扩大分片的数量，此时可以尝试使用Reindex。
> 可以通过请求`POST _tasks?detailed=true&actions=*reindex` 查看`_reIndex`进度
- [Index Aliases](https://www.elastic.co/guide/en/elasticsearch/reference/6.8/indices-aliases.html)
- [Elasticsearch如何修改Mapping结构并实现业务零停机](https://juejin.im/post/5e2d32c95188254d9032a7dd)



reindex的核心是跨索引、跨集群的数据迁移。 如果出现`reindex`慢的原因及优化思路无非包括：
1. 批量大小值可能太小。需要结合堆内存、线程池调整大小；默认情况下，_reindex使用1000进行批量操作。
2. 临时调整副本数为0。一个创建请求，在主分片创建了文档后，会转发请求到副本所在到节点，待副本创建成功后，才返回给客户端。
3. reindex的底层是scroll实现，借助scroll并行优化方式，提升效率；
4. 跨索引、跨集群的核心是写入数据，考虑写入优化角度提升效率。

##### <a name="32">索引别名与零停机</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
重建索引的问题是必须更新应用中的索引名称。 索引别名就是用来解决这个问题的。**索引别名**就像一个快捷方式或软连接，可以指向一个或多个索引，也可以给任何一个需要索引名的API来使用。

**步骤1: 建立新索引**
```
PUT sw_test.trade_contract_v2
```

**步骤2: 复制数据**
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

**步骤3: 修改别名关联**
```
POST /_aliases
{
    "actions": [
        { "remove": { "index": " sw_test.trade_contract_v1", "alias": " sw_test.trade_contract" }},
        { "add":    { "index": " sw_test.trade_contract_v2", "alias": " sw_test.trade_contract" }}
    ]
}

```

**步骤4: 删除旧索引**
```
DELETE  sw_test.trade_contract_v1
```


### <a name="33">排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
为了按照**相关性**来排序，需要将相关性表示为一个数值。在 Elasticsearch 中，**相关性得分**由一个浮点数进行表示，并在搜索结果中通过 `_score` 参数返回， 默认排序是 `_score` 降序。


执行一条关于**日期排序**的查询，结果如下：
```
GET /_search
{
    "query" : {
        "bool" : {
            "filter" : {
                "term" : {
                    "user_id" : 1
                }
            }
        }
    }
}

"hits" : {
    "total" :           6,
    "max_score" :       null, 
    "hits" : [ {
        "_index" :      "us",
        "_type" :       "tweet",
        "_id" :         "14",
        "_score" :      null, 
        "_source" :     {
             "date":    "2014-09-24",
             ...
        },
        "sort" :        [ 1411516800000 ] 
    },
    ...
}
```
- `_score`: 不被计算, 因为它并没有用于排序。
- `sort`: 在每个结果中有一个新的名为 sort 的元素，它包含了我们用于排序的值。


### <a name="34">深度分页</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
理解为什么深度分页是有问题的？\
> 我们可以假设在一个有 5 个主分片的索引中搜索。 当我们请求结果的第一页（结果从 1 到 10 ），每一个分片产生前 10 的结果，并且返回给 协调节点 ，协调节点对 50 个结果排序得到全部结果的前 10 个。\
> 现在假设我们请求第 1000 页—结果从 10001 到 10010 。所有都以相同的方式工作除了每个分片不得不产生前10010个结果以外。 然后协调节点对全部 50050 个结果排序最后丢弃掉这些结果中的 50040 个结果。\
> 可以看到，在分布式系统中，对结果排序的成本随分页的深度成指数上升。这就是 web 搜索引擎对任何查询都不要返回超过 1000 个结果的原因。


> 常规的查询中： 先查后取的过程支持用 `from、size` 参数分页，但是这是 有限制的 。 要记住需要传递信息给协调节点的每个分片必须先创建一个 `from + size` 长度的队列，协调节点需要根据 `number_of_shards * (from + size)` 排序文档，来找到被包含在 size 里的文档。\
> 是使用足够大的 from 值，排序过程可能会变得非常沉重，使用大量的CPU、内存和带宽。因为这个原因，我们强烈建议你不要使用深分页。\
> 实际上，“深分页” 很少符合人的行为。当2到3页过去以后，人会停止翻页，并且改变搜索标准。会不知疲倦地一页一页的获取网页直到你的服务崩溃的罪魁祸首一般是机器人或者web spider。

Es设置了 `max_result_window`(最大结果窗口)的参数，默认值是10000，它不仅限制了用户在一次查询中最多数据条数是1w条，并且限制了start+size 必须小于1w。

#### <a name="35">游标查询Scroll</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
`scroll` 查询 可以用来对 Elasticsearch 有效地执行大批量的文档查询，而又不用付出深度分页那种代价。游标查询允许先做查询初始化，然后再批量地拉取结果。这有点儿像传统数据库中的`cursor`。
> 游标查询会取某个时间点的快照数据，并保存搜索的`search context`。查询初始化之后索引上的任何变化会被它忽略。 它通过保存旧的数据文件来实现这个特性，结果就像保留初始化时的索引 _视图_ 一样。\
> 在游标查询中，每个shard 它是通过lastEmittedDoc来确定游标位置的。\
> **Scroll不适合用于实时的搜索，因为scroll的搜索内容是基于快照的。**

**相关参数：**
- `search.max_open_scroll_context` ：用于防止开启过多的scroll request，默认500
- `scroll_id`: `_scroll_id`在每次查询的时候可能会发生变化，所以在下次查询的时候都要带上上次查询的`_socrll_id`。
- `scroll`：存活时间，快照的存活时间，用于告诉ES，快照及`search context`要保存多长时间。每次请求后，就会根据参数**刷新该时间重新计时**。
    > 何时释放快照及`search context`？ 超时、调用`clear scroll`


请求流程：
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/scroll-1.png)

1. search阶段：第一次带查询参数的请求
```
// scroll=1m 保持游标查询窗口一分钟。
GET /old_index/_search?scroll=1m 
{
    "query": { "match_all": {}},
    "sort" : ["_doc"],  // 关键字 _doc 是最有效的排序顺序。
    "size":  1000
}

result:
{
	"_scroll_id": "DnF1ZXJ5VGhlbkZldGNoAgAAAAAAAaE_Fmc0Skc2OW9DUkZlQy1XX2w0eUZMbUEAAAAAAAGhPhZnNEpHNjlvQ1JGZUMtV19sNHlGTG1B",
	"took": 13,
	"timed_out": false,
	"_shards": {
		"total": 2,
		"successful": 2,
		"skipped": 0,
		"failed": 0
	},
	"hits": {
		"total": 1091,
		"max_score": null,
		"hits": [
		    { .... }
		]
	}
}
```

2. scroll阶段：第二次带scrollId的请求
> 第二阶段Scroll请求则大大简化，Search中的许多流程都不要再次进行，仅需要执行query、fetch、response三个阶段。而完整的search请求包含rewrite、can_match、dfs、query、fetch、dfs_query、expand、response等复杂的流程
```
GET /_search/scroll
{
    "scroll": "1m", 
    "scroll_id" : "DnF1ZXJ5VGhlbkZldGNoAgAAAAAAAaE_Fmc0Skc2OW9DUkZlQy1XX2w0eUZMbUEAAAAAAAGhPhZnNEpHNjlvQ1JGZUMtV19sNHlGTG1B"

}


{
  "_scroll_id" : "DnF1ZXJ5VGhlbkZldGNoAgAAAAAAAaE_Fmc0Skc2OW9DUkZlQy1XX2w0eUZMbUEAAAAAAAGhPhZnNEpHNjlvQ1JGZUMtV19sNHlGTG1B",
  "took" : 9,
  "timed_out" : false,
  "_shards" : {
    "total" : 2,
    "successful" : 2,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1091,
    "max_score" : null,
    "hits" : [
      {...}
      ]
	}
}
```

#### <a name="36">search after</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Search接口另一种翻页方式是SearchAfter，时间复杂度O(n)，空间复杂度O(1)。SearchAfter是一种动态指针的技术，每次查询都会携带上一次的排序值，这样下次取结果只需要从上次的位点继续扫数据，前提条件也是该字段是数值类型且设置了docValue。
> 举个例子，假设"val_1"是数值类型的字段，然后使用Search接口查询时候添加Sort("val_1")，那么response中可以拿到最后一条数据的"val_1"的值，也就是response中sort字段的值，然后下次查询将该值放在query中的searchAfter参数中，下次查询就可以在上一次结果之后继续查询，如此反复，最后可以翻页很深，内存消耗相比size+from的方式降低了数倍。该方式效果类似于我们直接在bool查询中主动加一个rangeFilter，可以达到类似的效果。\
> 表面看这种方案能将查询速度降到O(1)的复杂度，实际上其内部还是会扫sort字段的docValue，翻页越深，则扫docValve越多，因此复杂度和翻页深度成正比，越往后查询越慢，但是相比size+from的方式，至少可以完成深度翻页的任务，不至于OOM，速度勉强可以接受。SearchAfter的翻页方式在性能上有了质的提升，但是其限制了用户只能一页一页往后翻，无法跳页，因此很多产品在功能设计时候是不允许跳页的，只能一页一页往后翻，也是有一定的技术原因的。



使用search after 要求每次查询都使用相同的query和sort参数。
> 如果在查询的过程中ES触发了refresh(refresh实现的是文档从内存移到文件系统缓存的过程)，查询的顺序改变导致分页的结果不准确，可以调用PIT(Point in time)API来避免这个场景。
> PIT会创建一个轻量级的视图，保证了查询的时候不会因为refresh导致分页数据不一致的情况。


```
POST twitter/_search 
{ 
    "size": 10, 
    "query": { 
        "match" : { 
            "title" : "es" 
        } 
    }, 
    "sort": [ 
        {"date": "asc"}, 
        {"_id": "desc"} 
    ] 
} 

response:
{
	"took": 29,
	"timed_out": false,
	"_shards": {
		"total": 1,
		"successful": 1,
		"skipped": 0,
		"failed": 0
	},
	"hits": {
		"total": {
			"value": 5,
			"relation": "eq"
		},
		"max_score": null,
		"hits": [{
			"_source": {},
			"sort": [
				124648691,
				"624812"
			]
		}]
	}
}
```

第二次查询：
```
GET twitter/_search 
{ 
    "size": 10, 
    "query": { 
        "match" : { 
            "title" : "es" 
        } 
    }, 
    "search_after": [124648691, "624812"], 
    "sort": [ 
        {"date": "asc"}, 
        {"_id": "desc"} 
    ] 
} 
```

#### <a name="37">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- `from + size`:灵活性好，实现简单 深度分页问题 数据量比较小，能容忍深度分页问题
- `scroll`: 解决了深度分页问题 无法反应数据的实时性(快照版本)维护成本高，需要维护一个 scroll_id 海量数据的导出需要查询海量结果集的数据
- `search_after` : 性能最好不存在深度分页问题能够反映数据的实时变更 实现复杂，需要有一个全局唯一的字段连续分页的实现会比较复杂，因为每一次查询都需要上次查询的结果，它不适用于大幅度跳页查询 海量数据的分页
> 在7.*版本中，ES官方不再推荐使用Scroll方法来进行深分页，而是推荐使用带PIT的search_after来进行查询;


**search after 对比 scroll：**
优点：
1. 无状态查询，可以防止在查询过程中，数据的变更无法及时反映到查询中。
2. 不需要维护scroll_id，不需要维护快照，因此可以避免消耗大量的资源。

缺点：
1. 由于无状态查询，因此在查询期间的变更可能会导致跨页面的不一值，需要靠PIT保障。
2. 排序顺序可能会在执行期间发生变化，具体取决于索引的更新和删除。
3. 至少需要制定一个唯一的不重复字段来排序。
4. 它不适用于大幅度跳页查询，或者全量导出，对第N页的跳转查询相当于对es不断重复的执行N次search after，而全量导出则是在短时间内执行大量的重复查询。

SEARCH_AFTER不是自由跳转到任意页面的解决方案，而是并行滚动多个查询的解决方案。

#### <a name="38">参考资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [Elasticsearch 5.x 源码分析（3）from size, scroll 和 search after](https://www.jianshu.com/p/91d03b16af77)
- [Elasticsearch之SearchScroll原理剖析和优化](https://developer.aliyun.com/article/771575)
- [ElasticSearch深度分页解决方案](https://developer.51cto.com/article/684507.html)

### <a name="39">聚合</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
聚合允许我们向数据提出一些复杂的问题。虽然功能完全不同于搜索，但它使用相同的数据结构。这意味着聚合的执行速度很快并且就像搜索一样几乎是实时的。

聚合相关操作：
> 汽车经销商可能会想知道哪个颜色的汽车销量最好，用聚合可以轻易得到结果，用 terms 桶操作
```
GET /cars/transactions/_search
{
    "size" : 0,
    "aggs" : { 
        "popular_colors" : { 
            "terms" : { 
              "field" : "color"
            }
        }
    }
}

聚合查询response：
{
...
   "hits": {
      "hits": [] 
   },
   "aggregations": {
      "popular_colors": { 
         "buckets": [
            {
               "key": "red", 
               "doc_count": 4 
            },
            {
               "key": "blue",
               "doc_count": 2
            },
            {
               "key": "green",
               "doc_count": 2
            }
         ]
      }
   }
}
```

聚合的过滤：
- 在 `filter` 过滤中的 `non-scoring` 查询，同时影响搜索结果和聚合结果。
- `filter` 桶影响聚合。
- `post_filter` 只影响搜索结果。


### <a name="40">相关性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
每个文档都有相关性评分，用一个正浮点数字段 `_score` 来表示 。 `_score` 的评分越高，相关性越高。

查询语句会为每个文档生成一个 `_score` 字段。\
评分的计算方式取决于查询类型，不同的查询语句用于不同的目的：
- fuzzy 查询会计算与关键词的拼写相似程度
- terms 查询会计算 找到的内容与关键词组成部分匹配的百分比
> 通常我们说的 relevance 是我们用来计算全文本字段的值相对于全文本检索词相似程度的算法。

Elasticsearch 的相似度算法被定义为检索词频率/反向文档频率， TF/IDF ，包括以下内容：
- 检索词频率： 检索词在该字段出现的频率。出现频率越高，相关性也越高。 字段中出现过 5 次要比只出现过 1 次的相关性高。
- 反向文档频率：每个检索词在索引中出现的频率。频率越高，相关性越低。检索词出现在多数文档中会比出现在少数文档中的权重更低。
- 字段长度准则：字段的长度是多少。长度越长，相关性越低。 检索词出现在一个短的 title 要比同样的词出现在一个长的 content 字段权重更大。

**评分的标准可以通过`_explain`进行进一步了解**
```
GET /_search?explain 
{
   "query"   : { "match" : { "tweet" : "honeymoon" }}
}

结果：
"_explanation": { 
   "description": "weight(tweet:honeymoon in 0)
                  [PerFieldSimilarity], result of:",
   "value":       0.076713204,
   "details": [
      {
         "description": "fieldWeight in 0, product of:",
         "value":       0.076713204,
         "details": [
            {  
                // 检索词频率。`honeymoon` 在这个文档的 `tweet` 字段中的出现次数。
               "description": "tf(freq=1.0), with freq of:", 
               "value":       1,
               "details": [
                  {
                     "description": "termFreq=1.0",
                     "value":       1
                  }
               ]
            },
            { 
              // 反向文档频率。检索词 `honeymoon` 在索引上所有文档的 `tweet` 字段中出现的次数。
               "description": "idf(docFreq=1, maxDocs=1)",   
               "value":       0.30685282
            },
            { 
                // 字段长度准则。在这个文档中， `tweet` 字段内容的长度 -- 内容越长，值越小。
               "description": "fieldNorm(doc=0)",    
               "value":        0.25,
            }
         ]
      }
   ]
}
```


### <a name="41">倒排索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
倒排索引：是一种可以根据属性的值来查找记录的索引。这种索引表中的每一项都包括一个属性值和具有该属性值的各条记录的地址。由于不是由记录来确定属性值，而是由属性值来确定记录的位置，故成为倒排索引。
> 经常被用来存储在全文搜索下某个单词在一个文档或者一组文档中的存储位置的映射。它是文档检索系统中最常用的数据结构。


#### <a name="42">倒排索引创建</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
例如，假设我们有两个文档，每个文档的 content 域包含如下内容：
- position 1: The quick brown fox jumped over the lazy dog
- position 2: Quick brown foxes leap over lazy dogs in summer

**倒排索引创建过程**：
1. 首先把所有的原始数据进行编号，形成文档列表 DocId
2. 把文档数据进行分词 Term，得到很多的词条，以词条为索引。保存包含这些词条的文档的编号信息。


#### <a name="43">搜索过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当用户输入任意的词条时，首先对用户输入的数据进行分词，得到用户要搜索的所有词条，然后拿着这些词条去倒排索引列表中进行匹配。找到这些词条就能找到包含这些词条的所有文档的编号。

|**Term**    |  Doc_1  | Doc_2| Posting list|
| --- | --- | ---|  --- |
|Quick   |       |  X| 2|
|The     |   X   | | 1|
|brown   |   X   |  X| 1,2|
|dog     |   X   | | 1|
|dogs    |       |  X| 2|
|fox     |   X   | | 1|
|foxes   |       |  X| 2|
|in      |       |  X| 2|
|jumped  |   X   | | 1|
|lazy    |   X   |  X| 1,2|
|leap    |       |  X| 2|
|over    |   X   |  X| 1,2|
|quick   |   X   | | 1|
|summer  |       |  X| 2|
|the     |   X   |  | 1|

搜索 quick brown

Term    |  Doc_1 |  Doc_2 |
 ---| ---| ---|
brown   |   X   |  X
quick   |   X   |
Total   |   2   |  1

两个文档都匹配，但是第一个文档比第二个匹配度更高。优先排在前面。

#### <a name="44">倒排索引优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
倒排索引的在输入或者构建的分词中，经常有以下问题
1. 文本词条化：如带上撇号的格式——“Teacher’s office”，连字符格式——“English-speaking”,也需要进行对应的处理，把单词提取出来。
2. 停用词过滤：如英文：the, is, and，中文：的、是、个等等
3. 词条归一化：如英文：color、colour。
4. 词干提取、词形还原：如英文将“doing”、“done”、“did”转化成原型“do”


#### <a name="45">ES中分词</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

ES在处理输入的内容是通过分词器处理的。分析器处理将过三个步骤：
1. 字符过滤器: 首先，字符串按顺序通过每个 字符过滤器 。他们的任务是在分词前整理字符串。一个字符过滤器可以用来去掉HTML，或者将 & 转化成 and。
2. 分词器:其次，字符串被 分词器 分为单个的词条。一个简单的分词器遇到空格和标点的时候，可能会将文本拆分成词条。
3. Token过滤器： 最后，词条按顺序通过每个 token 过滤器 。这个过程可能会改变词条（例如，小写化 Quick ），删除词条（例如， 像 a， and， the 等无用词），或者增加词条（例如，像 jump 和 leap 这种同义词）。


Elasticsearch的分词器的一般工作流程：
1. 切分关键词
2. 去除停用词
3. 对于英文单词，把所有字母转为小写（搜索时不区分大小写）

参考资料：
- [搜索引擎之倒排索引解读](https://zhuanlan.zhihu.com/p/28320841)

### <a name="46">Doc Values</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在 Elasticsearch 中，`Doc Values` 就是一种**列式存储结构**，默认情况下每个字段的 `Doc Values` 都是激活的，`Doc Values` 是在索引时创建的，当字段索引时，Elasticsearch 为了能够快速检索，会把字段的值加入倒排索引中，同时它也会存储该字段的 `Doc Values`。

Elasticsearch 中的 Doc Values 常被应用到以下场景：
- 对一个字段进行**排序**
- 对一个字段进行**聚合**
- 某些过滤，比如地理位置过滤
- 某些与字段相关的脚本计算
> 对于聚合部分，我们需要找到 Doc_1 和 Doc_2 里所有唯一的词项。\
> 对于排序部分，我们需要找到 Doc 中对应的排序词项

#### <a name="47">结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Doc values 的存在是因为倒排索引只对某些操作是高效的。 倒排索引的优势**在于查找包含某个项的文档，而对于从另外一个方向的相反操作并不高效**，即：确定哪些项是否存在单个文档里。


**倒排索引**：

|**Term**    |  Doc_1  | Doc_2| Posting list|
| --- | --- | ---|  --- |
|Quick   |       |  X| 2|
|The     |   X   | | 1|
|brown   |   X   |  X| 1,2|
|dog     |   X   | | 1|
|dogs    |       |  X| 2|
|fox     |   X   | | 1|
|foxes   |       |  X| 2|
|in      |       |  X| 2|
|jumped  |   X   | | 1|
|lazy    |   X   |  X| 1,2|
|leap    |       |  X| 2|
|over    |   X   |  X| 1,2|
|quick   |   X   | | 1|
|summer  |       |  X| 2|
|the     |   X   |  | 1|


`Doc Value`**转置**了倒排索引中词项 `term` 与文档`Doc`之间的关系：

| Doc    |  Terms |
| ---| ---|
|Doc_1 | brown, dog, fox, jumped, lazy, over, quick, the |
|Doc_2 | brown, dogs, foxes, in, lazy, leap, over, quick, summer|
|Doc_3 | dog, dogs, fox, jumped, over, quick, the|


#### <a name="48">存储优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
从广义来说，Doc Values 本质上是一个**序列化**的 列式存储。\
压缩式存储：序列化的存储方式非常便于压缩，特别是数字类型。这样可以**减少磁盘空间并且提高访问速度**。现代 CPU 的处理速度要比磁盘快几个数量级，减少直接存磁盘读取数据的大小，额外消耗 CPU 运算用来进行解压。
> 存储的策略是通过借用CPU的处理速度，来提高整体的存取效率。CPU压缩，减少存储磁盘空间。磁盘空间小读取的速度变快，CPU进行解压。。

## <a name="49">docker 安装</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="50">elasticsearch 安装</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/es/ES.jpg)
  
参考资料： [Docker安装部署ELK教程](https://www.cnblogs.com/fbtop/p/11005469.html)
### <a name="51">ik分词器安装</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="52">在线安装</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
// 进入容器
docker exec -it elasticsearch /bin/bash

// 在线下载并安装
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.4/elasticsearch-analysis-ik-7.13.1.zip
```

#### <a name="53">离线安装</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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

#### <a name="54">分词器测试</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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
## <a name="55">linux http基本操作命令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="56">基本操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
[root@VM-0-10-centos ~]# curl -X GET 'http://localhost:9200/_cat/indices?v'

[root@VM-0-10-centos ~]# curl -X PUT 'localhost:9200/accounts'

[root@VM-0-10-centos ~]# curl -X DELETE 'localhost:9200/account'

```

### <a name="57">索引创建与新增元素</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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


### <a name="58">查询</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

## <a name="59">kibana 命令行操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="60">创建索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="61">中文分词</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

[参见](https://zhuanlan.zhihu.com/p/52543633)

analysis-ik分两种模式：ik_max_word和ik_smart模式

#### <a name="62">ik_max_word</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
会将文本做最细粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为“中华人民共和国、中华人民、中华、华人、人民共和国、人民、共和国、大会堂、大会、会堂等词语。

#### <a name="63">ik_smart</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
会做最粗粒度的拆分，比如会将“中华人民共和国人民大会堂”拆分为中华人民共和国、人民大会堂。

#### <a name="64">最佳实践</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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

### <a name="65">手动插入数据</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="66">查询</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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



#### <a name="67">字段类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[Field datatypes](https://www.elastic.co/guide/en/elasticsearch/reference/7.13/mapping-types.html)

#### <a name="68">filter and query</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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



### <a name="69">索引新增字段</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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
### <a name="70">更改字段类型为 multi_field</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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
### <a name="71">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- object类型自动映射，无需手动新增
- int、long、date等类型自动映射，可以不手动新增
- string类型会自动映射成multi_field，并使用默认分词器，建议手动修改ES mapping


## <a name="72">shard & replica</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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


### <a name="73">primary shard 主分片</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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


### <a name="74">replica shard 副本分片</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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



## <a name="75">spring 集成</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [spring data与elasticsearch版本对应](https://docs.spring.io/spring-data/elasticsearch/docs/4.1.9/reference/html/#preface.requirements)
- [spring data 官网文档](https://docs.spring.io/spring-data/elasticsearch/docs/4.1.9/reference/html/#elasticsearch.clients)

## <a name="76">面试题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="77">ES 的分布式架构原理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
ElasticSearch 设计的理念就是分布式搜索引擎，底层其实还是基于 lucene 的。核心思想就是在多台机器上启动多个 ES 进程实例，组成了一个 ES 集群。\
ES 中存储数据的**基本单位是索引**，索引创建的时候需要指定 `shard`，每个 `shard` 存储部分数据。创建`Index`的时候，每个`shard`都有一个`primary shard`，负责写入数据，但是还有几个`replica shard`负责处理查询请求以及做`primary shard`的备份。如果拥有`primary shard`的分片挂了，ES会重新选出一个`replica shard`作为主分片。每个`shard`分布在**不同节点的机器**上。
> ES 集群多个节点，会自动选举一个节点为 master 节点，这个 master 节点其实就是干一些管理的工作的，比如维护索引元数据、负责切换 primary shard 和 replica shard 身份等。要是 master 节点宕机了，那么会重新选举一个节点为 master 节点。\
> 如果是非 master 节点宕机了，那么会由 master 节点，让那个宕机节点上的 primary shard 的身份转移到其他机器上的 replica shard。接着你要是修复了那个宕机机器，重启了之后，master 节点会控制将缺失的 replica shard 分配过去，同步后续修改的数据之类的，让集群恢复正常。


