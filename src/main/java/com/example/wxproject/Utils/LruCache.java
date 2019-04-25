package com.example.wxproject.Utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> extends LinkedHashMap {

    private final int MAX_CACHE_SIZE;

    public LruCache(int cacheSize){
        //初始容量;负载因子;访问顺序
        super((int)Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size()> MAX_CACHE_SIZE;
    }
}
