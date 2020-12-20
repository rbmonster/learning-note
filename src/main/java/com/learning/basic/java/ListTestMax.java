package com.learning.basic.java;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * <pre>
 * @Description:
 * threadLocal
 * </pre>
 *
 * @version v1.0
 * @ClassName: ListTestMax
 * @Author: sanwu
 * @Date: 2020/10/31 11:17
 */
public class ListTestMax{
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        new ListTestMax().testThreadLocal();
    }


    public void testThreadLocal() {

//        ThreadLocal<String> listTestMaxThreadLocal = ThreadLocal.withInitial(String::new);
//        listTestMaxThreadLocal.set("123");
//        System.out.println(listTestMaxThreadLocal.get());
//        System.out.println(listTestMaxThreadLocal.get());
//        listTestMaxThreadLocal.remove();
//        listTestMaxThreadLocal.set("12312312");
//        listTestMaxThreadLocal.set("ad");
//        listTestMaxThreadLocal.set("ads");
//        System.out.println(listTestMaxThreadLocal.get());

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("父类数据:threadLocal");
        inheritableThreadLocal.set("父类数据:inheritableThreadLocal");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程获取父类`ThreadLocal`数据：" + threadLocal.get());
                System.out.println("子线程获取父类inheritableThreadLocal数据：" + inheritableThreadLocal.get());
            }
        }).start();
    }

    public void test() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableThread());
        Thread callable = new Thread(futureTask);
        callable.start();
        boolean done = futureTask.isDone();
        boolean cancelled = futureTask.isCancelled();
        // while (!Thread.interrupted())，那么本次任务会一直执行，只有mayInterruptIfRunning=true
//        futureTask.cancel(true);
        // 设置获取结果的等待时间,超时抛出timeOutException
//        String s = futureTask.get(1, TimeUnit.SECONDS);
        // 阻塞等待
        String result = futureTask.get();
        System.out.println(result);
        System.out.println(  Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor(
                2,5,60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),Executors.defaultThreadFactory() );
        // 动态配置线程池核心线程数
        threadPoolExecutor.setCorePoolSize(10);
        threadPoolExecutor.setMaximumPoolSize(10);
//        Future<String> future = executorService.submit(new CallableThread());

    }

    class CallableThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            // do some job
            TimeUnit.SECONDS.sleep(2);
            return "complete the job";
        }
    }

    public void test1(){
        Thread thread1 = new OriThread();
        Thread thread2 = new Thread(new RunnableThread());
    }
    class OriThread extends Thread{
        @Override
        public void run() {
            System.out.println("this is the org thread!");
        }
    }

    class RunnableThread implements Runnable{
        @Override
        public void run() {
            System.out.println("this is runnable thread");
        }
    }
}
