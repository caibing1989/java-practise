package controlconcurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: 演示CyclicBarrier，可重用
 * @Author: mtdp
 * @Date: 2020-08-03
 */

public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("已经全部到齐啦，准备出发");
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "准备前往集合地点");
            try {
                Thread.sleep(new Random().nextInt(10) * 100);
                System.out.println(Thread.currentThread().getName() + "已经到达集合地点");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "走，出发");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
