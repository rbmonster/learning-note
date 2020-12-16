package com.design.localcache;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
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
public class LocalCache<K,V> implements ILocalCache<K,V> {

    private volatile Map <K, WeakReference<V>> cache;

    public LocalCache (int size){
        cache = new ConcurrentHashMap<>(size);
    }


    @Override
    public void put(K key, V value) {
        cache.put(key, new WeakReference<>(value));
    }

    @Override
    public void delete(K key) {
        cache.remove(key);
    }

    @Override
    public V get(K key) {
        WeakReference<V> value = cache.get(key);
        if (Objects.isNull(value.get())){
            cache.remove(key, value);
        }
        return value.get();
    }

    @Override
    public int size() {
        return cache.size();
    }

    public static void main(String[] args) {
        Object obj = new Object();
        WeakReference<Object> key = new WeakReference<>(obj);
        System.out.println(key.get());
        obj = null;
        System.gc();
        System.out.println(key.get());
    }

}
