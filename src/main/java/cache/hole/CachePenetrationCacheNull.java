package cache.hole;

import com.google.common.cache.Cache;

/**
 * @Description: 伪代码，当Client请求MISS后，仍然将空对象保留到Cache中（可能是保留一段时间，具体问题具体分析），
 * 下次新的Request（同一个key）将会从Cache中获取到数据，保护了后端的DB
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class CachePenetrationCacheNull {

//    private Cache cache = new Cache();
//    private Storage storage = new Storage();
//    /**
//     * 模拟正常模式
//     * @param key
//     * @return
//     */
//    public String getNormal(String key) {
//        // 从缓存中获取数据
//        String cacheValue = cache.get(key);
//        // 缓存为空
//        if (StringUtils.isBlank(cacheValue)) {
//            // 从存储中获取
//            String storageValue = storage.get(key);
//            // 如果存储数据不为空,将存储的值设置到缓存
//            if (StringUtils.isNotBlank(storageValue)) {
//                cache.set(key, storageValue);
//            }
//            return storageValue;
//        } else {
//            // 缓存非空
//            return cacheValue;
//        }
//    }
//    /**
//     * 模拟防穿透模式
//     * @param key
//     * @return
//     */
//    public String getPassThrough(String key) {
//        // 从缓存中获取数据
//        String cacheValue = cache.get(key);
//        // 缓存为空
//        if (StringUtils.isBlank(cacheValue)) {
//            // 从存储中获取
//            String storageValue = storage.get(key);
//            cache.set(key, storageValue);
//            // 如果存储数据为空，需要设置一个过期时间(300秒)
//            if (StringUtils.isBlank(storageValue)) {
//                cache.expire(key, 60 * 5);
//            }
//            return storageValue;
//        } else {
//            // 缓存非空
//            return cacheValue;
//        }
//    }

}
