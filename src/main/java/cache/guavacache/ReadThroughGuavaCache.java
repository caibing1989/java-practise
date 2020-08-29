package cache.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @Description: Read-Through 也是在查询操作中更新缓存，和 Cache-Aside 相比，唯一的区别就是，当缓存失效的时候（过期或LRU换出），
 * Cache-Aside 是由业务代码负责把数据加载入缓存，而 Read-Through 则用缓存服务自己来加载，从而对业务代码是透明的
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class ReadThroughGuavaCache {
    static CacheLoader<String, String> loader = new CacheLoader<String, String>() {
        public String load(String key) {
            System.out.println("getFromDatabase,key:" + key);
            return getFromDatabase(key);
        }
    };

    // 模拟从数据库中获取数据
    private static String getFromDatabase(String key) {
        return key;
    }

    public static void main(String[] args) {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build(loader);

        String key = "key1";

        try {
            String result = loadingCache.get(key); // 第一次查询，缓存中无数据，需要从数据库加载
            System.out.println("result : " + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            String result2 = loadingCache.get(key); // 第二次查询，缓存有数据，直接从缓存获取
            System.out.println("result2 : " + result2);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
