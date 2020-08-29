package cache.highperformancecache;

import cache.highperformancecache.computable.Computable;
import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description: 利用future避免重复计算，即使两次计算间隔十分紧密也适用
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class AvoidReplicationComputeCache<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public AvoidReplicationComputeCache(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        Future<V> futureResult = cache.get(arg);

        if (futureResult == null) {
            Callable<V> callable = () -> computable.compute(arg);
            FutureTask<V> futureTask = new FutureTask<V>(callable);
            // 由ConcurrentHashMap保证原子性，调用有先有后
            futureResult = cache.putIfAbsent(arg, futureTask);
            // 避免几乎同时到达场景时的重复计算问题
            if (futureResult == null) {
                futureResult = futureTask;
                System.out.println("从futureTask中调用了计算函数");
                futureTask.run();
            }
        }

        return futureResult.get();
    }

    public static void main(String[] args) throws Exception {
        AvoidReplicationComputeCache<String, Integer> avoidReplicationComputeCache = new AvoidReplicationComputeCache<>(new ExpensiveFunction());

        new Thread(() -> {
            try {
                Integer result = avoidReplicationComputeCache.compute("13");
                System.out.println("第一次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = avoidReplicationComputeCache.compute("14");
                System.out.println("第二次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = avoidReplicationComputeCache.compute("13");
                System.out.println("第三次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
