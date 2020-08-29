package cache.hole;

/**
 * @Description: 伪代码，“提前”使用互斥锁(mutex key)  即在value内部设置1个超时值(timeout1),
 * timeout1比实际的redis timeout(timeout2)小。当从cache读取到timeout1发现它已经过期时候，
 * 马上延长timeout1并重新设置到cache。然后再从数据库加载数据并设置到cache中
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class CacheConcurrencyMutexKeyAdvancePseudoCode {

//    public String get(String key) {
//        MutexDTO value = storeClient.get(key);
//        StoreKey key_mutex = new MutexStoreKey(key);
//        if (value == null) {
//            if (storeClient.setnx(key_mutex, 3 * 60 * 1000)) {
//                value = db.get(key);
//                storeClient.set(key, value);
//                storeClient.delete(key_mutex);
//            } else {
//                sleep(50);
//                get(key);
//            }
//        } else {
//            if (value.getTimeout() <= System.currentTimeMillis()) {
//                if (storeClient.setnx(key_mutex, 3 * 60 * 1000)) {
//                    value.setTimeout(value.getTimeout() + 3 * 60 * 1000);
//                    storeClient.set(key, value, 3 * 3600 * 2);
//
//                    value = db.get(key);//获取最近DB更新数据
//                    value.setTimeout(value.getTimeout() + 3 * 60 * 1000);
//                    storeClient.set(key, value, 3 * 3600 * 2);
//                    storeClient.delete(key_mutex);
//                } else {
//                    sleep(50);
//                    get(key);
//                }
//            }
//        }
//        return value.getValue();
//    }

}
