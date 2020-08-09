package threadcoreknowledge.threadobjectMethod;

/**
 * @Description: 采用wait notify的方式，让两个线程交替奇偶打印0-100
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class PrintOddEvenWaitNotify implements Runnable {
    private final Object lock = new Object();
    private int count;

    @Override
    public void run() {
        while (count <= 100) {
            synchronized (lock) {
                // 拿到锁就打印
                System.out.println(Thread.currentThread().getName() + ": " + count++);
                lock.notify();

                // 如果没有打印完，就开始休眠，让出当前的锁
                if (count <= 100) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintOddEvenWaitNotify printOddEvenWaitNotify = new PrintOddEvenWaitNotify();
        Thread thread1 = new Thread(printOddEvenWaitNotify);
        Thread thread2 = new Thread(printOddEvenWaitNotify);
        thread1.start();
        thread2.start();
    }
}
