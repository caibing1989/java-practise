package cache.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: CallableCache的方式最大的特点在于可以在get的时候动态的指定load的数据源
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class GuavaCallableCache {
    public static void main(String[] args) {
        final String key = "0101";
        Cache<String, Optional<List<String>>> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(notification ->
                        System.out.println("cache expired, remove key : " + notification.getKey())).build();

        try {
            Optional<List<String>> optional;
            // 在cache.get使用Cache的时候用传入Callable对象
            System.out.println("load from cache once : " + cache.get(key, () -> Optional.ofNullable(MockDB.getCityListFromDB(key))).orElse(null));
            Thread.sleep(2000);
            System.out.println("load from cache twice : " + cache.get(key, () -> Optional.ofNullable(MockDB.getCityListFromDB(key))).orElse(null));
            Thread.sleep(2000);
            System.out.println("load from cache third : " + cache.get(key, () -> Optional.ofNullable(MockDB.getCityListFromDB(key))).orElse(null));
            Thread.sleep(2000);
            final String nullKey = "0103";
            optional = cache.get(nullKey, () -> Optional.ofNullable(MockDB.getCityListFromDB(nullKey)));
            System.out.println("load not exist key from cache : " + optional.orElse(null));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
