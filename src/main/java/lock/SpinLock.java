package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 演示自旋锁，要想自旋，必须要有个原子操作
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class SpinLock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();

        while (!sign.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + "自旋锁获取失败，重新尝试");
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        sign.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "尝试获取自旋锁");
                spinLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "已经获取自旋锁");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "释放自旋锁");
                    spinLock.unlock();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }

}
