package cache.highperformancecache;

import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 使用CountDownLatch实现压测
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class StressTestCachePerformance {
    public static final ValidityCache<String, Integer> cache = new ValidityCache<>(new ExpensiveFunction());

    public static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + "开始放行");

                    result = cache.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }

        Thread.sleep(1000);
        countDownLatch.countDown();

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }
}
