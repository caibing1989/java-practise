package cache.highperformancecache;

import cache.highperformancecache.computable.Computable;
import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.HashMap;

/**
 * @Description: 缩小了synchronized的粒度，提高性能，但是依然并发不安全
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class ReduceLockGranularityCache<A, V> implements Computable<A, V> {

    private final HashMap<A, V> cache = new HashMap<>();

    private final Computable<A, V> computable;

    public ReduceLockGranularityCache(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            synchronized (this) {
                cache.put(arg, result);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ReduceLockGranularityCache<String, Integer> reduceLockGranularityCache = new ReduceLockGranularityCache<>(new ExpensiveFunction());

        new Thread(() -> {
            try {
                Integer result = reduceLockGranularityCache.compute("13");
                System.out.println("第一次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = reduceLockGranularityCache.compute("14");
                System.out.println("第二次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = reduceLockGranularityCache.compute("13");
                System.out.println("第三次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
