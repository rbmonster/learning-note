<a name="index">**Index**</a>

<a href="#0">Spring </a>  
&emsp;<a href="#1">1. Spring IOC & AOP</a>  
&emsp;&emsp;<a href="#2">1.1. Spring IOC</a>  
&emsp;&emsp;<a href="#3">1.2. AOP</a>  
&emsp;&emsp;&emsp;<a href="#4">1.2.1. 应用场景</a>  
&emsp;&emsp;&emsp;<a href="#5">1.2.2. aop 相关概念</a>  
&emsp;&emsp;&emsp;<a href="#6">1.2.3. aop切面的相关方法   </a>  
&emsp;&emsp;<a href="#7">1.3. Spring AOP 和 AspectJ AOP 有什么区别？</a>  
&emsp;<a href="#8">2. Spring 中的 bean 的作用域有哪些?</a>  
&emsp;<a href="#9">3. Spring 中的 bean 生命周期</a>  
&emsp;<a href="#10">4. Spring 循环依赖</a>  
&emsp;&emsp;<a href="#11">4.1. 相关问题(加深理解)</a>  
&emsp;&emsp;<a href="#12">4.2. 相关文章</a>  
&emsp;<a href="#13">5. Spring Transaction</a>  
&emsp;&emsp;<a href="#14">5.1. 基础知识</a>  
&emsp;&emsp;<a href="#15">5.2. 编程式事务</a>  
&emsp;&emsp;<a href="#16">5.3. @Transactional 声明式事务管理</a>  
&emsp;&emsp;<a href="#17">5.4. 声明式事务相关属性</a>  
&emsp;&emsp;<a href="#18">5.5. 事务传播行为</a>  
&emsp;&emsp;<a href="#19">5.6. spring transaction的隔离级别</a>  
&emsp;&emsp;<a href="#20">5.7. 事务失效场景</a>  
&emsp;&emsp;&emsp;<a href="#21">5.7.1.  同一个方法调用无事务的解决方案</a>  
&emsp;<a href="#22">6. Spring boot 自动配置的加载流程</a>  
&emsp;<a href="#23">7. Spring mvc 工作原理</a>  
&emsp;<a href="#24">8. BeanFactory和ApplicationContext的区别</a>  
&emsp;<a href="#25">9. spring容器启动过程、spring boot启动过程</a>  
&emsp;<a href="#26">10. @RestController vs @Controller</a>  
&emsp;&emsp;<a href="#27">10.1. @ResponseBody</a>  
&emsp;<a href="#28">11. spring中的设计模式</a>  
&emsp;<a href="#29">12. @Import 注解</a>  
&emsp;<a href="#30">13. 零散的一些面试题</a>  
&emsp;&emsp;<a href="#31">13.1. @Autowired和@Resource的区别是什么？</a>  
&emsp;&emsp;<a href="#32">13.2. @PostConstruct和@PreDestroy</a>  
&emsp;&emsp;<a href="#33">13.3. Spring 的异常处理</a>  
&emsp;&emsp;<a href="#34">13.4.  json 数据处理</a>  
&emsp;&emsp;<a href="#35">13.5.  @Component 和 @Bean 的区别是什么？</a>  
# <a name="0">Spring </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">Spring IOC & AOP</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="2">Spring IOC</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
IOC 理解
1. 控制反转：原来在程序中手动创建对象，现在需要什么对象由IOC提供，一个好处就是对象统一管理。
2. 依赖注入：将对象之间的相互依赖关系交给 IoC 容器来管理，并由 IoC 容器完成对象的注入。简化开发及对象的创建。
  
### <a name="3">AOP</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
AOP(Aspect-Oriented Programming,面向切面编程)：能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。

Spring AOP就是基于动态代理的，如果要代理的对象，实现了某个接口，那么Spring AOP会使用JDK Proxy，去创建代理对象，而对于没有实现接口的对象，就无法使用 JDK Proxy 去进行代理了，这时候Spring AOP会使用Cglib 。
#### <a name="4">应用场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. Authentication 权限
2. Caching 缓存
3. Context passing 内容传递
4. Error handling 错误处理
5. Lazy loading 懒加载
6. Debugging 调试
7. logging, tracing, profiling and monitoring 记录跟踪 优化 校准
8. Performance optimization 性能优化
9. Persistence 持久化
10. Resource pooling 资源池
11. Synchronization 同步
12. Transactions 事务

#### <a name="5">aop 相关概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 切面（aspect）:类是对物体特征的抽象，切面就是对横切关注点的抽象
2. 横切关注点： 对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点。
3. 连接点（joinPoint）:被拦截到的点，因为 Spring 只支持方法类型的连接点，所以在 Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器。
4. 切入点（pointcut）:对连接点进行拦截的定义
5. 通知（advice）:所谓通知指的就是指拦截到连接点之后要执行的代码， 通知分为前置、后置、异常、最终、环绕通知五类。
6. 目标对象： 代理的目标对象
7. 织入（weave）:将切面应用到目标对象并导致代理对象创建的过程
8. 引入（introduction）:在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段

#### <a name="6">aop切面的相关方法   </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```
@Aspect
public class TransactionDemo {
    @Pointcut(value="execution(* com.yangxin.core.service.*.*.*(..))")
    public void point(){
    }
    @Before(value="point()")
    public void before(){
        System.out.println("transaction begin");
    }
    @AfterReturning(value = "point()")
    public void after(){
        System.out.println("transaction commit");
    }
    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("transaction begin");
        joinPoint.proceed();
        System.out.println("transaction commit");
    }
}
```
[Spring AOP切点表达式用法总结](https://www.cnblogs.com/zhangxufeng/p/9160869.html)


### <a name="7">Spring AOP 和 AspectJ AOP 有什么区别？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Spring AOP 属于运行时增强，而 AspectJ 是编译时增强。 Spring AOP 基于代理(Proxying)，而 AspectJ 基于字节码操作(Bytecode Manipulation)。
- 如果我们的切面比较少，那么两者性能差异不大。但是，当切面太多的话，最好选择 AspectJ ，基于字节码的修改实现代理，它比Spring AOP 快很多。
- @EnableAspectJAutoProxy + @Configuration 用于加载@AspectJ的类。但是在Spring Boot项目中，我们不必显式使用@EnableAspectJAutoProxy。 如果Aspect或Advice位于类路径中，则有一个专用的AopAutoConfiguration启用Spring的AOP支持。


## <a name="8">Spring 中的 bean 的作用域有哪些?</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- singleton : 唯一 bean 实例，Spring 中的 bean 默认都是单例的。
- prototype : 每次请求都会创建一个新的 bean 实例。如跟请**求状态有关**的对象，就不能使用单例，需要使用多例保证线程安全。
  > 之所以用多例，是为了防止**并发问题**；即一个请求改变了对象的状态，此时对象又处理另一个请求，而之前请求对**对象状态**的改变导致了对象对另一个请求做了错误的处理；\
  > 使用@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,proxyMode = ScopedProxyMode.TARGET_CLASS)。ScopedProxyMode.TARGET_CLASS是字节码级别多例,设置多例的代理模式使用一次就会产生一个运行时字节新对象
- request : 每一次HTTP请求都会产生一个新的bean，该bean仅在当前HTTP request内有效。
- session : 每一次HTTP请求都会产生一个新的 bean，该bean仅在当前 HTTP session 内有效。
> global-session： 全局session作用域，仅仅在基于portlet的web应用中才有意义，Spring5已经没有了。Portlet是能够生成语义代码(例如：HTML)片段的小型Java Web插件。它们基于portlet容器，可以像servlet一样处理HTTP请求。但是，与 servlet 不同，每个 portlet 都有不同的会话
   
## <a name="9">Spring 中的 bean 生命周期</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 见声明周期详解

## <a name="10">Spring 循环依赖</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于普通的循环依赖如A 依赖B， B依赖A。在初始化A的时候，会实例化B，实例化B发现需要A的引用，这时候通过缓存返回A的引用。虽然A还未初始化完毕，但是由于是对象的引用，所以最终初始化完成的时候，两个对象均是初始化完整的。
![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/four/iocAutowire.png)

getSingleton(beanName, true)这个方法实际上就是到缓存中尝试去获取Bean，整个缓存分为三级
- singletonObjects，一级缓存，存储的是所有创建好了的单例Bean
- earlySingletonObjects，完成实例化，但是还未进行属性注入及初始化的对象
- singletonFactories，提前暴露的一个单例工厂（一个获取对象的函数表达式），二级缓存中存储的就是从这个工厂中获取到的对象。

循环依赖装载流程：
  - 条件1.出现循环依赖的Bean必须要是单例
  - 条件2.依赖注入的方式不能全是构造器注入的方式
1. 实例化A对象，添加提前暴露的对象的方法到singletonFactory
2. 填充对象，无循环引用，直接调用postProcessAfterInitialization方法。有循环引用，doGetBean循环引用的对象。
3. 循环引用对象B实例化，在填充对象的时候，发现循环依赖A，从三级缓存中获取，并添加A到二级缓存。
4. 若A为AOP代理，此时二级缓存中的对象为代理对象A。
5. B初始化完成后，继续A的对象填充及初始化，填充完成后。从二级缓存中获取对象，若存在对象，说明发生了循环引用，返回二级缓存的对象。

### <a name="11">相关问题(加深理解)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
可以使用两层缓存吗？
> 1. 如果单单使用二级缓存，为解决循环引用问题，那么二级缓存存储的就是为进行属性注入的对象。这与Spring生命周期的设计相悖。\
> 2. Spring结合AOP跟Bean的生命周期本身就是通过AnnotationAwareAspectJAutoProxyCreator这个后置处理器来完成的，在这个后置处理的postProcessAfterInitialization方法中对初始化后的Bean完成AOP代理。
如果出现了循环依赖，那没有办法，只有给Bean先创建代理。但是如果没有出现循环依赖的情况下，设计之初就是让Bean在生命周期的最后一步完成代理而不是在实例化后就立马完成代理。

三级缓存为什么要使用工厂而不是直接使用引用？换而言之，为什么需要这个三级缓存，直接通过二级缓存暴露一个引用不行吗？
> 这个工厂的目的在于**延迟对实例化阶段生成对象的代理**，只有真正发生循环依赖的时候，才去提前生成代理对象，否则只会创建一个工厂并将其放入到三级缓存中，但是不会去通过这个工厂去真正创建对象。
  
三级缓存引入对于对象延迟实例化的体现。
![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/four/iocAutowire.jpg)

不同注入方式对于循环引用的影响
![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/four/iocAutowire3.jpg)

### <a name="12">相关文章</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[讲一讲Spring中的循环依赖](https://mp.weixin.qq.com/s/kS0K5P4FdF3v-fiIjGIvvQ)

## <a name="13">Spring Transaction</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="14">基础知识</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Spring 框架中，事务管理相关最重要的 3 个接口如下：
- TransactionDefinition： 事务定义信息(事务隔离级别、传播行为、超时、只读、回滚规则)。
- PlatformTransactionManager： （平台）事务管理器，Spring 事务策略的核心，约束了事务常用的方法。
> 通过这个接口，Spring 为各个平台如 JDBC(DataSourceTransactionManager)、Hibernate(HibernateTransactionManager)、JPA(JpaTransactionManager)等都提供了对应的事务管理器，但是具体的实现就是各个平台自己的事情了。
```java
public interface PlatformTransactionManager {
    //获得事务
    TransactionStatus getTransaction(@Nullable TransactionDefinition var1) throws TransactionException;
    //提交事务
    void commit(TransactionStatus var1) throws TransactionException;
    //回滚事务
    void rollback(TransactionStatus var1) throws TransactionException;
}
```

- TransactionStatus： 事务运行状态。
 ```java
public interface TransactionStatus{
    boolean isNewTransaction(); // 是否是新的事务
    boolean hasSavepoint(); // 是否有恢复点
    void setRollbackOnly();  // 设置为只回滚
    boolean isRollbackOnly(); // 是否为只回滚
    boolean isCompleted; // 是否已完成
}
```

1. 注解@EnableTransactionManagement 实现事务相关的Bean加载（现在自动配置使用AutoConfiguration实现）
2. TransactionInterceptor 主要的实现类，继承TransactionAspectSupport（定义了事务实现的方式）
3. 实现原理为使用AOP+ThreadLocal实现。

**事务能否生效数据库引擎是否支持事务是关键。比如常用的 MySQL 数据库默认使用支持事务的innodb引擎。但是，如果把数据库引擎变为 myisam，那么程序也就不再支持事务了！**
> 详细可见spring 源码部分

### <a name="15">编程式事务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 使用 TransactionManager 进行编程式事务管理
2. 使用TransactionTemplate 进行编程式事务管理
```java
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    private JdbcTemplate jdbcTemplate;
  /**
     * 使用transaction manager实现编程性事务
     */
    @GetMapping("/manager")
    public void testManager() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            String sql = "INSERT INTO `demo` (`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (?, 11, ?, '1', '123', '12', '12.000', '2020-11-19 19:36:07', '2020-11-07 19:36:11', '2020-11-07 19:36:18', '123', '123', NULL, NULL, NULL, '0') ";
            Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
            jdbcTemplate.update(sql, objects);
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
    }

    /**
     * 使用transaction Template 实现编程性事务
     */
    @GetMapping("/template")
    public void testTemplate() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    String sql = "INSERT INTO `demo` (`demo_id`, `demo_code`, `demo_name`, `status`, `status_desc`, `demo_qty`, `demo_rate`, `start_date`, `end_date`, `create_time`, `create_by`, `create_by_name`, `update_time`, `update_by`, `update_by_name`, `version`) VALUES (?, 11, ?, '1', '123', '12', '12.000', '2020-11-19 19:36:07', '2020-11-07 19:36:11', '2020-11-07 19:36:18', '123', '123', NULL, NULL, NULL, '0') ";
                    Object[] objects = new Object[]{String.valueOf(random.nextInt(1000000)), "test"};
                    jdbcTemplate.update(sql, objects);
                    // ....  业务代码
                } catch (Exception e){
                    //回滚
                    transactionStatus.setRollbackOnly();
                }

            }
        });
    }
```
### <a name="16">@Transactional 声明式事务管理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```java
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class, readOnly = false, timeout = -1)
@GetMapping("/update")
public String update() {
... 
}
```
### <a name="17">声明式事务相关属性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

| 属性名      | 说明                                                                                         |
| :---------- | :------------------------------------------------------------------------------------------- |
| propagation | 事务的传播行为，默认值为 REQUIRED，可选的值在上面介绍过                                      |
| isolation   | 事务的隔离级别，默认值采用 DEFAULT，可选的值在上面介绍过                                     |
| timeout     | 事务的超时时间，默认值为-1（不会超时）。如果超过该时间限制但事务还没有完成，则自动回滚事务。 |
| readOnly    | 指定事务是否为只读事务，默认值为 false。                                                     |
| rollbackFor | 用于指定能够触发事务回滚的异常类型，并且可以指定多个异常类型。                               |


### <a name="18">事务传播行为</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**事务传播行为是为了解决业务层方法之间互相调用的事务问题。**

汇总：
- **Propagation.REQUIRED**：如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。
- Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。
- **Propagation.MANDATORY**：如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常IllegalTransactionStateException。

- **Propagation.REQUIRES_NEW**：重新创建一个新的事务，如果当前存在事务，延缓当前的事务。
- **Propagation.NESTED**：如果没有，就新建一个事务；如果有，就在当前事务中嵌套其他事务。

- Propagation.NOT_SUPPORTED：以非事务的方式运行，如果当前存在事务，暂停当前的事务。
- Propagation.NEVER：以非事务的方式运行，如果当前存在事务，则抛出异常。
  
### <a name="19">spring transaction的隔离级别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- TransactionDefinition.ISOLATION_DEFAULT :使用后端数据库默认的隔离级别，MySQL 默认采用的 REPEATABLE_READ 隔离级别 Oracle 默认采用的 READ_COMMITTED 隔离级别.
- TransactionDefinition.ISOLATION_READ_UNCOMMITTED :最低的隔离级别，使用这个隔离级别很少，因为它允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读
- TransactionDefinition.ISOLATION_READ_COMMITTED : 允许读取并发事务已经提交的数据，可以阻止脏读，但是幻读或不可重复读仍有可能发生
- TransactionDefinition.ISOLATION_REPEATABLE_READ : 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。
- TransactionDefinition.ISOLATION_SERIALIZABLE : 最高的隔离级别，完全服从 ACID 的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。


### <a name="20">事务失效场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 注解导致的事务失效：
    1. @Transactional 注解属性 propagation 设置错误，设置了以非事务的状态运行
    2. @Transactional  注解属性 rollbackFor 设置错误，实际抛出的错误跟设置不一致
2. 方法修饰符导致的事务失效
    1. @Transactional 应用在非 public 修饰的方法上，在事务方法拦截执行中，非public方法不执行。
    2. 在动态代理层面，若为也需要代理的方法为public才能正常代理。如JDK动态代理，通过接口代理，接口方法默认都是public。而Cglib基于类的代理中会默认判断是否为public方法。
3. 同一个类中方法调用，导致@Transactional失效，在当前的bean中非事务方法调用事务方法为什么不生效？
    - 当进行**方法拦截**的时候，方法拦截器首先获取当前动态代理的对象所代理的原始对象。如果判断当前的方法比如save方法没有Advice(增强)，则直接调用原对象的方法，即这个时候调用的是FirstApp.save方法。
4. 异常被 catch 导致@Transactional失效
5. 数据库引擎不支持事务

#### <a name="21"> 同一个方法调用无事务的解决方案</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当这个方法被同一个类调用的时候，spring无法将这个方法加到事务管理中。只有在代理对象之间进行调用时，可以触发切面逻辑。
1. 使用 ApplicationContext 上下文对象获取该对象;
2. 使用 AopContext.currentProxy() 获取代理对象,但是需要配置exposeProxy=true


## <a name="22">Spring boot 自动配置的加载流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 在上下文初始化中invoke**PostProcessor是有执行等级之分的。\
> 自动配置加载主要在上下文初始化的invokeBeanFactoryPostProcessors(beanFactory);
1. Spring boot的配置自动加载主要通过@SpringBootApplication 中的 @EnableAutoConfiguration注解实现
2. 注解中@Import(AutoConfigurationImportSelector.class) 的类。借助@Import的支持，收集和注册特定场景相关的bean定义。
3. 该AutoConfigurationImportSelector类的getAutoConfigurationEntry方法会扫描所有包下spring-autoconfigure-metadata.properties的属性
4. 通过@ConditionOn的系列注解并对比过滤符合当前配置的配置项，重新进行config的注解扫描添加需要的bean配置到BenDefinition中
5. 再执行初始化方法。

## <a name="23">Spring mvc 工作原理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
流程说明（重要）：
> 一个http请求发送过来先经过Servlet的filter进行过滤，之后进入MVC流程
1. 客户端（浏览器）发送请求，直接请求到 DispatcherServlet。
2. DispatcherServlet 根据请求信息调用 HandlerMapping，解析请求对应的 Handler。
3. 解析到对应的 Handler（也就是我们平常说的 Controller 控制器）后，开始由 HandlerAdapter 适配器处理。
4. HandlerAdapter 会根据 Handler 来调用真正的处理器来处理请求，并处理相应的业务逻辑。调用handler的时候，如果有继承HandlerInterceptor接口，就对应拦截处理。
5. 处理器处理完业务后，会返回一个 ModelAndView 对象，Model 是返回的数据对象，View 是个逻辑上的 View。
6. ViewResolver 会根据逻辑 View 查找实际的 View。
7. DispatcherServlet 把返回的 Model 传给 View Resolver（视图渲染）。
8. 把 View 返回给请求者（浏览器）

## <a name="24">BeanFactory和ApplicationContext的区别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

BeanFactory：负责配置、创建、管理bean，IOC功能的实现主要就依赖于该接口子类实现。
- 针对bean对象数据加载都是懒加载模式，只有在使用到某个Bean时(调用getBean())，才对该Bean进行加载实例化。

ApplicationContext 是 Spring 应用程序中的中央接口，用于向应用程序提供配置信息。它继承了 BeanFactory 接口，所以 ApplicationContext 包含 BeanFactory 的所有功能以及更多功能
其主要实现的接口与功能如下：
- MessageSource，主要用于国际化数据资源的加载，如存在中文的配置文件。
- ApplicationEventPublisher，提供了事件发布功能，事件驱动模型。
- EnvironmentCapable，可以获取容器当前运行的环境
- ResourceLoader，主要用于加载资源文件，提供底层资源的访问的能力
- BeanFactory接口
- 非延迟加载：ApplicationContext是在容器启动时，一次性创建了所有的Bean。这样，在容器启动时，我们就可以发现Spring中存在的配置错误。
- 后置处理器注册方式：两者都支持BeanPostProcessor、BeanFactoryPostProcessor的使用，但两者之间的区别是：BeanFactory需要手动注册，而ApplicationContext则是自动注册
- 提供Web及Aop的支持

BeanFactory是Spring框架的基础设施，面向Spring本身；而ApplicationContext面向使用Spring的开发者，相比BeanFactory提供了更多面向实际应用的功能，几乎所有场合都可以直接使用ApplicationContext而不是底层的BeanFactory

ApplicationContext 还区分不同类型的上下文：
1. ClassPathXmlApplicationContext：从类路径加载配置文件的上下文。
2. AnnotationApplicationContext：基于注解加载配置的上下文。
3. ServletWebServerApplicationContext：提供web应用的服务的上下文。


## <a name="25">spring容器启动过程、spring boot启动过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
spring boot启动过程
1. 新建SpringApplication对象，实例化ApplicationContextInitializer和ApplicationListener
2. 实例化EventPublishRunLister，用于事件驱动模型的广播。
3. prepareEnvironment准备环境，并广播事件。
    - > 环境准备的时候，BootStrapApplicationListener会启动父类上下文，并加载对应的父类配置(spring cloud的应用)。添加CloseContextOnFailureApplicationListener，用于关闭父类上下文的监听器
4. 区分类型创建上下文。分为server、reactive和默认基于注解的三种
5. prepareContext准备上下文，调用初始化器的初始化方法
    - > BeanDefinition的加载是通过BeanDefinitionHolder填充BeanDefinition的属性，再注册到Context的BeanFactory中
6. refresh 刷新上下文，区分上下文类型创建bean工厂。
    1. 对象锁 + AtomicBoolean 锁定刷新过程。
    2. 区分类型创建 BeanFactory
    3. 进入postProcessor的调用。其中invokeBeanFactoryPostProcessors触发自动配置的加载流程
    4. 对于web 类型的上下文，新建对应的嵌入式Server
    5. 对于所有非懒加载的Bean对象，触发bean 的实例化
    6. 完成初始化后，启动server容器

## <a name="26">@RestController vs @Controller</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
@RestController
```java
@Controller
@ResponseBody
public @interface RestController { ... }
```
> 单独使用 @Controller 不加 @ResponseBody的话返回一个视图，这种情况属于比较传统的Spring MVC 的应用

### <a name="27">@ResponseBody</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
@ResponseBody 注解的作用是将 Controller 的方法返回的对象通过适当的**转换器**转换为指定的格式之后，写入到HTTP 响应(Response)对象的 body 中，通常用来返回 JSON 或者 XML 数据，返回 JSON 数据的情况比较多。

- 自定义MessageConvert？
TODO
## <a name="28">spring中的设计模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 工厂设计模式 : Spring使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
- 代理设计模式 : Spring AOP 功能的实现。
- 单例设计模式 : Spring 中的 Bean 默认都是单例的。
- 模板方法模式 : Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
- 包装器设计模式 : 我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。
- 观察者模式: Spring 事件驱动模型就是观察者模式很经典的一个应用。
- 适配器模式 :Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配Controller。

## <a name="29">@Import 注解</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 直接填class数组方式
   > @Import({ 类名.class , 类名.class... })
2. ImportSelector方式
    > 实现ImportSelector接口，返回一个String数组，将全类名返回。
     ```java
    public class Myclass implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata annotationMetadata) {
            return new String[]{"com.yc.Test.TestDemo3"};
        }
    }
    ```
3. ImportBeanDefinitionRegistrar方式 (手动注册bean到容器)



[spring注解之@Import注解的三种使用方式](https://www.cnblogs.com/yichunguo/p/12122598.html)

## <a name="30">零散的一些面试题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="31">@Autowired和@Resource的区别是什么？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. @Autowired注解是按类型装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它required属性为false。可以结合@Qualifier注解一起使用。
2. @Resource注解和@Autowired一样，也可以标注在字段或属性的setter方法上，但它默认按名称装配。默认按byName自动注入，也提供按照byType 注入；
3. @Resources按名字，是JDK的，@Autowired按类型，是Spring的。
4. 处理这2个注解的BeanPostProcessor不一样CommonAnnotationBeanPostProcessor是处理@Resource注解的，AutoWiredAnnotationBeanPostProcessor是处理@AutoWired注解的


### <a name="32">@PostConstruct和@PreDestroy</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
@PostConstruct和@PreDestroy 是两个作用于 Servlet 生命周期的注解，被这两个注解修饰的方法可以保证在整个 Servlet 生命周期只被执行一次，即使 Web 容器在其内部中多次实例化该方法所在的 bean。
  - @PostConstruct : 用来修饰方法，标记在项目启动的时候执行这个方法,一般用来执行某些初始化操作比如全局配置。PostConstruct 注解的方法会在构造函数之后执行,Servlet 的init()方法之前执行。
  - @PreDestroy : 当 bean 被 Web 容器的时候被调用，一般用来释放 bean 所持有的资源。。@PreDestroy 注解的方法会在Servlet 的destroy()方法之前执行。
  - 实现Spring 提供的 InitializingBean和 DisposableBean接口的效果和使用@PostConstruct和@PreDestroy 注解的效果一样
    > 建议您不要使用 InitializingBean回调接口，因为它不必要地将代码耦合到 Spring
```java
@Configuration
public class MyConfiguration {
    public MyConfiguration() {
      System.out.println("构造方法被调用");
    }
    
    @PostConstruct
    private void init() {
      System.out.println("PostConstruct注解方法被调用");
    }
    
    @PreDestroy
    private void shutdown() {
      System.out.println("PreDestroy注解方法被调用");
    }
}
```
### <a name="33">Spring 的异常处理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用 @ControllerAdvice 和 @ExceptionHandler处理全局异常
```java
@RestControllerAdvice(basePackages = {"com.design.apidesign.controller"}) 
public class ExceptionControllerAdvice { 

      @ExceptionHandler(MethodArgumentNotValidException.class)
      public String MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) { }
} 
```

@ResponseStatusException：通过 ResponseStatus注解简单处理异常的方法（将异常映射为状态码）。
```java
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourseNotFoundException2 extends RuntimeException {

   public ResourseNotFoundException2() {
   }

   // status ： http status 
   // reason ：response 的消息内容
   // cause ： 抛出的异常
   public ResponseStatusException(HttpStatus status, @Nullable String reason, @Nullable Throwable cause) {
        super(null, cause);
        Assert.notNull(status, "HttpStatus is required");
        this.status = status;
        this.reason = reason;
   }

   public ResourseNotFoundException2(String message) {
       super(message);
   }
}
```

### <a name="34"> json 数据处理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- @JsonIgnoreProperties 作用在类上用于过滤掉特定字段不返回或者不解析
- @JsonIgnore一般用于类的属性上，作用和上面的@JsonIgnoreProperties 一样。
- @JsonFormat一般用来格式化 json 数据。
- @JsonUnwrapped扁平化对象

```
  //before
  {
      "location": {
          "provinceName":"湖北",
          "countyName":"武汉"
      },
      "personInfo": {
          "userName": "coder1234",
          "fullName": "shaungkou"
      }
  }
  
  
  @Getter
  @Setter
  @ToString
  public class Account {
      @JsonUnwrapped
      private Location location;
      @JsonUnwrapped
      private PersonInfo personInfo;
      ......
  }
  
  
  //after 
  {
    "provinceName":"湖北",
    "countyName":"武汉",
    "userName": "coder1234",
    "fullName": "shaungkou"
  }
```
  
### <a name="35"> @Component 和 @Bean 的区别是什么？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
作用对象不同: @Component 注解作用于类，而@Bean注解作用于方法。
  - @Component通常是通过类路径扫描来自动侦测以及自动装配到Spring容器中（我们可以使用 @ComponentScan 注解定义要扫描的路径从中找出标识了需要装配的类自动装配到 Spring 的 bean 容器中）。@Bean 注解通常是我们在标有该注解的方法中定义产生这个 bean,@Bean告诉了Spring这是某个类的示例，当我需要用它的时候还给我。
  - @Bean 注解比 Component 注解的自定义性更强，而且很多地方我们只能通过 @Bean 注解来注册bean。比如当我们引用第三方库中的类需要装配到 Spring容器时，则只能通过 @Bean来实现。