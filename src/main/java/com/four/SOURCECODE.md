# Spring源码
- 根据注解加载配置：org.springframework.context.annotation.ConfigurationClassParser
- @EnableAutoConfiguration导入AutoConfigurationImportSelector：org.springframework.boot.autoconfigure.AutoConfigurationImportSelector.getAutoConfigurationEntry
- AutoConfigurationImportSelector 加载配置，先看Exclude注解有没有需要过滤的，在执行过滤器过滤出需要加载的autoConfiguration

## Spring transaction
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/transaction/picture/springTransaction.jpg)

- Spring 框架中，事务管理相关最重要的 3 个接口如下：
    - PlatformTransactionManager： （平台）事务管理器，Spring 事务策略的核心。
    - TransactionDefinition： 事务定义信息(事务隔离级别、传播行为、超时、只读、回滚规则)。
    - TransactionStatus： 事务运行状态。
- 注解@EnableTransactionManagement 实现事务相关的Bean加载（现在自动配置使用AutoConfiguration实现）
- TransactionInterceptor 主要的实现类，继承TransactionAspectSupport（定义了事务实现的方式）
- 实现原理为使用AOP+Threadlocal实现。
### TransactionAspectSupport

- transactionInfoHolder：定义一个ThreadLocal，Spring采用Threadlocal的方式，来保证单个线程中的数据库操作使用的是同一个数据库连接，同时，采用这种方式可以使业务层使用事务时不需要感知并管理connection对象，通过传播级别，巧妙地管理多个事务配置之间的切换，挂起和恢复。
- @Transaction方法调用链条：
```
// 方法拦截器：TransactionInterceptor.invoke

 @Nullable
    protected Object invokeWithinTransaction(Method method, @Nullable Class<?> targetClass, TransactionAspectSupport.InvocationCallback invocation) throws Throwable {
            // 获取参数配置
           TransactionAttributeSource tas = this.getTransactionAttributeSource();
           TransactionAttribute txAttr = tas != null ? tas.getTransactionAttribute(method, targetClass) : null;
            // 生成对应配置的事务管理器
           TransactionManager tm = this.determineTransactionManager(txAttr);
            // 判断事务的种类
            .....
            // 常规的DataSourceManager
                // createTransactionIfNecessary内部，内部主要就是使用spring事务硬编码的方式开启事务，最终会返回一个TransactionInfo对象
                TransactionAspectSupport.TransactionInfo txInfo = this.createTransactionIfNecessary(ptm, txAttr, joinpointIdentification);
    
                try {
                    // 方法执行
                    retVal = invocation.proceedWithInvocation();
                } catch (Throwable var18) {
                    //异常情况下，处理取决于事务的配置
                    completeTransactionAfterThrowing(txInfo, ex);
                    throw ex;
                }
                finally {
                    //清理事务信息
                    cleanupTransactionInfo(txInfo);
                }
                // retVal干啥的不确定 ----如果 retVal的类型是 Try 则在 Try onFailure 中执行回滚判定
                if (vavrPresent && TransactionAspectSupport.VavrDelegate.isVavrTry(retVal)) {
                    TransactionStatus status = txInfo.getTransactionStatus();
                    if (status != null && txAttr != null) {
                        retVal = TransactionAspectSupport.VavrDelegate.evaluateTryFailure(retVal, txAttr, status);
                    }
                }
    
                 //业务方法返回之后，只需事务提交操作
                commitTransactionAfterReturning(txInfo);
                //返回执行结果
                return retVal;
}
```

```
 protected TransactionAspectSupport.TransactionInfo createTransactionIfNecessary(@Nullable PlatformTransactionManager tm, @Nullable TransactionAttribute txAttr, final String joinpointIdentification) {
        if (txAttr != null && ((TransactionAttribute)txAttr).getName() == null) {
            txAttr = new DelegatingTransactionAttribute((TransactionAttribute)txAttr) {
                public String getName() {
                    return joinpointIdentification;
                }
            };
        }

        TransactionStatus status = null;
        if (txAttr != null) {
            if (tm != null) {
                // 创建事务的核心方法
                status = tm.getTransaction((TransactionDefinition)txAttr);
            } else if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping transactional joinpoint [" + joinpointIdentification + "] because no transaction manager has been configured");
            }
        }

        return this.prepareTransactionInfo(tm, (TransactionAttribute)txAttr, joinpointIdentification, status);
    }


---
public final TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException {
        TransactionDefinition def = definition != null ? definition : TransactionDefinition.withDefaults();
        Object transaction = this.doGetTransaction();

...

}

---------------------------
// DataSourceTransactionManager 开启事务
 protected Object doGetTransaction() {
        DataSourceTransactionManager.DataSourceTransactionObject txObject = new DataSourceTransactionManager.DataSourceTransactionObject();
        txObject.setSavepointAllowed(this.isNestedTransactionAllowed());
        ConnectionHolder conHolder = (ConnectionHolder)TransactionSynchronizationManager.getResource(this.obtainDataSource());
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

```

- Spring 事务处理 中，可以通过设计一个 TransactionProxyFactoryBean 来使用 AOP 功能，通过这个 TransactionProxyFactoryBean 可以生成 Proxy 代理对象

# 事务不生效

private方法不会生效，JDK中必须是接口，接口中不可能有private方法，protect方法的话，也不生效。原因是spring内部判断方法修饰符如果不是public不生成事务拦截代理类。
CGLib代理的时候，final方法不会生效，抛NullPointException，cglib与JDK内部机制。
