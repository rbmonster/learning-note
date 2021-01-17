<a name="index">**Index**</a>

<a href="#0">Spring源码</a>  
&emsp;<a href="#1">1. Spring IOC初始化(暂时不看)</a>  
&emsp;&emsp;<a href="#2">1.1. invokeBeanFactoryPostProcessors(beanFactory);</a>  
&emsp;&emsp;<a href="#3">1.2. Spring自动配置加载</a>  
&emsp;<a href="#4">2. Spring transaction</a>  
&emsp;&emsp;<a href="#5">2.1. TransactionAspectSupport</a>  
&emsp;&emsp;<a href="#6">2.2. 事务隔离实现</a>  
&emsp;&emsp;<a href="#7">2.3. @Transactional失效场景</a>  
&emsp;&emsp;<a href="#8">2.4. 相关文章</a>  
&emsp;<a href="#9">3. Spring AOP</a>  
&emsp;&emsp;<a href="#10">3.1. HandlerAdapter与InvocableHandlerMethod</a>  
&emsp;&emsp;<a href="#11">3.2. 整体的调用流程</a>  
&emsp;&emsp;<a href="#12">3.3. 判断使用JDK代理还是Cglib代理</a>  
&emsp;&emsp;<a href="#13">3.4. CglibAopProxy的代理方法</a>  
&emsp;&emsp;<a href="#14">3.5. JdkDynamicAopProxy的代理方法</a>  
&emsp;&emsp;<a href="#15">3.6. AspectJ的方法织入</a>  
# <a name="0">Spring源码</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">Spring IOC初始化(暂时不看)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
构造方法：this.reader = new AnnotatedBeanDefinitionReader(this);
  - AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
  - 默认添加几个Processor：
    - internalConfigurationAnnotationProcessor
    - internalAutowiredAnnotationProcessor
    - internalCommonAnnotationProcessor
    - internalEventListenerProcessor
    - internalEventListenerFactory
### <a name="2">invokeBeanFactoryPostProcessors(beanFactory);</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
  - 获取到ConfigurationClassPostProcessor， 并调用postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) 方法
  - ConfigurationClassParser.doProcessConfigurationClass ：扫描注解的地方
    - // Process any @Import annotations // processImports(configClass, sourceClass, getImports(sourceClass), filter, true);

    
- // Invoke factory processors registered as beans in the context.
  invokeBeanFactoryPostProcessors(beanFactory);
   - ConfigurationClassPostProcessor.processConfigBeanDefinitions
   - ```
     	// Parse each @Configuration class
     		ConfigurationClassParser parser = new ConfigurationClassParser(
     				this.metadataReaderFactory, this.problemReporter, this.environment,
     				this.resourceLoader, this.componentScanBeanNameGenerator, registry);
     
     		Set<BeanDefinitionHolder> candidates = new LinkedHashSet<>(configCandidates);
     		Set<ConfigurationClass> alreadyParsed = new HashSet<>(configCandidates.size());
     		do {
     			parser.parse(candidates);
     ```
     
   org.springframework.boot.autoconfigure.AutoConfigurationMetadataLoader
   protected static final String PATH = "META-INF/spring-autoconfigure-metadata.properties";
 
- AnnotationConfigUtils.registerAnnotationConfigProcessors() 
 
### <a name="3">Spring自动配置加载</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 根据注解加载配置：org.springframework.context.annotation.ConfigurationClassParser
- @EnableAutoConfiguration导入AutoConfigurationImportSelector：org.springframework.boot.autoconfigure.AutoConfigurationImportSelector.getAutoConfigurationEntry
- AutoConfigurationImportSelector 加载配置，先看Exclude注解有没有需要过滤的，在执行过滤器过滤出需要加载的autoConfiguration

## <a name="4">Spring transaction</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/picture/springTransaction.jpg)

Spring 框架中，事务管理相关最重要的 3 个接口如下：
- PlatformTransactionManager： （平台）事务管理器，Spring 事务策略的核心。
- TransactionDefinition： 事务定义信息(事务隔离级别、传播行为、超时、只读、回滚规则)。
- TransactionStatus： 事务运行状态。
- 注解@EnableTransactionManagement 实现事务相关的Bean加载（现在自动配置使用AutoConfiguration实现）
- TransactionInterceptor 主要的实现类，继承TransactionAspectSupport（定义了事务实现的方式）
- 实现原理为使用AOP+Threadlocal实现。

### <a name="5">TransactionAspectSupport</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
transactionInfoHolder：定义一个ThreadLocal，Spring采用ThreadLocal的方式，来保证单个线程中的数据库操作使用的是同一个数据库连接，同时，采用这种方式可以使业务层使用事务时不需要感知并管理connection对象，通过传播级别，巧妙地管理多个事务配置之间的切换，挂起和恢复。
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


### <a name="6">事务隔离实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>


spring 事务隔离主要通过`DataSourceTransactionManager` 在开启事务的时候，设置对应的隔离级别到数据库连接中。
> 本质上事务的实现是通过设置数据库连接的隔离级别。即类似于 `mysql> set global transaction_isolation ='read-committed';` 因此数据库的实现依赖于数据库支持的隔离级别
```
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
		Connection con = null;

		try {
			if (!txObject.hasConnectionHolder() ||
					txObject.getConnectionHolder().isSynchronizedWithTransaction()) {
				Connection newCon = obtainDataSource().getConnection();
				if (logger.isDebugEnabled()) {
					logger.debug("Acquired Connection [" + newCon + "] for JDBC transaction");
				}
				txObject.setConnectionHolder(new ConnectionHolder(newCon), true);
			}

			txObject.getConnectionHolder().setSynchronizedWithTransaction(true);
			con = txObject.getConnectionHolder().getConnection();

			Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(con, definition);
			txObject.setPreviousIsolationLevel(previousIsolationLevel);
        ...
        }
    }
```

### <a name="7">@Transactional失效场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 注解导致的事务失效：
    1. @Transactional 注解属性 propagation 设置错误，设置了以非事务的状态运行
    2. @Transactional  注解属性 rollbackFor 设置错误，实际抛出的错误跟设置不一致
2. 方法修饰符导致的事务失效
    1. @Transactional 应用在非 public 修饰的方法上，在事务方法拦截执行中，非public方法不执行。
       -  ```
            @Nullable
            protected TransactionAttribute computeTransactionAttribute(Method method, @Nullable Class<?> targetClass) {
                // Don't allow no-public methods as required.
                if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
                    return null;
                }
                ...
            }
            ```
    2. 在动态代理层面，若为也需要代理的方法为public才能正常代理。如JDK动态代理，通过接口代理，接口方法默认都是public。而Cglib基于类的代理中会默认判断是否为public方法。
      - ```
         public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            ...
            TargetSource targetSource = this.advised.getTargetSource();
            try {
                ...
                // 拦截链为空，且方法为public 直接调用
                if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) {
                    Object[] argsToUse = AopProxyUtils.adaptArgumentsIfNecessary(method, args);
                    retVal = methodProxy.invoke(target, argsToUse);
                }
            ···
         }
        ```
3. 同一个类中方法调用，导致@Transactional失效，在当前的bean中非事务方法调用事务方法为什么不生效？
    - 当进行方法拦截的时候，方法拦截器首先获取当前动态代理的对象所代理的原始对象。如果判断当前的方法比如save方法没有Advice(增强)，则直接调用原对象的方法，即这个时候调用的是FirstApp.save方法。
4. 异常被 catch 导致@Transactional失效
5. 数据库引擎不支持事务

### <a name="8">相关文章</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- https://juejin.cn/post/6844903779188342798


## <a name="9">Spring AOP</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="10">HandlerAdapter与InvocableHandlerMethod</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 对于Controller的方法，请求发送的时候通过HandlerAdapter调用InvocableHandlerMethod.doInvoke方法。
  - InvocableHandlerMethod.doInvoke 强制会把方法设为可见：
  - ```
    	protected Object doInvoke(Object... args) throws Exception {
    		ReflectionUtils.makeAccessible(getBridgedMethod());
    		try {
    			return getBridgedMethod().invoke(getBean(), args);
    		}
            ...
        }
    ```
### <a name="11">整体的调用流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 对于web的调用，首先通过InvocationHandlerMethod，设置方法可见，强制调用方法。
2. 调用方法后，判断方法是否使用代理，若没使用代理，直接调用方法。若使用了代理，进入代理方法的invoke方法。
3. 区分JDK代理、Cglib代理，获取Advisor的对应拦截链，分别进入到拦截类的proceed()方法执行中。

### <a name="12">判断使用JDK代理还是Cglib代理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 对于实际的Method调用，如果是代理对象的调用会分别进入各自的代理的invoke方法中，主要分为Cglib（CglibAopProxy）和JDK代理（JdkDynamicAopProxy）
- DefaultAopProxyFactory：如何判断使用JDK代理还是Cglib代理
  - ```
    @Override
    	public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
    		if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
    			Class<?> targetClass = config.getTargetClass();
    			if (targetClass == null) {
    				throw new AopConfigException("TargetSource cannot determine target class: " +
    						"Either an interface or a target is required for proxy creation.");
    			}
               // 代理对象是接口，使用JDK代理
    			if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
    				return new JdkDynamicAopProxy(config);
    			}
    			return new ObjenesisCglibAopProxy(config);
    		}
    		else {
    			return new JdkDynamicAopProxy(config);
    		}
    	}
    ```
### <a name="13">CglibAopProxy的代理方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- CglibAopProxy 代理方法：
  - ```
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object oldProxy = null;
        boolean setProxyContext = false;
        Object target = null;
        // 获取AopProxyAdvier 
        TargetSource targetSource = this.advised.getTargetSource();
        try {
            if (this.advised.exposeProxy) {
                oldProxy = AopContext.setCurrentProxy(proxy);
                setProxyContext = true;
            }
            target = targetSource.getTarget();
            Class<?> targetClass = (target != null ? target.getClass() : null);
            // 获取对应的拦截链
            List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
            Object retVal;
            // 拦截链为空，且方法为public 直接调用
            if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) {
                Object[] argsToUse = AopProxyUtils.adaptArgumentsIfNecessary(method, args);
                retVal = methodProxy.invoke(target, argsToUse);
            }
            else {
                 // 执行拦截链中的interceptor
                retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
            }
            retVal = processReturnType(proxy, target, method, retVal);
            return retVal;
        }
        finally {
        }
    ```
### <a name="14">JdkDynamicAopProxy的代理方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- JdkDynamicAopProxy:  基于JDK的代理可以看出，对于基本的方法hashcode以及equals方法都是没有进行拦截的。
  - ```
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    		Object oldProxy = null;
    		boolean setProxyContext = false;
    
    		TargetSource targetSource = this.advised.targetSource;
    		Object target = null;
    
    		try {
    			if (!this.equalsDefined && AopUtils.isEqualsMethod(method)) {
    				return equals(args[0]);
    			}
    			else if (!this.hashCodeDefined && AopUtils.isHashCodeMethod(method)) {
    				return hashCode();
    			}
    			else if (method.getDeclaringClass() == DecoratingProxy.class) {
    				// There is only getDecoratedClass() declared -> dispatch to proxy config.
    				return AopProxyUtils.ultimateTargetClass(this.advised);
    			}
    			else if (!this.advised.opaque && method.getDeclaringClass().isInterface() &&
    					method.getDeclaringClass().isAssignableFrom(Advised.class)) {
    				return AopUtils.invokeJoinpointUsingReflection(this.advised, method, args);
    			}
    
    			Object retVal;
    
    			if (this.advised.exposeProxy) {
    				oldProxy = AopContext.setCurrentProxy(proxy);
    				setProxyContext = true;
    			}
    
    			target = targetSource.getTarget();
    			Class<?> targetClass = (target != null ? target.getClass() : null);
    
    			// Get the interception chain for this method.
    			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
    
    			// Check whether we have any advice. If we don't, we can fallback on direct
    			// reflective invocation of the target, and avoid creating a MethodInvocation.
    			if (chain.isEmpty()) {
    				// We can skip creating a MethodInvocation: just invoke the target directly
    				// Note that the final invoker must be an InvokerInterceptor so we know it does
    				// nothing but a reflective operation on the target, and no hot swapping or fancy proxying.
    				Object[] argsToUse = AopProxyUtils.adaptArgumentsIfNecessary(method, args);
    				retVal = AopUtils.invokeJoinpointUsingReflection(target, method, argsToUse);
    			}
    			else {
    				// We need to create a method invocation...
    				MethodInvocation invocation =
    						new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
    				// Proceed to the joinpoint through the interceptor chain.
    				retVal = invocation.proceed();
    			}
    }
    ```
### <a name="15">AspectJ的方法织入</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- AspectJ的Aop 在方法调用的时候添加了AOP对应的拦截方法，根据对应的拦截类型
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/picture/methodAop.jpg)

