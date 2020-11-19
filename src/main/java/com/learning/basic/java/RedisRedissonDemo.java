package com.learning.basic.java;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * Redission redis 分布式锁应用
 * </pre>
 *
 * @version v1.0
 * @ClassName: RedisDemo
 * @Author: sanwu
 * @Date: 2020/11/19 18:55
 */
public class RedisRedissonDemo {

    public static void main(String[] args) {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:6379");

        // 2. Create Redisson instance

        // Sync and Async API
        RedissonClient redisson = Redisson.create(config);

        // Reactive API
//        RedissonReactiveClient redissonReactive = Redisson.createReactive(config);

        // RxJava2 API
//        RedissonRxClient redissonRx = Redisson.createRx(config);

        // 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap
        RMap<String, String> map = redisson.getMap("myMap");

//        RMapReactive<String, String> mapReactive = redissonReactive.getMap("myMap");

//        RMapRx<String, String> mapRx = redissonRx.getMap("myMap");

        // 4. Get Redis based implementation of java.util.concurrent.locks.Lock
        RLock lock = redisson.getLock("myLock");

        try {
            if( lock.tryLock(100, TimeUnit.SECONDS)){
                System.out.println("get redis lock");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

            lock.unlock();
        }

//        RLockReactive lockReactive = redissonReactive.getLock("myLock");
//
//        RLockRx lockRx = redissonRx.getLock("myLock");



// or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
    }
}
