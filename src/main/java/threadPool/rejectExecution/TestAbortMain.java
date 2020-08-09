package threadPool.rejectExecution;

import java.util.concurrent.TimeUnit;

import static threadPool.rejectExecution.ExecutorTask.abortThreadPoolExecutor;

/**
 * @Description: abort policy 异常
 * @Author: mtdp
 * @Date: 2020-07-07
 */

public class TestAbortMain {
    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println("第" + i + "个任务");
            try {
                abortThreadPoolExecutor.submit(new MyWorkRunnable(i));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            System.out.println("队列容量剩余" + abortThreadPoolExecutor.getQueue().remainingCapacity());

        }

        abortThreadPoolExecutor.shutdown();

        try {
            // awaitTermination本身不会终止外部任务提交，并在一段等待时间过后，会尝试终止正在执行的任务并或略队列中等待的任务
            // 配合shutdown一起使用
            abortThreadPoolExecutor.awaitTermination(2 * 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程池已关闭");
    }
}

/**
 * CorePoolSize=1 MaximumPoolSize=2 BlockingQueue=2
 * 有19个任务，第一个任务来了，新建线程，CorePoolSize满了，BlockingQueue未使用，剩余空间2
 * 第二个任务来了，进入队列，BlockingQueue剩余空间-1，为1
 * 第三个任务来了，进入队列，BlockingQueue剩余空间-1，为0，满了
 * 第四个任务来了，MaximumPoolSize未满，新建线程，然后MaximumPoolSize满了
 * 第五个任务来了，抛出执行时异常，拒绝执行
 * 后面的任务都拒绝执行
 *
 */
