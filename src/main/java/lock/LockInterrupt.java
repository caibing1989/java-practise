package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 在获取锁的过程中，被中途打断了
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class LockInterrupt implements Runnable {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        LockInterrupt lockInterrupt1 = new LockInterrupt();
        LockInterrupt lockInterrupt2 = new LockInterrupt();
        Thread thread1 = new Thread(lockInterrupt1);
        Thread thread2 =new Thread(lockInterrupt2);
        thread1.start();
        thread2.start();

        Thread.sleep(1000);

//        thread1.interrupt();
        thread2.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获得该锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获得了该锁");
                // 就是要让第二个线程等待
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了该锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "等锁期间被中断");
        }
    }
}
