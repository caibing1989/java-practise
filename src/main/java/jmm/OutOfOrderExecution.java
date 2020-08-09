package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 演示重排序
 * @Author: mtdp
 * @Date: 2020-07-20
 */

public class OutOfOrderExecution {
    static private int a = 0, b = 0;
    static private int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        while (true) {
            i++;
            // 初始化
            a = 0;
            b = 0;
            x = 0;
            y = 0;

            CountDownLatch countDownLatch = new CountDownLatch(1);

            Thread thread1 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread thread2 = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            thread1.start();
            thread2.start();
            countDownLatch.countDown();

            thread2.join();
            thread1.join();

            // 指令重排序
            if (x == 0 && y == 0) {
                System.out.println("经过" + i + "轮循环后，x=" + x + " y=" + y);
                break;
            } else {
                System.out.println("经过" + i + "轮循环后，x=" + x + " y=" + y);
            }
        }
    }
}
