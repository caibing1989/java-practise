package cache.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Description: Gauva Cache 可以通过 expireAfterAccess 参数设置空闲时间
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class ExpireAfterAccessGuavaCache {
    public static void main(String[] args) {
        Cache<String, String> objectCache = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build();

        objectCache.put("key1", "value1");

        String value1 = objectCache.getIfPresent("key1");
        System.out.println("key:key1" + " value:" + value1);//value1

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String value1AfterExpired = objectCache.getIfPresent("key1");
        System.out.println("key:key1" + " value:" + value1AfterExpired);//null
    }
}
