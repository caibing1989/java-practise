package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 演示可见性问题
 * @Author: mtdp
 * @Date: 2020-07-21
 */

public class FieldVisibility {
//    private int a = 2;
//    private int b = 3;

    // 可以用volatile来解决可见性问题
    private volatile int a = 2;
    private volatile int b = 3;

    private void change() {
        this.a = 4;
        this.b = a;
    }

    // a == 2 && b == 4时，发生可见性问题
    private void print() {
        // if中的内容本身不是原子操作，存在线程切换的可能，所以将this.a == 2 && this.b == 4 调整为 this.b == 4 && this.a == 2，后者一定不会出现
        if (this.b == 4 && this.a == 2) {
            System.out.println("发生了可见性问题");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            FieldVisibility fieldVisibility = new FieldVisibility();
            CountDownLatch countDownLatch = new CountDownLatch(1);

            Thread thread1 = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fieldVisibility.change();
            });

            Thread thread2 = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                fieldVisibility.print();
            });

            thread1.start();
            thread2.start();
            countDownLatch.countDown();

            thread1.join();
            thread2.join();
        }
    }

}
