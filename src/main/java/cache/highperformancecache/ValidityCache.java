package cache.highperformancecache;

import cache.highperformancecache.computable.Computable;
import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.concurrent.*;

/**
 * @Description: 处于安全性考虑，缓存需要设置有效期，到期自动失效
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class ValidityCache<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public ValidityCache(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
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

            try {
                return futureResult.get();
            } catch (CancellationException e) {
                System.out.println("被取消了");
                e.printStackTrace();
                cache.remove(arg);
                throw e;
            } catch (InterruptedException e) {
                e.printStackTrace();
                cache.remove(arg);
                throw e;
            } catch (ExecutionException e) {
                System.out.println("计算错误，需要重试");
                // 清除受污染的缓存
                cache.remove(arg);
            }
        }
    }

    private final static ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    public V compute(A arg, long expire) throws InterruptedException {
        if (expire > 0) {
            executor.schedule(() -> {
                expireKey(arg);
            }, expire, TimeUnit.SECONDS);
        }

        return compute(arg);
    }

    // 生成随机过期时间
    public V computeRandomExpire(A arg) throws InterruptedException {
        long expireTime = (long) (Math.random() * 100);
        return compute(arg, expireTime);
    }

    private synchronized void expireKey(A key) {
        Future<V> future = cache.get(key);
        if (future != null) {
            if (!future.isDone()) {
                System.out.println("过期时间到，future任务被取消");
                future.cancel(true);
            }

            System.out.println("过期时间到，缓存被清除");
            cache.remove(key);
        }

    }

    public static void main(String[] args) throws Exception {
        ValidityCache<String, Integer> validityCache = new ValidityCache<>(new ExpensiveFunction());

        new Thread(() -> {
            try {
                Integer result = validityCache.compute("13", 10L);
                System.out.println("第一次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = validityCache.compute("14",10L);
                System.out.println("第二次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = validityCache.compute("13",10L);
                System.out.println("第三次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
