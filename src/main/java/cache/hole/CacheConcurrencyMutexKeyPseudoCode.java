package cache.hole;

/**
 * @Description: 伪代码，使用互斥锁(mutex key) 解决缓存并发问题，
 * 就是只让一个线程构建缓存，其他线程等待构建缓存的线程执行完，重新从缓存获取数据就可以
 * @Author: mtdp
 * @Date: 2020-08-16
 */

public class CacheConcurrencyMutexKeyPseudoCode {

//    public String get(String key) {
//        String value = storeClient.get(key);
//        StoreKey key_mutex = new MutexStoreKey(key);
//        if (value == null) {//代表缓存值过期
//            //设置2min的超时，防止删除缓存操作失败的时候，下次缓存过期一直不能获取DB数据
//            if (storeClient.setnx(key_mutex, 1, 2 * 60)) {  //代表设置成功
//                value = db.get(key);
//                storeClient.set(key, value, 3 * 3600);
//                storeClient.delete(key_mutex);
//            } else {
//                sleep(1000);  //这个时候代表同时候的其他线程已经获取DB数据并回设到缓存了，这时候重试获取缓存值即可
//                return get(key);  //重试
//            }
//        }
//        return value;
//    }

}
