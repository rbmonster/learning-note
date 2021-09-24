<a name="index">**Index**</a>

<a href="#0">本地缓存</a>  
&emsp;<a href="#1">1. 设计需求要点</a>  
&emsp;<a href="#2">2. 基本方案</a>  
&emsp;<a href="#3">3. 相关资料</a>  

# <a name="0">本地缓存</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">设计需求要点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 数据结构
2. 线程安全
3. 加载
4. 清除

拓展要点：
- 过期时间
- 清除策略
- 阻塞机制
- 持久化   

## <a name="2">基本方案</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 键值对数据结构且线程安全 ConcurrentHashMap\
 还可以使用正常的HashMap，但是针对增删改查均使用synchronize修饰保证线程安全。
2. 加载走正常hashMap添加就行
3. 清除有两种清除策略：
   - 懒清除：使用softReference 或 weakReference，让jvm清除。
   - 主动清除：LRU、LFU、FIFO
   

## <a name="3">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
