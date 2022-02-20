# fastDFS 文件服务器

## 分布式文件存储系统

FastDFS服务端有三个角色：跟踪服务器（tracker server）、存储服务器（storage server）和客户端（client）。

tracker server：跟踪服务器，主要做调度工作，起负载均衡的作用。Tracker是FastDFS的协调者，负责管理所有的storage server和group，每个storage在启动后会连接Tracker，告知自己所属的group等信息，并保持周期性的心跳，tracker根据storage的心跳信息，建立group==&gt;[storage server list]的映射表。

storage server：存储服务器（又称：存储节点或数据服务器），文件和文件属性（meta data）都保存到存储服务器上。Storage server直接利用OS的文件系统调用管理文件。
- Storage server（后简称storage）以组（卷，group或volume）为单位组织，一个group内包含多台storage机器，数据互为备份，存储空间以group内容量最小的storage为准，所以建议group内的多个storage尽量配置相同，以免造成存储空间的浪费。
- 以group为单位组织存储能方便的进行应用隔离、负载均衡、副本数定制（group内storage server数量即为该group的副本数），比如将不同应用数据存到不同的group就能隔离应用数据。
  
  
  
如何保证数据不丢失：\
在于Trunk-Server上，Trunk-Server实现空间的分配，每一次的空间分配都记录到Trunk-Binlog文件之中，并且定期（每秒）将该文件的更新同步给组内的其他Storage服务器

内部机制如下：
1. 选择tracker server当集群中不止一个tracker server时，由于tracker之间是完全对等的关系，客户端在upload文件时可以任意选择一个trakcer。 选择存储的group 当tracker接收到upload file的请求时，会为该文件分配一个可以存储该文件的group，支持如下选择group的规则：
    1. Round robin，所有的group间轮询
    2. Specified group，指定某一个确定的group
    3. Load balance，剩余存储空间多多group优先
2. 选择storage server。当选定group后，tracker会在group内选择一个storage server给客户端，支持如下选择storage的规则：
    1. Round robin，在group内的所有storage间轮询
    2. First server ordered by ip，按ip排序
    3. First server ordered by priority，按优先级排序（优先级在storage上配置）
3. 选择storage path。当分配好storage server后，客户端将向storage发送写文件请求，storage将会为文件分配一个数据存储目录，支持如下规则：
    1. Round robin，多个存储目录间轮询
    2. 剩余存储空间最多的优先
4. 生成Fileid。选定存储目录之后，storage会为文件生一个Fileid，由storage server ip、文件创建时间、文件大小、文件crc32和一个随机数拼接而成，然后将这个二进制串进行base64编码，转换为可打印的字符串。 选择两级目录 当选定存储目录之后，storage会为文件分配一个fileid，每个存储目录下有两级256*256的子目录，storage会按文件fileid进行两次hash（猜测），路由到其中一个子目录，然后将文件以fileid为文件名存储到该子目录下。
5. 生成文件名。当文件存储到某个子目录后，即认为该文件存储成功，接下来会为该文件生成一个文件名，文件名由group、存储目录、两级子目录、fileid、文件后缀名
  
