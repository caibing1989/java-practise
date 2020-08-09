package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: lock不会像synchronized那样，遇到异常时自动释放，需要在finally中释放锁，以便在发生异常时被释放
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class MustUnlock {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        } finally {
            lock.unlock();
        }
    }


}
