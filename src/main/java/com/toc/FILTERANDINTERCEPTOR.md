<a name="index">**Index**</a>

<a href="#0">MVC 拦截器与过滤器</a>  
&emsp;<a href="#1">1. 过滤器 servlet</a>  
&emsp;<a href="#2">2. 拦截器</a>  
&emsp;&emsp;<a href="#3">2.1. HandlerInterceptor</a>  
&emsp;&emsp;<a href="#4">2.2. MethodInterceptor</a>  
# <a name="0">MVC 拦截器与过滤器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/four/filter2intercept.jpg)
## <a name="1">过滤器 servlet</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
filter（过滤器）作用于在interceptor(拦截器)之前，不像interceptor一样依赖于springmvc框架，只需要依赖于serverlet。
- 比较原始的一种方法是定义一个类实现javax.servlet.Filter接口
- 另一种方法的自定义Filter，继承springframework.web.filter.OncePerRequestFilter，可以对同一个请求，只经过一次过滤

SpringBootApplication 上使用@ServletComponentScan 注解后
- Servlet可以直接通过@WebServlet注解自动注册
  - 常规servlet，可以声明URL，doGet和doPost方法
- Filter可以直接通过@WebFilter注解自动注册
  - @Order里边的数字越小代表越先被该Filter过滤
  - Filter和FilterChain都是用责任链模式实现
- Listener可以直接通过@WebListener 注解自动注册
  - 监听Servlet生命周期


## <a name="2">拦截器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="3">HandlerInterceptor</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
拦截器可用于横切关注点，并避免重复的处理程序代码，如日志记录、更改Spring模型中全局使用的参数等。

通过继承HandlerInterceptor接口，重写以下三个方法：
- preHandle()： 在实际的handler被执行前但是modelView 还未生成前调用。
  - 返回boolean类型数据，可以告诉spring 是否执行该方法。
- postHandle()：在handler处理请求完成之后，生成视图之前执行。 
- afterCompletion()：在请求完全完成且视图也生成了之后调用。
    
spring mvc的拦截器是只拦截controller而不拦截jsp,html 页面文件的。这就用到过滤器filter了，filter是在servlet前执行的，你也可以理解成过滤器中包含拦截器，一个请求过来 ，先进行过滤器处理，看程序是否受理该请求 。 过滤器放过后 ， 程序中的拦截器进行处理 。
1. 拦截器不依赖servlet容器，过滤器依赖；
2. 拦截器是基于java反射机制来实现的，过滤器基于回调

- 过滤器：关注web请求；
- 拦截器：关注方法调用；
```
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    boolean multipartRequestParsed = false;
    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

    try {
        try {
         
                HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                String method = request.getMethod();
                // preHandler前置处理，如果返回false 直接不执行。
                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                    return;
                }
                // 具体请求调用
                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                if (asyncManager.isConcurrentHandlingStarted()) {
                    return;
                }

                this.applyDefaultViewName(processedRequest, mv);
                // postHandle 后置调用
                mappedHandler.applyPostHandle(processedRequest, response, mv);
            } catch (Exception var20) {
                dispatchException = new NestedServletException("Handler dispatch failed", var21);
            }

            this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
        } catch (Exception var22) {
            ......
        }

    } finally {
        if (asyncManager.isConcurrentHandlingStarted()) {
            if (mappedHandler != null) {
                //  请求全部完成后调用
                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
            }
        } else if (multipartRequestParsed) {
            this.cleanupMultipart(processedRequest);
        }

    }
}

```


### <a name="4">MethodInterceptor</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MethodInterceptor是AOP项目中的拦截器，它拦截的目标是方法，即使不是controller中的方法。实现MethodInterceptor拦截器大致也分为两种，一种是实现MethodInterceptor接口，另一种利用AspectJ的注解或配置。

MethodInterceptor是AOP项目中的拦截器，它拦截的目标是方法，即使不是controller中的方法。实现MethodInterceptor拦截器大致也分为两种，一种是实现MethodInterceptor接口，另一种利用AspectJ的注解或配置。

1. 实现MethodInterceptor接口：实际见项目

2. 利用AspectJ的注解或配置
　a、基于AspectJ注解