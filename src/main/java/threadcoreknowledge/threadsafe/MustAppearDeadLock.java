package threadcoreknowledge.threadsafe;

/**
 * @Description: 演示一个一定出现死锁的情况
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class MustAppearDeadLock implements Runnable {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    private int flag;

    private MustAppearDeadLock(int flag) {
        this.flag = flag;
    }

    public static void main(String[] args) {
        MustAppearDeadLock mustAppearDeadLock1 = new MustAppearDeadLock(0);
        MustAppearDeadLock mustAppearDeadLock2 = new MustAppearDeadLock(1);

        new Thread(mustAppearDeadLock1).start();
        new Thread(mustAppearDeadLock2).start();
    }

    @Override
    public void run() {
        if (flag == 1) {
            System.out.println(Thread.currentThread().getName() + "的flag为1， 等待lock1");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "获得lock1");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "拿着lock1， 等待lock2");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "进入lock2");
                }
            }
        }

        if (flag == 0) {
            System.out.println(Thread.currentThread().getName() + "的flag为0， 等待lock2");
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "获得lock2");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "拿着lock2， 等待lock1");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "进入lock1");
                }
            }
        }
    }
}
