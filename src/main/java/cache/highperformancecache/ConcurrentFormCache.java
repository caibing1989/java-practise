package cache.highperformancecache;

import cache.highperformancecache.computable.Computable;
import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class ConcurrentFormCache<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, V> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public ConcurrentFormCache(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ConcurrentFormCache<String, Integer> concurrentFormCache = new ConcurrentFormCache<>(new ExpensiveFunction());

        new Thread(() -> {
            try {
                Integer result = concurrentFormCache.compute("13");
                System.out.println("第一次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = concurrentFormCache.compute("14");
                System.out.println("第二次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = concurrentFormCache.compute("13");
                System.out.println("第三次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

/**
 * 缺点：在计算完成之前，另一个要求计算相同值的请求进来，会导致计算两遍，这和缓存想避免多次计算的初衷
 * 恰恰相反，是不可接受的
 *
 *
 */


