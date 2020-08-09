package threadPool.rejectExecution;

import java.util.concurrent.TimeUnit;

import static threadPool.rejectExecution.ExecutorTask.discardOldestPolicyExecutor;

/**
 * @Description: 丢弃最老的
 * @Author: mtdp
 * @Date: 2020-07-08
 */

public class TestDiscardOldestMain {
    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println("第" + i + "个任务");
            try {
                discardOldestPolicyExecutor.submit(new MyWorkRunnable(i));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            System.out.println("队列容量剩余" + discardOldestPolicyExecutor.getQueue().remainingCapacity());
        }

        discardOldestPolicyExecutor.shutdown();

        try {
            // awaitTermination本身不会终止外部任务提交，并在一段等待时间过后，会尝试终止正在执行的任务并或略队列中等待的任务
            // 配合shutdown一起使用
            discardOldestPolicyExecutor.awaitTermination(2 * 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程池已关闭");
    }
}

/**
 * 任务1，新建线程执行
 * 任务2，进入队列排队
 * 任务3，进入队列排队
 * 任务4，新建线程执行
 * 任务5，抛弃队列中的任务2，任务5进入
 * 往下以此类推，最后18、19任务会在队列中
 *
 */
