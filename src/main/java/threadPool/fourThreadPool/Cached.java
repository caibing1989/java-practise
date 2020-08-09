package threadPool.fourThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue())
 * 多余的线程，在默认60秒后进行回收，最大线程数没有限制，会导致oom
 * @Author: mtdp
 * @Date: 2020-07-10
 */

public class Cached {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
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
 * 如果线程池大小超过了任务所需，就会回收空闲的线程池size
 * 当任务数增大时，线程池会扩大size来满足所需，没有最大限制，线程池的大小受限于操作系统
 */
