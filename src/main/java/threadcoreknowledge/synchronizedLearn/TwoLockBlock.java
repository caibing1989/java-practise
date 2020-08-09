package threadcoreknowledge.synchronizedLearn;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class TwoLockBlock implements Runnable {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    @Override
    public void run() {

        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "获得了lock1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "工作完成，释放lock1");
        }

        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + "获得了lock2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "工作完成，释放lock2");
        }

    }

    public static void main(String[] args) {
        TwoLockBlock twoLockBlock = new TwoLockBlock();
        Thread thread1 = new Thread(twoLockBlock);
        Thread thread2 = new Thread(twoLockBlock);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("执行完毕");
    }
}
