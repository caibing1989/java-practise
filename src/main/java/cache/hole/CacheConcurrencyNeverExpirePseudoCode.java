package cache.hole;

/**
 * @Description: 伪代码，缓存不过期，从功能上看，如果不过期，那不就成静态的了吗？
 * 所以我们把过期时间存在key对应的value里，如果发现要过期了，通过一个后台的异步线程进行缓存的构建，也就是“逻辑”过期。
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class CacheConcurrencyNeverExpirePseudoCode {

//    public String get(String key) {
//        MutexDTO mutexDTO = storeClient.get(key);
//        String value = mutexDTO.getValue();
//        if (mutexDTO.getTimeout() <= System.currentTimeMillis()) {
//            ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();// 异步更新后台异常执行
//            singleThreadExecutor.execute(new Runnable() {
//                public void run() {
//                    StoreKey mutexKey = new MutexStoreKey(key);
//                    if (storeClient.setnx(mutexKey, "1")) {
//                        storeClient.expire(mutexKey, 3 * 60);
//                        String dbValue = db.get(key);
//                        storeClient.set(key, dbValue);
//                        storeClient.delete(mutexKey);
//                    }
//                }
//            });
//        }
//        return value;
//    }

}
