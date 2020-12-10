<a name="index">**Index**</a>

<a href="#0">订单取消</a>  
&emsp;<a href="#1">1. redis 延迟队列</a>  
&emsp;&emsp;<a href="#2">1.1. 相关文章</a>  
&emsp;<a href="#3">2. 相关文章 </a>  
# <a name="0">订单取消</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>





## <a name="1">redis 延迟队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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



- redisson的DelayedQueue使用上是将元素及延时信息入队，之后定时任务将到期的元素转移到目标队列
- 这里使用了三个结构来存储，一个是目标队列list；一个是原生队列list，添加的是带有延时信息的结构体；一个是timeoutSetName的zset，元素是结构体，其score为timeout值


### <a name="2">相关文章</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- https://blog.csdn.net/weixin_34392435/article/details/87993708



## <a name="3">相关文章 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 延时队列的三种实现：https://blog.csdn.net/zsj777/article/details/82468212
- 延时队列：https://zhuanlan.zhihu.com/p/87113913