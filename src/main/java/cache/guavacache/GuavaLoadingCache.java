package cache.guavacache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: LoadingCache是附带CacheLoader构建而成的缓存实现。创建自己的CacheLoader通常只需要简单地实现load()方法
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class GuavaLoadingCache {
    public static void main(String[] args) {
        LoadingCache<String, Optional<List<String>>> loadingCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // 用来监听当缓存里面的key被移除时候触发的事件
                .removalListener(notification -> System.out.println("cache expired, remove key : " + notification.getKey()))
                // 传入一个CacheLoader类，指定缓存中没有的时候调用 CacheLoader 类的load方法（所以一般需要重写该方法）
                .build(new CacheLoader<String, Optional<List<String>>>() {
                    @Override
                    public Optional<List<String>> load(String key) throws Exception {
                        // 当CacheLoader尝试获取数据库中不存在的数据会抛出异常，所以我们这里使用Optional可空对象处理一下
                        return Optional.ofNullable(MockDB.getCityListFromDB(key));
                    }
                });

        try {
            System.out.println("load from cache once : " + loadingCache.get("0101").orElse(Lists.newArrayList()));
            // 缓存我们设置3秒过期，所以两次Sleep以后就会重新获取数据库
            Thread.sleep(2000);
            System.out.println("load from cache two : " + loadingCache.get("0101").orElse(Lists.newArrayList()));
            Thread.sleep(2000);
            System.out.println("load from cache three : " + loadingCache.get("0101").orElse(Lists.newArrayList()));
            Thread.sleep(2000);
            System.out.println("load not exist key from cache : " + loadingCache.get("0103").orElse(Lists.newArrayList()));

        } catch (ExecutionException | InterruptedException e) {
            //记录日志
        }
    }
}
