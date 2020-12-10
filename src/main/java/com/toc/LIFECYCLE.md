<a name="index">**Index**</a>

<a href="#0">bean初始化流程</a>  
&emsp;<a href="#1">1. 接口介绍</a>  
&emsp;&emsp;<a href="#2">1.1. Bean的完整生命周期及方法调用</a>  
&emsp;&emsp;<a href="#3">1.2. Bean 声明周期级别的接口及方法</a>  
&emsp;&emsp;<a href="#4">1.3. BeanDefinitionRegistryPostProcessor接口</a>  
&emsp;&emsp;<a href="#5">1.4. BeanFactoryPostProcessor接口</a>  
&emsp;&emsp;<a href="#6">1.5. InstantiationAwareBeanPostProcessor接口</a>  
&emsp;&emsp;<a href="#7">1.6. BeanPostProcessor接口：</a>  
&emsp;<a href="#8">2. Bean初始化顺序</a>  
&emsp;&emsp;<a href="#9">2.1. 初始化Bean的所有接口调用流程</a>  
&emsp;&emsp;<a href="#10">2.2. BeanProcessor 调用流程</a>  
# <a name="0">bean初始化流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">接口介绍</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">Bean的完整生命周期及方法调用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
方法可以划分为以下几类：
1. Bean自身的方法：这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法
2. Bean级生命周期接口方法：这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DisposableBean这些接口的方法
3. 容器级生命周期接口方法：这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。
4. 工厂后处理器接口方法：这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。

### <a name="3">Bean 声明周期级别的接口及方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- BeanFactoryAware接口：让Bean获取配置他们的BeanFactory的引用。
- BeanNameAware接口：让Bean可以设置bean中的name。
- InitializingBean接口：为bean提供了定义初始化方法的方式。ThreadPoolExecutorFactoryBean 使用该接口创建线程池。
- DisposableBean接口：销毁接口调用。

spring初始化bean有两种方式：
  1. 实现InitializingBean接口，继而实现afterPropertiesSet的方法
  2. 反射原理，配置文件使用init-method标签直接注入bean
    

### <a name="4">BeanDefinitionRegistryPostProcessor接口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义： 允许在常规上下文对象初始化之前，注册更多的bean定义

### <a name="5">BeanFactoryPostProcessor接口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：可以管理我们的bean工厂内所有的beanDefinition（未实例化）数据，可以随心所欲的修改属性。

### <a name="6">InstantiationAwareBeanPostProcessor接口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
主要作用：作用在于目标对象的实例化过程中需要处理的事情，包括实例化对象的前后过程以及实例的属性设置
- postProcessBeforeInstantiation(Class<?> beanClass, String beanName)：实例化、依赖注入前，在调用显示的初始化之前完成一些定制的初始化任务。
    - 如**AbstractAutoProxyCreator创建代理对象**，其返回值将替代原始的Bean对象；
- postProcessAfterInstantiation(Object bean, String beanName)：对象初始化方法调用完成后，对对象的修改；
- postProcessProperties(PropertyValues pvs, Object bean, String beanName)：对象值修改触发的方法。
  
### <a name="7">BeanPostProcessor接口：</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- postProcessBeforeInitialization(Object bean, String beanName)：实例化完成对对象的修改，如**BeanValidationPostProcessor的@Valid验证对象数据**
- postProcessAfterInitialization(Object bean, String beanName) : 对象所有初始化方法调用完成后，对对象的修改。



## <a name="8">Bean初始化顺序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
【BeanDefinitionRegistryPostProcessor接口】初始化
【BeanDefinitionRegistryPostProcessor接口】调用postProcessBeanDefinitionRegistry方法
【BeanDefinitionRegistryPostProcessor接口】调用postProcessBeanFactory方法
【BeanFactoryPostProcessor接口】初始化
【BeanFactoryPostProcessor接口】调用postProcessBeanFactory方法
【BeanPostProcessor接口】初始化
【InstantiationAwareBeanPostProcessor接口】初始化

【InstantiationAwareBeanPostProcessor接口】调用postProcessBeforeInstantiation
        【Bean对象】具体初始化
【InstantiationAwareBeanPostProcessor接口】调用postProcessAfterInstantiation
【InstantiationAwareBeanPostProcessor接口】调用postProcessProperties
        【BeanNameAware接口】调用BeanNameAware.setBeanName()
        【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
    【BeanPostProcessor接口】方法postProcessBeforeInitialization对属性进行更改！
【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
    【init-method】调用@bean的init-method属性指定的初始化方法
    【BeanPostProcessor接口】方法postProcessAfterInitialization对属性进行更改！
【DisposableBean接口】调用DisposableBean.destroy()
【destroy-method】调用@bean的destroy-method属性指定的初始化方法


IOC:
invokeBeanFactoryPostProcessors(beanFactory);  -> BeanFactoryProcessor

finishBeanFactoryInitialization(beanFactory);  -> InstantiationAwareBeanPostProcessor、BeanPostProcessor
```
 
### <a name="9">初始化Bean的所有接口调用流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- AbstractAutowireCapableBeanFactory.doCreateBean
- AbstractAutowireCapableBeanFactory.populateBean
    - 实例化前置方法调用
- AbstractAutowireCapableBeanFactory.initializeBean
    - 实例化后方法调用

- aware 初始化Bean的相关接口调用流程
```
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeAwareMethods

    private void invokeAwareMethods(final String beanName, final Object bean) {
		if (bean instanceof Aware) {
			if (bean instanceof BeanNameAware) {
				((BeanNameAware) bean).setBeanName(beanName);
			}
			if (bean instanceof BeanClassLoaderAware) {
				ClassLoader bcl = getBeanClassLoader();
				if (bcl != null) {
					((BeanClassLoaderAware) bean).setBeanClassLoader(bcl);
				}
			}
			if (bean instanceof BeanFactoryAware) {
				((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
			}
		}
	}
```

### <a name="10">BeanProcessor 调用流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)

    @Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
			throws BeansException {

		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessBeforeInitialization(result, beanName);
			if (current == null) {
				return result;
			}
			result = current;
		}
		return result;
	}
```