package threadPool.fourThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: return new ThreadPoolExecutor(var0, var0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue())
 * @Author: mtdp
 * @Date: 2020-07-10
 */

public class FixedPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new TestRun(i));
        }
        executorService.shutdown();
    }

    private static class TestRun implements Runnable {
        Integer cycle;

        TestRun(Integer cycle) {
            this.cycle = cycle;
        }

        @Override
        public void run() {
            System.out.println("testRun: " + cycle + " " + Thread.currentThread().getName());
        }
    }
}

/**
 * 随着任务数增加，逐渐创建线程，直到参数规定的最大线程数，后续的任务加入无界队列中LinkedBlockingQueue
 * 入参=1时，就是单线程的线程池newSingleThreadExecutor
 */
