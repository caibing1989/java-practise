package cache.highperformancecache;

import cache.highperformancecache.computable.Computable;
import cache.highperformancecache.computable.MayFailFunction;

import java.util.concurrent.*;

/**
 * @Description: 需要处理耗时计算过程中可能出现的错误问题，注意：需要考虑缓存污染问题
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class HandleExceptionCache<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public HandleExceptionCache(Computable<A, V> computable) {
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

    public static void main(String[] args) throws Exception {
        HandleExceptionCache<String, Integer> handleExceptionCache = new HandleExceptionCache<>(new MayFailFunction());

        new Thread(() -> {
            try {
                Integer result = handleExceptionCache.compute("13");
                System.out.println("第一次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = handleExceptionCache.compute("14");
                System.out.println("第二次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Integer result = handleExceptionCache.compute("13");
                System.out.println("第三次计算结果：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

/**
 * CancellationException和InterruptedException是人为取消的，我们应该立即终止任务
 * 但是如果是计算错误，且我们明确知道多试几次就可以得到正确答案，那我们的逻辑应该重试，直到正确的结果出现
 * 在这里，我们加上while(true)来保证计算出错不会影响我们的逻辑，然后如果是计算错误，就进入下一个循环，
 * 重新计算，直到计算成功；如果是人为取消，那么就抛出异常结束执行
 */
