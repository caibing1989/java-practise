package threadPool.rejectExecution;

import java.util.concurrent.TimeUnit;

import static threadPool.rejectExecution.ExecutorTask.callerRunsPolicyExecutor;

/**
 * @Description: 谁提交了这个任务，就谁帮我去跑
 * @Author: mtdp
 * @Date: 2020-07-08
 */

public class TestCallerRunsMain {
    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println("第" + i + "个任务");
            try {
                callerRunsPolicyExecutor.submit(new MyWorkRunnable(i));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            System.out.println("队列容量剩余" + callerRunsPolicyExecutor.getQueue().remainingCapacity());

        }

        callerRunsPolicyExecutor.shutdown();

        try {
            // awaitTermination本身不会终止外部任务提交，并在一段等待时间过后，会尝试终止正在执行的任务并或略队列中等待的任务
            // 配合shutdown一起使用
            callerRunsPolicyExecutor.awaitTermination(2 * 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程池已关闭");
    }
}

/**
 * 第一个任务 新建线程执行
 * 第二个任务 进入队列排队
 * 第三个任务 进入队列排队
 * 第四个任务 新建线程执行
 * 第五个任务 main线程执行
 * 后续任务阻塞，直到main执行完成
 *
 */
