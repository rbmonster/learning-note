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
 
### Spring boot 自动配置的加载流程
- postProcessor是有执行等级之分的。
- invokeBeanFactoryPostProcessors(beanFactory);
1. Spring boot的配置自动加载主要通过 @EnableAutoConfiguration注解实现
2. 注解中@Import(AutoConfigurationImportSelector.class) 的类
3. 该类的getAutoConfigurationEntry方法会扫描所有包下spring-autoconfigure-metadata.properties的属性，并对比过滤符合当前配置的配置项。
4. 重新进行config的注解扫描添加需要的bean配置到BenDefinition中
5. 再执行初始化方法。