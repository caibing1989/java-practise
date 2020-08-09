package lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 使用trylock来避免死锁
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class TryLockAvoidDeadLock implements Runnable {
    private int flag = 1;
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockAvoidDeadLock tryLockAvoidDeadLock1 = new TryLockAvoidDeadLock();
        TryLockAvoidDeadLock tryLockAvoidDeadLock2 = new TryLockAvoidDeadLock();
        tryLockAvoidDeadLock1.flag = 1;
        tryLockAvoidDeadLock2.flag = 2;

        new Thread(tryLockAvoidDeadLock1).start();
        new Thread(tryLockAvoidDeadLock2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "已经获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));

                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "已经获取到了锁2，获取了两把锁");
                                    Thread.sleep(new Random().nextInt(1000));
                                    System.out.println(Thread.currentThread().getName() + "执行完毕");
                                    break;
                                } finally {
                                    lock2.unlock();
                                    System.out.println(Thread.currentThread().getName() + "已经释放锁2");
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + "获取锁2失败，开始重试");
                            }
                        } finally {
                            lock1.unlock();
                            System.out.println(Thread.currentThread().getName() + "已经释放锁1");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁1失败，开始重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (flag == 2) {
                try {
                    if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "已经获取到了锁2");
                            Thread.sleep(new Random().nextInt(1000));

                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "已经获取到了锁1，获取了两把锁");
                                    Thread.sleep(new Random().nextInt(1000));
                                    System.out.println(Thread.currentThread().getName() + "执行完毕");
                                    break;
                                } finally {
                                    lock1.unlock();
                                    System.out.println(Thread.currentThread().getName() + "已经释放锁1");
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + "获取锁1失败，开始重试");
                            }
                        } finally {
                            lock2.unlock();
                            System.out.println(Thread.currentThread().getName() + "已经释放锁2");
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取锁2失败，开始重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
