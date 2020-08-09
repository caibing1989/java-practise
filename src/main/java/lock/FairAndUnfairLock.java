package lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示公平锁和非公平锁
 * @Author: mtdp
 * @Date: 2020-07-29
 */

public class FairAndUnfairLock {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new MyRunnable(printQueue));
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}

class MyRunnable implements Runnable {
    private PrintQueue printQueue;

    public MyRunnable(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始打印");
        printQueue.printPaper();
        System.out.println(Thread.currentThread().getName() + "打印结束");
    }
}

class PrintQueue {
    // true为公平锁，false为非公平锁
    Lock lock = new ReentrantLock(true);

    public void printPaper() {
        lock.lock();
        try {
            int times = new Random().nextInt(10);
            System.out.println(Thread.currentThread().getName() + "正在打印第一个张，需要" + times + "秒");
            Thread.sleep(times * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            int times = new Random().nextInt(10);
            System.out.println(Thread.currentThread().getName() + "正在打印第二个张，需要" + times + "秒");
            Thread.sleep(times * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
