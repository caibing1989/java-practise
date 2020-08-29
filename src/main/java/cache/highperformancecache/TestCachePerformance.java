package cache.highperformancecache;

import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 用线程池测试缓存性能
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class TestCachePerformance {
    public static final ValidityCache<String, Integer> cache = new ValidityCache<>(new ExpensiveFunction());

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                Integer result = null;
                try {
                    result = cache.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }
}
