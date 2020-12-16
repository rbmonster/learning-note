package com.design.localcache;

/**
 * <pre>
 * @Description:
 * 本地缓存基本接口
 * </pre>
 *
 * @version v1.0
 * @ClassName: ILocalCache
 * @Author: sanwu
 * @Date: 2020/12/15 12:39
 */
public interface ILocalCache<K, V> {

    void put(K key, V value);

    void delete(K key);

    V get(K key);

    int size();
}
