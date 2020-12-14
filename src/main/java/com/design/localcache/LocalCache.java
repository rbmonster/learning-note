package com.design.localcache;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * @Description:
 * 本地缓存
 * </pre>
 *
 * @version v1.0
 * @ClassName: LocalCache
 * @Author: sanwu
 * @Date: 2020/12/14 23:26
 */
public class LocalCache {

    public static void main(String[] args) {

        Map<WeakReference<String>, WeakReference<String>> cache = new ConcurrentHashMap<>();
        WeakReference<String> key = new WeakReference<>("asdf");
        cache.put(key, new WeakReference<>("asdf"));
        WeakReference<String> asdf = key;
        System.out.println(asdf);
        System.out.println(cache.size());
        cache.put(key, new WeakReference<>("12"));
        System.gc();
        System.out.println(cache.size());
    }
}
