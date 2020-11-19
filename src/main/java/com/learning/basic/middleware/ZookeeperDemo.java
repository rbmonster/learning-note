package com.learning.basic.middleware;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * @Description:
 * zk分布式锁demo
 * </pre>
 *
 * @version v1.0
 * @ClassName: ZookeeperDemo
 * @Author: sanwu
 * @Date: 2020/11/19 18:19
 */
public class ZookeeperDemo {

    private final static String url = "127.0.0.1:2181";
    private final static int timeout = 1000;
    private final static int retry = 3;

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(timeout,retry);
        CuratorFramework client = CuratorFrameworkFactory.newClient(url, retryPolicy);
        client.start();

        String lockKey = "/testZooKeeperKey";
        InterProcessMutex lock = new InterProcessMutex(client, lockKey);
        try {
            if(lock.acquire(3, TimeUnit.SECONDS)) {
                System.out.println("成功获得锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("释放锁");
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
