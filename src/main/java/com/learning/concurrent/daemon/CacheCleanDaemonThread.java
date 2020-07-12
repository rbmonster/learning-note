package com.learning.concurrent.daemon;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 *  daemon 缓存清理线程
 * </pre>
 *
 * @version v1.0
 * @ClassName: CacheCleanDaemonThread
 * @Author: sanwu
 * @Date: 2020/7/10 23:48
 */
public class CacheCleanDaemonThread implements Runnable {

    private Map<String,String> cacheMap;

    public CacheCleanDaemonThread(Map<String, String> cacheMap) {
        this.cacheMap = cacheMap;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(10l);
                synchronized (cacheMap){
                    String cache = cacheMap.get(CacheLoadThread.DICT_KEY);
                    if (!StringUtils.isEmpty(cache)) {
                        cacheMap.remove(CacheLoadThread.DICT_KEY);
                        System.out.println("daemon thread clean cache~~");
                    }
                }
            } catch (Exception e) {
                new InterruptedException("interrupt~");
            }
        }
    }
}
