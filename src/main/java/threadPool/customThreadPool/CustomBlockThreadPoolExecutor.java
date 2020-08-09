package threadPool.customThreadPool;

import java.util.concurrent.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-10
 */

public class CustomBlockThreadPoolExecutor {

    private ThreadPoolExecutor pool = null;

    private void init() {
        pool = new ThreadPoolExecutor(2, 4, 0,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                new CustomBlockThreadPoolExecutor.CustomThreadFactory(),
                new CustomBlockThreadPoolExecutor.CustomRejectHandler());
    }

    private void destroy() {
        if (pool != null) {
            pool.shutdown();
            System.out.println("shoutDone");
        }
    }

    private ThreadPoolExecutor getExecutor() {
        return this.pool;
    }

    private static class CustomRejectHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            System.out.println("CustomRejectPolicy handling start...");
            try {
                // 阻塞队列执行的关键点
                threadPoolExecutor.getQueue().put(runnable);
                System.out.println("CustomRejectPolicy handling end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable);
        }
    }

    public static void main(String[] args) {
        CustomBlockThreadPoolExecutor customBlockThreadPoolExecutor = new CustomBlockThreadPoolExecutor();
        customBlockThreadPoolExecutor.init();
        ThreadPoolExecutor threadPoolExecutor = customBlockThreadPoolExecutor.getExecutor();


        for (int i = 0; i < 20; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try {
                    System.out.println(currentThread().getName() + " " + finalI + " start to do");
                    sleep(1000);
                    System.out.println(currentThread().getName() + " " + finalI + " done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("队列容量剩余" + threadPoolExecutor.getQueue().remainingCapacity());
        }

        customBlockThreadPoolExecutor.destroy();
    }
}

/**
 * 线程池阻塞执行，将会把所有的任务都执行完毕
 *
 */
