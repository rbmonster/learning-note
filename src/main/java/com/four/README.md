# Spring 

## Spring IOC初始化
- 构造方法：this.reader = new AnnotatedBeanDefinitionReader(this);
  - AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
  - 默认添加几个Processor：
    - internalConfigurationAnnotationProcessor
    - internalAutowiredAnnotationProcessor
    - internalCommonAnnotationProcessor
    - internalEventListenerProcessor
    - internalEventListenerFactory
### invokeBeanFactoryPostProcessors(beanFactory);
- PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());
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
 
## Spring boot 自动配置的加载流程
- postProcessor是有执行等级之分的。
- invokeBeanFactoryPostProcessors(beanFactory);
1. Spring boot的配置自动加载主要通过 @EnableAutoConfiguration注解实现
2. 注解中@Import(AutoConfigurationImportSelector.class) 的类。借助@Import的支持，收集和注册特定场景相关的bean定义。
3. 该类的getAutoConfigurationEntry方法会扫描所有包下spring-autoconfigure-metadata.properties的属性，并对比过滤符合当前配置的配置项。
4. 重新进行config的注解扫描添加需要的bean配置到BenDefinition中
5. 再执行初始化方法。


## Spring Transaction

#### 注解 @Transactional 的声明式事务管理
- Propagation.REQUIRED：如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。
- Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。
- Propagation.MANDATORY：如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常。

- Propagation.REQUIRES_NEW：重新创建一个新的事务，如果当前存在事务，延缓当前的事务。
- Propagation.NOT_SUPPORTED：以非事务的方式运行，如果当前存在事务，暂停当前的事务。
- Propagation.NEVER：以非事务的方式运行，如果当前存在事务，则抛出异常。
- Propagation.NESTED：如果没有，就新建一个事务；如果有，就在当前事务中嵌套其他事务。
  
#### spring transaction的隔离级别
- TransactionDefinition.ISOLATION_DEFAULT :使用后端数据库默认的隔离级别，MySQL 默认采用的 REPEATABLE_READ 隔离级别 Oracle 默认采用的 READ_COMMITTED 隔离级别.
- TransactionDefinition.ISOLATION_READ_UNCOMMITTED :最低的隔离级别，使用这个隔离级别很少，因为它允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读
- TransactionDefinition.ISOLATION_READ_COMMITTED : 允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
- TransactionDefinition.ISOLATION_REPEATABLE_READ : 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。
- TransactionDefinition.ISOLATION_SERIALIZABLE : 最高的隔离级别，完全服从 ACID 的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

####  同一个方法无事务的方法调用有事务的方法会出现什么情况？
- 当这个方法被同一个类调用的时候，spring无法将这个方法加到事务管理中。只有在代理对象之间进行调用时，可以触发切面逻辑。
1.使用 ApplicationContext 上下文对象获取该对象;
2.使用 AopContext.currentProxy() 获取代理对象,但是需要配置exposeProxy=true

#### aop切面的相关方法   
https://www.cnblogs.com/zhangxufeng/p/9160869.html

## 零散的一些面试题

#### @Autowired和@Resource的区别是什么？
1. @Autowired注解是按类型装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它required属性为false。可以结合@Qualifier注解一起使用。
2. @Resource注解和@Autowired一样，也可以标注在字段或属性的setter方法上，但它默认按名称装配。默认按byName自动注入，也提供按照byType 注入；

3. @Resources按名字，是ＪＤＫ的，@Autowired按类型，是Ｓｐｒｉｎｇ的。
4. 处理这2个注解的BeanPostProcessor不一样CommonAnnotationBeanPostProcessor是处理@ReSource注解的AutoWiredAnnotationBeanPostProcessor是处理@AutoWired注解的

