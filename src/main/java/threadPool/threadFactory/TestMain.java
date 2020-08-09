package threadPool.threadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-07
 */

public class TestMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new MyThreadFactory(false));

        for (int i = 0; i < 30; i++) {
            executorService.submit(new TestRunnable(i));
        }

        // 指示当所有任务都结束之后，关闭线程池和其中工作的线程，如果不指示线程池不会关闭
        // 线程池设置为shutdown之后，对外是停止接收新的任务，内部正在允许的任务包含队列中等待的任务不会立即停止
        // 当队列中等待的任务全部执行完毕之后，才算是真正的停止
        executorService.shutdown();

        // 和shutdownNow是有区别的，它不仅会中断外部提交任务，而且还会忽略队列中的任务，并且尝试终止正在执行的任务，最后返回未执行的任务列表

        try {
            // awaitTermination本身不会终止外部任务提交，并在一段等待时间过后，会尝试终止正在执行的任务并或略队列中等待的任务
            // 配合shutdown一起使用
            executorService.awaitTermination(2 * 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class TestRunnable implements Runnable {
        int messageNumber;

        TestRunnable(int messageNumber) {
            this.messageNumber = messageNumber;
        }

        @Override
        public void run() {
            System.out.println("testRunnable : " + messageNumber + " complete");
        }
    }
}
