# 延迟队列

与时间相关场景的应用，经常用于延后多少时间执行什么任务。

## java 自带延迟队列

```
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayMealTask> queue = new DelayQueue<>();
        DelayMealTask task = new DelayMealTask(System.nanoTime() + ThreadLocalRandom.current().nextLong(100000000L, 300000000L));
        queue.add(task);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("begin to take task");
        DelayMealTask take = queue.take();
        System.out.println("get task complete id :"+take.getTaskId());
        stopWatch.stop();
        System.out.println("cost time : " +stopWatch.getTotalTimeMillis());
    }

// output ~

begin to take task
get task complete id :1
cost time : 110
```


## redis 延迟队列

1. 使用zset当延迟队列，使用时间戳当score。
2. redission 内部实现使用lua 脚本。结合**zset、list和发布订阅模型**
3. 在zset的添加的时候，获取第一个元素发布消息。QueueTransferService 接受到消息，通过判断过期时间与当前时间戳的差值执行不同的策略。
4. 若时间戳时延在10以内，则立即触发zset 元素到list的转移。若时延大于10，则新建定时任务，更新QueueTransferService 的最后触发定时任务时间。
```

<dependency>
   <groupId>org.redisson</groupId>
   <artifactId>redisson</artifactId>
   <version>3.8.0</version>
</dependency> 

public void producer(){
         RedissonClient redissonClient = Redisson.create(config);
         // 阻塞队列用于后端服务器的消费
         RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");
 
         RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
         for (int i = 0; i <10 ; i++) {
             try {
                 Thread.sleep(1*1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             // 一分钟以后将消息发送到指定队列
            //相当于1分钟后取消订单
            // 延迟队列包含callCdr 1分钟，然后将其传输到blockingFairQueue中
             //在1分钟后就可以在blockingFairQueue 中获取callCdr了 
            // (CallCdr自定义的类)
             CallCdr callCdr = new CallCdr(30000.00);
             callCdr.setPutTime();
             delayedQueue.offer(callCdr, 1, TimeUnit.MINUTES);
 
         }
        // 在该对象不再需要的情况下，应该主动销毁。
        // 仅在相关的Redisson对象也需要关闭的时候可以不用主动销毁。
         delayedQueue.destroy();
...
}


public void comsumer() {
     ...
     RedissonClient redissonClient = Redisson.create(config);
     RBlockingQueue<CallCdr> blockingFairQueue = redissonClient.getBlockingQueue("delay_queue");
     RDelayedQueue<CallCdr> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
     while (true){
         CallCdr callCdr = null;
         try {
             callCdr = blockingFairQueue.take();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println("订单取消时间："+new SimpleDateFormat("hh:mm:ss").format(new Date())+"==订单生成时间"+callCdr.getPutTime());
     }
    ...
}
```



redisson的DelayedQueue使用上是将元素及延时信息入队，之后定时任务将到期的元素转移到目标队列
- 这里使用了三个结构来存储，一个是目标队列list；一个是原生队列list，添加的是带有延时信息的结构体；一个是timeoutSetName的zset，元素是结构体，其score为timeout值

相关资料： https://my.oschina.net/go4it/blog/2206612

## RabbitMq 延迟队列

采用死信队列+TTL过期时间来实现延迟队列
> TTL则刚好能让消息在延迟多久之后成为死信，另一方面，成为死信的消息都会被投递到死信队列里。使用消费者消费死信队列。

相关资料： https://www.cnblogs.com/mfrank/p/11260355.html

## 应用
### 异步通知
异步通知的重试，在很多系统中，当用户完成服务调用后，系统有时需要将结果异步通知到用户的某个URI。由于网络等原因，很多时候会通知失败，这个时候就需要一种重试机制。

这时可以用DelayQueue保存通知失败的请求，失效时间可以根据已通知的次数来设定（比如：2s、5s、10s、20s），这样每次从队列中take获取的就是剩余时间最短的请求，如果已重复通知次数超过一定阈值，则可以把消息抛弃。

### 下单未支付取消
如淘宝订单确认订单后未支付30分钟后自动关闭订单的实现就可以使用延迟队列的实现。


### 考试系统 定时交卷
下面将使用此类实现一个多考生考试的场景：

- 考试总时间为2h，至少30min后才可进行交卷。
- 考生可在30 - 120min这段时间内的任意时间交卷。
- 考试时间一到，所有未交卷的学生必须交卷


### 其他
1. 新创建的店铺，如果在十天内都没有上传过商品，则自动发送消息提醒。
2. 账单在一周内未支付，则自动结算。
3. 用户注册成功后，如果三天内没有登陆则进行短信提醒。
4. 用户发起退款，如果三天内没有得到处理则通知相关运营人员。
5. 预定会议后，需要在预定的时间点前十分钟通知各个与会人员参加会议。

### 定时任务实现
java 线程池中ScheduledThreadPoolExecutor时发现它主要依赖线程池和它的静态内部类DelayedWorkQueue实现

redis可以结合RedissonClient 实现定时任务，做为一个分布式的定时任务。

- 相关文章：https://zhuanlan.zhihu.com/p/107624995
### 相关文章
- https://blog.csdn.net/weixin_34392435/article/details/87993708



## 相关文章 
- 延时队列的三种实现：https://blog.csdn.net/zsj777/article/details/82468212
- 延时队列：https://zhuanlan.zhihu.com/p/87113913