package cache.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @Description: Gauva Cache 可以通过 maximumSize 参数设置缓存容量，当超出 maximumSize 时，按照 LRU 算法进行缓存回收
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class MaximumSizeGuavaRecover {
    public static void main(String[] args) {
        Cache<String, String> objectCache = CacheBuilder.newBuilder()
                .maximumSize(1)
                .build();

        objectCache.put("key1", "value1");
        String value1 = objectCache.getIfPresent("key1");

        System.out.println("key:key1" + " value:" + value1);//value1

        objectCache.put("key2", "value2");
        String value1AfterExpired = objectCache.getIfPresent("key1");
        String value2 = objectCache.getIfPresent("key2");

        System.out.println("key:key1" + " value:" + value1AfterExpired);//null
        System.out.println("key:key2" + " value:" + value2);//value2
    }
}
