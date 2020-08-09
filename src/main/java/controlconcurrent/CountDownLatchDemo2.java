package controlconcurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同时开始跑步；当所有人都到终点后，比赛结束
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        // 等待一个裁判
        CountDownLatch start = new CountDownLatch(1);
        // 等待五个运动员
        CountDownLatch end = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(no + " 准备好了，等待发令枪");
                    try {
                        start.await();
                        System.out.println("发令枪已响, " + no + "开始跑步");
                        // 模拟一个随机的跑步时长
                        Thread.sleep(new Random().nextInt(10) * 1000);
                        System.out.println(no + "已经跑到了终点");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 跑到终点或者中途退出后，通知我们的裁判员
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }

        // 裁判做比赛前的准备工作
        Thread.sleep(4000);
        System.out.println("发令枪响，比赛开始");
        start.countDown();
        end.await();
        System.out.println("所有人到达终点，比赛结束");
        executorService.shutdown();
    }

}
