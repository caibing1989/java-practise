package controlconcurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 工厂中，质检，只有5个人都通过，才通过
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        System.out.println("No:" + no + " 完成了检查");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            };

            executorService.submit(runnable);
        }

        System.out.println("等待全部检查完毕~");
//        executorService.shutdown();
//
//        while (!executorService.isTerminated()) {
//
//        }
        countDownLatch.await();
        System.out.println("已经全部检查完毕~");

    }
}
