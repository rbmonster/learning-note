#Spring boot 

## Spring boot start
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/picture/springBootLoadother.png)

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/picture/springLoad.jpg)

## spring 构造方法
```
@SuppressWarnings({ "unchecked", "rawtypes" })
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    this.resourceLoader = resourceLoader;
    Assert.notNull(primarySources, "PrimarySources must not be null");
    this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
    this.webApplicationType = WebApplicationType.deduceFromClasspath();
    // 加载配置 spring-boot/spring-boot-2.2.6.RELEASE.jar!/META-INF/spring.factories
    // 过滤出 org.springframework.context.ApplicationContextInitializer的相关Initializer，通过反射实例化
    setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    // 同上读取配置文件中org.springframework.context.ApplicationListener，反射并实例化
    setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    this.mainApplicationClass = deduceMainApplicationClass();
}
```
## Spring boot的run方法
```
    // spring boot start
	public ConfigurableApplicationContext run(String... args) {
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		configureHeadlessProperty();
        // 过滤配置文件中SpringApplicationRunListener的监听器，反射实例化，仅一个EventPublishingRun
        // org.springframework.boot.context.event.EventPublishingRunListener
        SpringApplicationRunListeners listeners = getRunListeners(args);
        // 主要用来发布事件监听，新建时间组播器并将一开始获取的ApplicationListener注册到组播器上
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            // 1.根据类型准备环境变量 2.触发preparedEvent事件监听，通过EventListener，触发组播器广播事件
            // 环境准备的时候，BootStrapApplicationListener会启动父类上下文，并加载对应的父类配置。添加CloseContextOnFailureApplicationListener，用于关闭父类上下文的监听器
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
			configureIgnoreBeanInfo(environment);
			Banner printedBanner = printBanner(environment);
            // 区分类型创建Application的上下文，分为SERVER、REACTIVE和默认的上下文三种
			context = createApplicationContext();
            // 反射实例化用于异常分析的分析器
			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
            // 准备上下文！！！  见下一节
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
            // 调用ApplicationRunner、CommandLineRunner的runner方法
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, listeners);
			throw new IllegalStateException(ex);
		}

		try {
            // 广播运行事件
			listeners.running(context);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, null);
			throw new IllegalStateException(ex);
		}
		return context;
	}
```

## prepare准备上下文
```
	private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment,
			SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
		context.setEnvironment(environment);
        // 触发上下文的后置处理器（目前没看到），并设置一个懒加载的对话处理器，默认配置了适合于大多数Spring引导应用程序的转换器和格式化程序。
		postProcessApplicationContext(context);
        // 总共11个初始化器，调用六个初始化器的initializer方法
        // BootStrapApplication的几个初始化器在此调用
        // 添加两个beanFactoryPostProcessor，SharedMetadataReaderFactoryContextInitializer（ConfigurationClassPostProcessor 的后置处理器）和ConfigurationWariningsApplicationContextInitializer
        // 添加ServerPortInfo到ApplicationEventListener
		applyInitializers(context);
        // 触发监听器的contextPrepared事件，此处仅过滤出两个listener(BackGroundPre...,DelegatingApplicationListener）
		listeners.contextPrepared(context);
        // 打印当前启用的配置
		if (this.logStartupInfo) {
			logStartupInfo(context.getParent() == null);
			logStartupProfileInfo(context);
		}
		// Add boot specific singleton beans（注册一个单例bean）
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		beanFactory.registerSingleton("springApplicationArguments", applicationArguments);
        // 注册启动打印springboot的对象
		if (printedBanner != null) {
			beanFactory.registerSingleton("springBootBanner", printedBanner);
		}
        // 允许bean定义重写
		if (beanFactory instanceof DefaultListableBeanFactory) {
			((DefaultListableBeanFactory) beanFactory)
					.setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
		}
		if (this.lazyInitialization) {
			context.addBeanFactoryPostProcessor(new LazyInitializationBeanFactoryPostProcessor());
		}
		// Load the sources
		Set<Object> sources = getAllSources();
		Assert.notEmpty(sources, "Sources must not be empty");
        // 实例化BeanDefinitionLoader，调用load的方法，读取SpringBootApplication及BootStrapApplicationListener上下文的bean定义
        // BeanDefinition的加载是通过BeanDefinitionHolder填充BeanDefinition的属性，再注册到Context的BeanFactory中
		load(context, sources.toArray(new Object[0]));
        // 通知监听器上下文装载事件，这时候过滤出5个注册该事件的listener
		listeners.contextLoaded(context);
	}
```

## refresh方法
调用上下文的refresh方法
  - AbstractRefreshableApplicationContext:这个类在每次调用refresh方法的时候都会产生一个新的beanfactory实例(通常是，但是不是必须的)。这个应用上下文会通过一系列的配置文件去加载BeanDefinition。在调用refresh方法的时候才会创建内部持有的BeanFacoty实例(可以参见该类中的refreshBeanFactory方法）
  - GenericApplicationContext:这个类内部持有唯一的一个DefaultListableBeanFactory实例，而且相较于其它ApplicationContext的实现类，这个类在创建的时候就会有一个BeanFactory的实例，意思就是在refresh方法调用前，内部持有的BeanFactory实例就已经创建，且这个类从开始到最终都是一个BeanFacoty实例。
```
    @Override
	public void refresh() throws BeansException, IllegalStateException {
        // 使用对象锁
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
            // 通过设置AutomaticBoolean类型的close 和 active变量，表示环境开始刷新。
            // 验证环境的信息，对于WebServer的上下文清空metadata cache。
			prepareRefresh();
            
            // 看上下文的类型，如果是可更新的会创建beanFactory，而GenericApplicationContext，就设置一个工厂的序列化ID
			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
            
            // 准备spring BeanFactory，注入ClassLoader等
            // 设置beanFactory.ignoreDependencyInterface,即针对ApplicationContext和BeanFactory这种使用PostProcessor的进行注入的bean，在Autowire中自动忽略注入。
            //  注册两个后置处理器ApplicationContextAwareProcessor、ApplicationListenerDetector
			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
                // 主要注册了 Register ServletContextAwareProcessor(BeanPostProcessor)
               // Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

                 // 调用三个BeanFactory级别的后置处理器，通过SharedMetadataReaderFactoryContextInitializer，注册对应的ConfigurationClassPostProcessor
                // 接着调用BeanDefinitionRegistryPostProcessor 级别的方法， 上述注册的ConfigurationClassPostProcessor被调用加载@Configuration
                // @SpringBootApplication 注解的类，在这边开始被加载进来的。并调用@EnableAutoConfiguration 的导入类@Import(AutoConfigurationImportSelector.class)
                    // AutoConfigurationImportSelector会先加载进所有配置，先过滤exclude的autoConfiguration，在加载配置过滤器ConfigurationFilter，过滤配置（主要过滤@ConditionOnClass之类的依赖，并标明该AutoConfiguration是否需要加载）
                    // 过滤完配置，调用AutoConfigurationImportEvent的事件监听，记录配置过滤结果，并且AutoConfigurationImportSelector 排序记录需要加载的autoConfiguration entry
                    // 排序完的configurationList，通过栈的调用方式，依次加载@Configuration中@imports的类。最终ConfigurationClassParser会记载需要按顺序加载的Configuration List
                // 加载排序完成后的Configration的BeanDefinition，RefreshScope修改BeanDefinition（热加载，配置修改相关事项）
                // 最后调用BeanFactory级别的后置处理器。这边加入了propertySourcesPlaceholderConfigurer 用于替换占位符的。BeanFactory调用时区分后置处理器的顺序调用。
				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);
                
                // 注册BeanPostProcessor级别的后置处理器，先注册优先级高的
				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				initMessageSource();
                
				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

                 // 对于Web的ApplicationContext，在这边新建初始化TomcatServer
                // 此时与Spring web 相关的东西被注册到beanfactory中
				// Initialize other special beans in specific context subclasses.
				onRefresh();
                
                // 此处把当前上下文的Listener注册到BeanFactory中
				// Check for listener beans and register them.
				registerListeners();
                
                // 实例化所有剩余的非懒加载的Bean, 这边调用到了doGetBean的方法
                // 实例化对象的时候就触发Bean声明周期对应的aware及后置处理器，AbstractAutowireCapableBeanFactory.resolveBeforeInstantiation
                // 这时候对象实例化完成后，通过postProcessAfterInitialization，AbstractAutoProxyCreator就开始包装返回代理对象，AbstractAutoProxyCreator.createProxy并把对应的方法拦截织入对象中
                // DefaultAopProxyFactory 中判断使用JDK代理还是Cglib代理。如果代理对象有拦截链条，封装ReflectiveMethodInvocation进行调用（常见的AOP方法环节）
				// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);

                // 清理资源缓存，初始化并调用ContextLifecyclePostProcessor
                // 广播上下文装载完成的事件
                // 启动webServer，广播ServletWebServerInitializedEvent
				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}
			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
```

---

## spring boot相关知识
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/picture/BootStrapLoad.png)
Spring Cloud 构建于 Spring Boot 之上，在 Spring Boot 中有两种上下文，一种是 bootstrap,另外一种是 application,
- application 配置文件这个容易理解，主要用于 Spring Boot 项目的自动化配置。
- bootstrap 是应用程序的父上下文，也就是说 bootstrap 加载优先于 applicaton。
- bootstrap 主要用于从额外的资源来加载配置信息，还可以在本地外部配置文件中解密属性。
- 这两个上下文共用一个环境，它是任何Spring应用程序的外部属性的来源。
- bootstrap 里面的属性会优先加载，它们默认也不能被本地相同配置覆盖。
- boostrap 由父 ApplicationContext 加载，比 applicaton 优先加载
- boostrap 里面的属性不能被覆盖

bootstrap 配置文件有以下几个应用场景。
1. 使用 Spring Cloud Config 配置中心时，这时需要在 bootstrap 配置文件中添加连接到配置中心的配置属性来加载外部配置中心的配置信息；
2. 一些加密/解密的场景；



Spring 与其他形式的重写应用程序上下文属性相比，application properties属性文件具有最低的优先级。Spring Boot提供了不同的方法来覆盖这些属性。
- 我们可以在代码、命令行参数、ServletConfig init参数、ServletContext init参数、Java系统属性、操作系统变量和应用程序属性文件中覆盖这些参数。
- 倾向于将可以在应用程序上下文中覆盖的属性分组:
    - 核心属性(日志属性、线程属性)
    - 集成属性(RabbitMQ属性，ActiveMQ属性)
    - Web属性(HTTP属性，MVC属性)
    - 安全属性(LDAP属性、OAuth2属性)

bootstrap Context：负责从外部源加载配置属性，以及用于在本地外部配置文件中解密属性。
  - 要记住的另一个关键点是，这两个上下文共享环境，而环境是任何Spring应用程序的外部属性的源。

bootstrap Context 加载配置的源可以是文件系统，甚至是git存储库。这些服务使用它们的spring-cloud-config-client依赖关系来访问配置服务器。


