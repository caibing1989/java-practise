package lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 演示读写锁
 * @Author: mtdp
 * @Date: 2020-07-30
 */

public class CinemaReadWrite {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void read() {
        System.out.println(Thread.currentThread().getName() + "准备获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取读锁，开始读");
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        System.out.println(Thread.currentThread().getName() + "准备获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取写锁，开始写");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
//        new Thread(CinemaReadWrite::read, "thread_1").start();
//        new Thread(CinemaReadWrite::read, "thread_2").start();
//        new Thread(CinemaReadWrite::write, "thread_3").start();
//        new Thread(CinemaReadWrite::write, "thread_4").start();

        // 演示非公平锁效果，读锁插队到写锁前面
        new Thread(CinemaReadWrite::write, "thread_1").start();
        new Thread(CinemaReadWrite::read, "thread_2").start();
        new Thread(CinemaReadWrite::read, "thread_3").start();
        new Thread(CinemaReadWrite::read, "thread_3.1").start();
        new Thread(CinemaReadWrite::read, "thread_3.2").start();
        new Thread(CinemaReadWrite::read, "thread_3.3").start();
        new Thread(CinemaReadWrite::write, "thread_4").start();
        new Thread(CinemaReadWrite::read, "thread_5").start(); // thread_5不能插队 因为前面有写锁

        new Thread(() -> {
            Thread[] threads = new Thread[10000];
            for (int i = 0; i < 10000; i++) {
                threads[i] = new Thread(CinemaReadWrite::read, "子线程创建的Thread" + i);
            }
            for (int i = 0; i < 10000; i++) {
                threads[i].start();
            }
        }, "thread-6").start();

    }
}
