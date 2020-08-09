package threadPool.shutdownpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class ShutDown implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ShutDown());
        }

        Thread.sleep(1500);

        System.out.println("-------------isShutdown: " + executorService.isShutdown());
        executorService.shutdown();
        System.out.println("-------------isShutdown: " + executorService.isShutdown());
        System.out.println("-------------isTerminated: " + executorService.isTerminated());
        System.out.println("-------------awaitTermination等待3秒是不是完全执行完毕了: " + executorService.awaitTermination(3, TimeUnit.SECONDS));

        List<Runnable> runnableList = executorService.shutdownNow();
        System.out.println("立即停止线程池now，在队列中的剩余任务数是：" + runnableList.size());

        Thread.sleep(10000);
        System.out.println("-------------isTerminated: " + executorService.isTerminated());
        System.out.println("-------------awaitTermination等待10000秒是不是完全执行完毕了: " + executorService.awaitTermination(10000, TimeUnit.SECONDS));
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
            e.printStackTrace();
        }
    }
}
