package threadcoreknowledge.threadsafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: i++会出现线程安全问题，当出现线程安全问题时，需要将其打印出来，并统计最后有多少个这样的问题出现
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class MultiThreadError implements Runnable {
    private static int number;
    // 原子整型帮助辅助统计
    private static AtomicInteger realNumber = new AtomicInteger();
    private static AtomicInteger wrongNumber = new AtomicInteger();
    // 初始化，都为false
    private boolean[] numberArray = new boolean[2000000];

    // 添加2个单位为2的栅栏
    private CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    private CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    @Override
    public void run() {
        // 设置特殊情况
        numberArray[0] = true;

        for (int i = 0; i < 100000; i++) {

            // number++ 前后添加栅栏
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            number++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            realNumber.incrementAndGet();

            // 添加同步代码块，将检查操作原子化
            synchronized (this) {
                // 两个条件，避免synchronized带来的可见性的判读错误
                if (numberArray[number] && numberArray[number - 1]) {
                    // 错误数加1
                    wrongNumber.incrementAndGet();
                    System.out.println("错误的数字为" + number);
                }

                // 正常情况下，将number位置设置为true，表示已计算过
                numberArray[number] = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError multiThreadError = new MultiThreadError();
        Thread thread1 = new Thread(multiThreadError);
        Thread thread2 = new Thread(multiThreadError);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("最后number的数量为" + number);
        System.out.println("最后真正的数量应该为" + realNumber);
        System.out.println("最后错误的数量应该为" + wrongNumber);
    }
}
