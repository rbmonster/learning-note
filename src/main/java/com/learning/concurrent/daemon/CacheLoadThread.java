package com.learning.concurrent.daemon;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.*;

/**
 * <pre>
 * @Description:
 *  缓存加载线程
 * </pre>
 *
 * @version v1.0
 * @ClassName: LoadCacheThread
 * @Author: sanwu
 * @Date: 2020/7/10 23:49
 */
public class CacheLoadThread implements Runnable {
    private  Map<String, String> cacheMap;
    public static final String DICT_KEY = "dict";

    public CacheLoadThread(Map<String, String> cacheMap) {
        this.cacheMap = cacheMap;
    }

    @Override
    public void run() {
        while (true){
            synchronized (cacheMap){
                if (StringUtils.isEmpty(cacheMap.get(DICT_KEY))) {
                    System.out.println(" add cache test~~~");
                    cacheMap.put(DICT_KEY, "test");
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Map<String,String> cacheMap = new ConcurrentHashMap<>();
        CacheLoadThread loadCacheThread = new CacheLoadThread(cacheMap);
        Thread cacheCleanDaemonThread = new Thread(new CacheCleanDaemonThread(cacheMap));
        cacheCleanDaemonThread.setDaemon(true);
        executor.execute(loadCacheThread);
        executor.execute(cacheCleanDaemonThread);
        System.out.println("Execute complete~~");
        TimeUnit.SECONDS.sleep(5);
        executor.shutdown();
    }
}
