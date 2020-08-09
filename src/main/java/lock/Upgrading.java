package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 演示读写锁升降级
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class Upgrading {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void readUpgrading() {
        System.out.println(Thread.currentThread().getName() + "尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "已经获取读锁");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "尝试升级为写锁");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "已经升级为写锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
        }
    }

    public static void writeUpgrading() {
        System.out.println(Thread.currentThread().getName() + "尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "已经获取写锁");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + "尝试降级为读锁");
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "已经降级为读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
        }

    }

    public static void main(String[] args) {
        // 演示读锁升级写锁，将会阻塞
//        Thread thread1 = new Thread(Upgrading::readUpgrading);
//        thread1.start();

        // 演示写锁降级为读锁
        Thread thread2 = new Thread(Upgrading::writeUpgrading);
        thread2.start();
    }

}
