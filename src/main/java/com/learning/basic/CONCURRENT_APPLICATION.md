# Java并发应用

## 生产者与消费者模型



## 线程安全的类定义
1. 无状态的类：没有任何成员变量的类，如无任何方法的枚举类型。
2. 让类不可变	
   1. 加final关键字
   2. 不提供修改成员变量，也不提供获取成员变量方法
3. 使用volatile，保证类的可见性，不能保证线程安全。适合一写多读的场景
4. 加锁和CAS，使用synchronized、lock、原子变量AtomicInteger等
   1. 如StringBuffer 修改的方法都使用synchronize修饰。
   2. 如concurrentHashMap 使用自旋加CAS修改。
   3. 使用Atomic包的基本类型，如AtomicInteger、AtomicReference、AtmoicStampReference修饰变量。
   

## 单订单重复退款请求
1. synchronize修饰退款方法。 
2. 缩小synchronize锁范围，使用对象锁。对象锁，创建弱引用的一个订单ID对象，放到同一的锁对象资源池中。
   - 清理锁对象可以使用守护线程的方法，基于Unsafe的包操作去清除。
3. 分布式应用，使用分布式锁来处理。


### 分布式锁的处理方案

1. 数据库锁，数据库乐观锁，数据库悲观锁。

2. redis 锁 或者 ZooKeeper锁

3. 使用消息队列顺序消费，保证不重复退款