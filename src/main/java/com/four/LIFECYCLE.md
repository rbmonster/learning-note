# bean初始化流程
## 接口介绍

### Bean的完整生命周期及方法调用
方法可以划分为以下几类：
1. Bean自身的方法：这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法
2. Bean级生命周期接口方法：这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DisposableBean这些接口的方法
3. 容器级生命周期接口方法：这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。
4. 工厂后处理器接口方法：这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。

### Bean 声明周期级别的接口及方法
- BeanFactoryAware接口：让Bean获取配置他们的BeanFactory的引用。
- BeanNameAware接口：让Bean可以设置bean中的name。
- InitializingBean接口：为bean提供了定义初始化方法的方式。ThreadPoolExecutorFactoryBean 使用该接口创建线程池。
- DisposableBean接口：销毁接口调用。

spring初始化bean有两种方式：
  1. 实现InitializingBean接口，继而实现afterPropertiesSet的方法
  2. 反射原理，配置文件使用init-method标签直接注入bean
    

### BeanDefinitionRegistryPostProcessor接口
定义： 允许在常规上下文对象初始化之前，注册更多的bean定义

### BeanFactoryPostProcessor接口
定义：可以管理我们的bean工厂内所有的beanDefinition（未实例化）数据，可以随心所欲的修改属性。

### InstantiationAwareBeanPostProcessor接口
主要作用：作用在于目标对象的实例化过程中需要处理的事情，包括实例化对象的前后过程以及实例的属性设置
- postProcessBeforeInstantiation(Class<?> beanClass, String beanName)：实例化、依赖注入前，在调用显示的初始化之前完成一些定制的初始化任务。
    - 如**AbstractAutoProxyCreator创建代理对象**，其返回值将替代原始的Bean对象；
- postProcessAfterInstantiation(Object bean, String beanName)：对象初始化方法调用完成后，对对象的修改；
- postProcessProperties(PropertyValues pvs, Object bean, String beanName)：对象值修改触发的方法。
  
### BeanPostProcessor接口：
- postProcessBeforeInitialization(Object bean, String beanName)：实例化完成对对象的修改，如**BeanValidationPostProcessor的@Valid验证对象数据**
- postProcessAfterInitialization(Object bean, String beanName) : 对象所有初始化方法调用完成后，对对象的修改。



## Bean初始化顺序
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
 
### 初始化Bean的所有接口调用流程
AbstractAutowireCapableBeanFactory.doCreateBean\
AbstractAutowireCapableBeanFactory.populateBean
> 实例化前置方法调用

AbstractAutowireCapableBeanFactory.initializeBean
> 实例化后方法调用

`aware` 初始化Bean的相关接口调用流程
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

### BeanProcessor 调用流程
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

### DisposableBean 销毁方法
Bean的销毁使用了适配器模式，注册销毁方法的时候，会根据是接口类型和配置类型统一交给 DisposableBeanAdapter 销毁适配器类来做统一处理。


为什么使用适配器的类？ 
> 因为销毁方法有两种甚至多种方式，目前有实现接口 DisposableBean、配置信息 destroy-method，两种方式。而这两种方式的销毁动作是由 AbstractApplicationContext 在注册虚拟机钩子后看，虚拟机关闭前执行的操作动作。那么在销毁执行时不太希望还得关注都销毁那些类型的方法，它的使用上更希望是有一个统一的接口进行销毁，所以这里就新增了适配类，做统一处理。

DisposableBeanAdapter做的工作：
- 执行`DestructionAwareBeanPostProcessors`
- 执行继承 `DisposableBean` 的bean
- 执行bean定义中的 `destroy-method` 方法

```
org.springframework.beans.factory.support.AbstractBeanFactory.registerDisposableBeanIfNecessary

// 创建bean 的时候，注册到DisposableBean 的Map中
protected void registerDisposableBeanIfNecessary(String beanName, Object bean, RootBeanDefinition mbd) {
    AccessControlContext acc = (System.getSecurityManager() != null ? getAccessControlContext() : null);
    // requiresDestruction方法，判断bean在销毁时需要执行销毁方法
    if (!mbd.isPrototype() && **requiresDestruction**(bean, mbd)) {
        if (mbd.isSingleton()) {
            // Register a DisposableBean implementation that performs all destruction
            // work for the given bean: DestructionAwareBeanPostProcessors,
            // DisposableBean interface, custom destroy method.
            // 适配器模式
            registerDisposableBean(beanName,
                    new DisposableBeanAdapter(bean, beanName, mbd, getBeanPostProcessors(), acc));
        }
        else {
            // A bean with a custom scope...
            Scope scope = this.scopes.get(mbd.getScope());
            if (scope == null) {
                throw new IllegalStateException("No Scope registered for scope name '" + mbd.getScope() + "'");
            }
            scope.registerDestructionCallback(beanName,
                    new DisposableBeanAdapter(bean, beanName, mbd, getBeanPostProcessors(), acc));
        }
    }
}

org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroySingleton

	private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

	public void destroySingleton(String beanName) {
		// Remove a registered singleton of the given name, if any.
		removeSingleton(beanName);

		// Destroy the corresponding DisposableBean instance.
		DisposableBean disposableBean;
		synchronized (this.disposableBeans) {
			disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
		}
		destroyBean(beanName, disposableBean);
	}


// 在上下文中注册钩子方法
org.springframework.context.support.AbstractApplicationContext.registerShutdownHook

 public void registerShutdownHook() {
        if (this.shutdownHook == null) {
            this.shutdownHook = new Thread("SpringContextShutdownHook") {
                public void run() {
                    synchronized(AbstractApplicationContext.this.startupShutdownMonitor) {
                        AbstractApplicationContext.this.doClose();
                    }
                }
            };
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }

    }
```

## FactoryBean
FactoryBean 提供一个能让使用者定义复杂的 Bean 对象的途径

createBean 执行对象创建、属性填充、依赖加载、前置后置处理、初始化等操作后，就要开始做执行判断整个对象是否是一个 FactoryBean 对象，如果是这样的对象，就需要再继续执行获取 FactoryBean 具体对象中的 getObject 对象了。整个 getBean 过程中都会新增一个单例类型的判断factory.isSingleton()，用于决定是否使用内存存放对象信息。

作用： 如 MyBatis 框架中的DAO代理操作、feignClient的Http调用客户端的生成
```
org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean

    protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
			@Nullable final Object[] args, boolean typeCheckOnly) throws BeansException {

		final String beanName = transformedBeanName(name);
		Object bean;

		// Eagerly check singleton cache for manually registered singletons.
		Object sharedInstance = getSingleton(beanName);
		if (sharedInstance != null && args == null) {
			if (logger.isTraceEnabled()) {
				if (isSingletonCurrentlyInCreation(beanName)) {
					logger.trace("Returning eagerly cached instance of singleton bean '" + beanName +
							"' that is not fully initialized yet - a consequence of a circular reference");
				}
				else {
					logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
				}
			}
			// 调用
			bean = getObjectForBeanInstance(sharedInstance, name, beanName, null);
		}
    }
    
    
    protected Object getObjectForBeanInstance(
			Object beanInstance, String name, String beanName, @Nullable RootBeanDefinition mbd) {
        ....

		Object object = null;
		if (mbd != null) {
			mbd.isFactoryBean = true;
		}
		else {
		    // 从缓存中获取FactoryBean
			object = getCachedObjectForFactoryBean(beanName);
		}
		if (object == null) {
			// Return bean instance from factory.
			FactoryBean<?> factory = (FactoryBean<?>) beanInstance;
			// Caches object obtained from FactoryBean if it is a singleton.
			if (mbd == null && containsBeanDefinition(beanName)) {
				mbd = getMergedLocalBeanDefinition(beanName);
			}
			boolean synthetic = (mbd != null && mbd.isSynthetic());
			 // 调用factoryBean的 getObject 方法
			object = getObjectFromFactoryBean(factory, beanName, !synthetic);
		}
		return object;
	}
```