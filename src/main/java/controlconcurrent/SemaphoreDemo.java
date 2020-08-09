package controlconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 演示信号量
 * @Author: mtdp
 * @Date: 2020-08-03
 */

public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(1);
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + "获取了1个信号量");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(1);
                    System.out.println(Thread.currentThread().getName() + "释放了1个信号量");
                }
            }
        };

        for (int i = 0; i < 100; i++) {
            executorService.submit(runnable);
        }

        executorService.shutdown();
    }
}
