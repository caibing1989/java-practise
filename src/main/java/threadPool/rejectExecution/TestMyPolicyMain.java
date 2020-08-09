package threadPool.rejectExecution;

import java.util.concurrent.TimeUnit;

import static threadPool.rejectExecution.ExecutorTask.myPolicyExecutor;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-08
 */

public class TestMyPolicyMain {

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++) {
            System.out.println("第" + i + "个任务");
            try {
                myPolicyExecutor.submit(new MyWorkRunnable(i));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            System.out.println("队列容量剩余" + myPolicyExecutor.getQueue().remainingCapacity());
        }

        myPolicyExecutor.shutdown();

        try {
            // awaitTermination本身不会终止外部任务提交，并在一段等待时间过后，会尝试终止正在执行的任务并或略队列中等待的任务
            // 配合shutdown一起使用
            myPolicyExecutor.awaitTermination(2 * 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程池已关闭");
    }
}

/**
 * 采用自定义拒绝策略，每次都新建线程去执行
 *
 */
