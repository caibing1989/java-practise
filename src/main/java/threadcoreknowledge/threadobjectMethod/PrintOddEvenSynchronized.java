package threadcoreknowledge.threadobjectMethod;

/**
 * @Description: 两个线程交替奇偶打印0-100，采用synchronized实现，该方法存在效率的问题
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class PrintOddEvenSynchronized {
    private static final Object lock = new Object();
    private static int count;

    public static void main(String[] args) {
        // 构建两个线程, 线程1处理偶数，线程2处理奇数，采用位运算

        // 处理偶数
        Thread thread1 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    // 偶数
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }

        }, "偶数线程");

        // 处理奇数
        Thread thread2 = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    // 奇数
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "奇数线程");

        thread1.start();
        thread2.start();
    }
}
