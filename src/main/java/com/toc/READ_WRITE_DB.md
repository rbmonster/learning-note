<a name="index">**Index**</a>

<a href="#0">spring 数据库读写分离</a>  
&emsp;<a href="#1">1. 启动类排除自动配置</a>  
&emsp;<a href="#2">2. 循环引用问题</a>  
&emsp;<a href="#3">3. 数据库初始化相关</a>  
&emsp;&emsp;<a href="#4">3.1. DataSourceAutoConfiguration 数据库自动加载</a>  
&emsp;&emsp;&emsp;<a href="#5">3.1.1. DataSourcePoolMetadataProvidersConfiguration</a>  
&emsp;&emsp;&emsp;<a href="#6">3.1.2. DataSourceInitializationConfiguration </a>  
&emsp;&emsp;<a href="#7">3.2. DruidDataSourceAutoConfigure 阿里数据库</a>  
&emsp;<a href="#8">4. AbstractRoutingDataSource 抽象类</a>  
&emsp;<a href="#9">5. 读写分离实现</a>  
&emsp;&emsp;<a href="#10">5.1. 初始化数据库对象</a>  
&emsp;&emsp;<a href="#11">5.2. 初始化DataSourceRouter</a>  
&emsp;&emsp;<a href="#12">5.3. 具体使用</a>  
&emsp;&emsp;&emsp;<a href="#13">5.3.1. Aop 实现</a>  
&emsp;&emsp;&emsp;<a href="#14">5.3.2. 方法</a>  
&emsp;<a href="#15">6. 相关资料</a>  
# <a name="0">spring 数据库读写分离</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

数据库的读写分离，首先要把spring 中的自动加载的类排除掉，因为我们配置文件配置了多数据源，并且希望自己主导sql语句执行的数据库。

## <a name="1">启动类排除自动配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class})
```

## <a name="2">循环引用问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
┌─────┐
|  dataSource defined in class path resource [com/design/readwritedb/config/DataSourceConfig.class]
↑     ↓
|  dbMaster defined in class path resource [com/design/readwritedb/config/DataSourceConfig.class]
↑     ↓
|  org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker
└─────┘
```
问题出现原因：
1. 自定义的datasource初始化的时候过程触发dataSourceInitializerPostProcessor后置处理器，而该后置处理器会触发DataSourceInitializerInvoker的初始化。
2. DataSourceInitializerInvoker的初始化需要构造器注入datasource对象。
3. 此处就导致的循环引用的问题

spring的循环引用的解决使用三级缓存，但是针对于循环引用都是构造器的情况就会抛出循环引用报错的问题。**缓存存储的是实例化的对象，而若对象实例化的构造器此时就需要注入对象，而循环引用的对象还未初始化完毕，那么就会出现循环引用的问题。**

解决方案：在springBoot 启动的时候exclude DataSourceAutoConfiguration

## <a name="3">数据库初始化相关</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="4">DataSourceAutoConfiguration 数据库自动加载</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- @ConditionalOnClass 注解判断是否需要加载数据库
- @EnableConfigurationProperties 注解用于加载配置文件中`spring.datasource`相关配置，会验证关键配置
- @Import方法借助两个Configuration生成数据库对象
`DataSourcePoolMetadataProvidersConfiguration.class, DataSourceInitializationConfiguration.class`
```
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ DataSource.class, EmbeddedDatabaseType.class })
@EnableConfigurationProperties(DataSourceProperties.class)
@Import({ DataSourcePoolMetadataProvidersConfiguration.class, DataSourceInitializationConfiguration.class })
public class DataSourceAutoConfiguration {
    ....
}
```
#### <a name="5">DataSourcePoolMetadataProvidersConfiguration</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
DataSourcePoolMetadataProvidersConfiguration 注册 DataSourcePoolMetadataProvider，用于提供一个当前应用支持的数据源


#### <a name="6">DataSourceInitializationConfiguration </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
DataSourceInitializationConfiguration 用于初始化数据源主要通过@Import调用自动配置加载DataSource
```
@Configuration(proxyBeanMethods = false)
@Import({ DataSourceInitializerInvoker.class, DataSourceInitializationConfiguration.Registrar.class })
class DataSourceInitializationConfiguration {
    ...
}
```
以下两个类主要用于尽快初始化DataSourceInitializerInvoker
- DataSourceInitializationConfiguration.Registrar 用于手动注册DataSourceInitializerPostProcessor 
- dataSourceInitializerPostProcessor在后置处理器级别通过getBean获取DataSourceInitializerInvoker，主要用于尽快初始化DataSource

DataSourceInitializerInvoker 是spring 上下文的事件驱动模型的监听器，
- 监听DataSourceSchemaCreatedEvent事件
- 继承InitializingBean接口，执行数据库初始化的操作  （ 目前主要通过这个触发）

```
    @Override
	public void afterPropertiesSet() {
		DataSourceInitializer initializer = getDataSourceInitializer();
		...
    }
    
    private DataSourceInitializer getDataSourceInitializer() {
    		if (this.dataSourceInitializer == null) {
                // 这边初始化Bean
    			DataSource ds = this.dataSource.getIfUnique();
    			if (ds != null) {
    				this.dataSourceInitializer = new DataSourceInitializer(ds, this.properties, this.applicationContext);
    			}
    		}
    		return this.dataSourceInitializer;
    	}
		
```

### <a name="7">DruidDataSourceAutoConfigure 阿里数据库</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
可以看出DruidDataSource的自动配置很简单，通过@AutoConfigureBefore在DataSource自动配置加载数据库前加载好数据库
```
@Configuration
@ConditionalOnClass({DruidDataSource.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class, DruidStatViewServletConfiguration.class, DruidWebStatFilterConfiguration.class, DruidFilterConfiguration.class})
public class DruidDataSourceAutoConfigure {
   @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        LOGGER.info("Init DruidDataSource");
        return new DruidDataSourceWrapper();
    }
}
```

## <a name="8">AbstractRoutingDataSource 抽象类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
AbstractRoutingDataSource 为DataSource接口的一个子类，提供了路由数据库的相关抽象功能，也是数据库读写分离的主要实现。

提供如下抽象方法，让继承子类返回需要执行的datasource Key
```
    @Nullable
    protected abstract Object determineCurrentLookupKey();
```



## <a name="9">读写分离实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>


### <a name="10">初始化数据库对象</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
因为自己配置数据源，所以需要在@Configuration中初始化对应的bean对象。

- 这里使用阿里的Druid数据源，可以结合@ConfigurationProperties与DuridBuilder生成数据源配置
```
    @Bean(name = "dbMaster")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource dbMaster() {
        log.info("generate master dataSource independently");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dbSlave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource dbSlave() {
        log.info("generate slave dataSource independently");
        return DruidDataSourceBuilder.create().build();
    }
```


配置文件配置
```
spring:
  application:
    name: test
  datasource:
    druid:
      master:
        driver-class-name: com.mysql.jdbc.Driver
        filters: stat
        testOnReturn: false
        testWhileIdle: true
        maxActive: 30
        removeAbandoned: true
        name: datasource
        testOnBorrow: false
        minIdle: 10
        initialSize: 5
        poolPreparedStatements: true
        url: jdbc:mysql://xxxx/tms?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
        type: com.alibaba.druid.pool.DruidDataSource
        validationQuery: select 1
        maxOpenPreparedStatements: 300
        username: root
        password: xxxx
      slave:
        driver-class-name: com.mysql.jdbc.Driver
        filters: stat
        testOnReturn: false
        testWhileIdle: true
        maxActive: 30
        removeAbandoned: true
        name: slaveDatasource    # DataSource命名要区分开，否则datasource 销毁时，会把同名的datasource一起销毁
        testOnBorrow: false
        minIdle: 10
        initialSize: 5
        poolPreparedStatements: true
        url: jdbc:mysql://xxxx:3306/tms?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
        type: com.alibaba.druid.pool.DruidDataSource
        validationQuery: select 1
        maxOpenPreparedStatements: 300
        username: root
        password: xxxx
```

### <a name="11">初始化DataSourceRouter</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 首先需要自定义一个DataSourceRouter类，通过继承AbstractRoutingDataSource，并实现determineCurrentLookup方法
2. 常规都是结合ThreadLocal 判断使用的数据源，并返回对应的key。
3. DynamicDataSourceRouter需要主动在@Configuration文件中实例化，要注入对应的数据库数据源。
4. 上面也提及了AbstractRoutingDataSource为DataSource的子类，通过@Primary注解，让所有需要引用datasource对象都优先使用该类进行初始化。
> 需要DataSource对象的类，如常见的JdbcTemplate以及数据库事务PlatformTransactionManager。对应的数据库配置类DataSourceTransactionManagerAutoConfiguration、JdbcTemplateConfiguration

自定义实现类：
```
public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DataSourceContextHolder.getCurrentDataSource();
        log.debug("当前使用数据源为: " + datasource);
        return datasource;
    }
}

```

初始化dynamicDataSourceRouter数据源：
```
    @Primary
    @Bean(name = "dataSource") // 对应Bean: DataSource
    public DataSource dynamicDataSource(@Qualifier("dbMaster") DataSource master, @Qualifier("dbSlave") DataSource slave) {
        DynamicDataSourceRouter dataSourceRouter = new DynamicDataSourceRouter();
        log.info(" ---------------------- dynamic dataSource configure begin----------------------");
        DruidDataSource druidDataSourceMaster = (DruidDataSource) master;
        DruidDataSource druidDataSourceSlave = (DruidDataSource) slave;
        //配置多数据源
        Map<Object, Object> map = new HashMap<>(5);
        map.put(DataSourceEnum.SLAVE.getName(), slave);
        map.put(DataSourceEnum.MASTER.getName(), master);    // key需要跟ThreadLocal中的值对应
        // master 作为默认数据源
        dataSourceRouter.setDefaultTargetDataSource(master);
        dataSourceRouter.setTargetDataSources(map);
        return dataSourceRouter;
    }

```

### <a name="12">具体使用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
动态切换数据库的实现，本质上只要改变ThreadLocal中的key，就能在具体数据库路由的时候走不同的数据库。

本文主要使用aop结合注解的方式实现。

#### <a name="13">Aop 实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 声明切面，获取注解上的数据库key
```
  @Pointcut("@annotation(DS)")
    public void dbAspect(){
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~this is logProcess!");
    }

    @Before("dbAspect()")
    public void changeDB(JoinPoint joinPoint){
        log.info("this is before");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DS annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DS.class);
        if (Objects.nonNull(annotation)) {
            DataSourceContextHolder.putDataSource(annotation.name().getName());
        }
    }
```

#### <a name="14">方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
@DS(name = DataSourceEnum.SLAVE)
@GetMapping("/readwriteByAnnotation")
public Map<String, Object> getFromDb1(){
    String sql = "select * from container_load limit 1";
    Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql);
    return stringObjectMap;
}
```

## <a name="15">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
mybatis相关插件：https://mybatis.plus/guide/dynamic-datasource.html

