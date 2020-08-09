package threadcoreknowledge.threadobjectMethod;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示sleep不释放lock（lock需要主动释放）
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class SleepDoNotReleaseLock implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();

        System.out.println(Thread.currentThread().getName() + "获得了lock");

        try {
            System.out.println(Thread.currentThread().getName() + "睡着了");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "睡醒了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了lock");
        }
    }

    public static void main(String[] args) {
        SleepDoNotReleaseLock sleepDoNotReleaseLock = new SleepDoNotReleaseLock();
        new Thread(sleepDoNotReleaseLock).start();
        new Thread(sleepDoNotReleaseLock).start();
    }
}
