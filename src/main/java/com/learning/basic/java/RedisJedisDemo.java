package com.learning.basic.java;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: RedisJedisDemo
 * @Author: sanwu
 * @Date: 2020/11/19 22:56
 */
public class RedisJedisDemo {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = buildPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");
        Jedis jedis = jedisPool.getResource();

        testLock(jedis);
        System.out.println(tryLock_with_set(jedis, "lock",Thread.currentThread().getName(), 3));


    }

    /**
     * 分布式锁正确方法  将set指令 与过期时间 结合起来使用
     * 缺陷：A客户端在Redis的master节点上拿到了锁，但是这个加锁的key还没有同步到slave节点。
     *      master故障，发生故障转移，一个slave节点升级为master节点，
     *      B客户端也可以获取同个key的锁，但客户端A也已经拿到锁了，这就导致多个客户端都拿到锁。
     * @param jedis
     * @param key
     * @param UniqueId
     * @param seconds
     * @return
     */
    public static boolean tryLock_with_set(Jedis jedis, String key, String UniqueId, int seconds) {
        return "OK".equals(jedis.set(key, UniqueId, "NX", "EX", seconds));
    }


    /**
     * 释放锁时需要验证value值，也就是说我们在获取锁的时候需要设置一个value，不能直接用del key这种粗暴的方式，因为直接del key任何客户端都可以进行解锁了，所以解锁时，我们需要判断锁是否是自己的，基于value值来判断
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public boolean releaseLock_with_lua(Jedis jedis, String key,String value) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end";
        return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
    }



    /**
     * 实现分布式锁 错误做法方法1
     * 利用setnx+expire命令 (错误的做法)
     *
     * @return
     */
    public static boolean testLock(Jedis jedis) {
        String lockKey = "lockKey";
        Long result = jedis.setnx(lockKey, "Value");
        int timeout = 3;
        // result = 1时，设置成功，否则设置失败
        if (result == 1L) {
            return jedis.expire(lockKey, timeout) == 1L;
        } else {
            return false;
        }
    }

    /**
     * 分布式锁 错误做法方案2
     * 使用Lua脚本（包含setnx和expire两条指令）
     * @param jedis
     * @param key
     * @param UniqueId
     * @param seconds
     * @return
     */
    public boolean tryLock_with_lua(Jedis jedis,String key, String UniqueId, int seconds) {
        String lua_scripts = "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then" +
                "redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end";
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add(key);
        values.add(UniqueId);
        values.add(String.valueOf(seconds));
        Object result = jedis.eval(lua_scripts, keys, values);
        //判断是否成功
        return result.equals(1L);
    }


    private static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
