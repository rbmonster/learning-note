# 系统优化思路及方案

应用性能问题的诊断主要从以下三方面入手：内存、CPU、网络。

## 内存
现象
1. OutOfMemoryError: Java heap space；
2. 频繁FULL GC；

原因: 
1. 内存泄露；
2. 堆大小配置不合理

解决方法:
1. jvisualvm：java自带监控程序
2. jstat: 命令行级别查看java的GC过程
3. jmap: 对堆进行一个Dump，文件会在Jvm运行
4. mat: MAT是一个基于Eclipse 的内存分析工具，是一个基础插件。


## CPU
现象
1. 应用响应缓慢；
2. Java进程CPU占用高；

原因
1. 存在大量消耗CPU的逻辑；
2. 循环；
3. 复杂计算；

解决方法
1. Top + jstack: 查看耗费较高cpu的线程
2. Jvisualvm: java自带监控程序
3. Async-profiler: async-profiler是一款采集分析java性能的工具

## 网络
1. 查看当前主机IP：**ip a**
2. 查看当前主机名：hostname
3. 检查目标IP是否可达：ping
4. 检查目标端口是否可达：telnet
5. 查看网卡：ifconfig
6. 查看路由表：route –n
7. 查看从当前主机发往目标主机中间会经过哪些路由：traceroute –i
8. 查看当前主机的网卡流量：iptraf-ng
9. 查看以IP为单位的网络流量排名：iftop –n
10. 查看当前主机上监听的端口：netstat –tpnl
11. 查看当前主机上的TCP连接：netstat –tpn