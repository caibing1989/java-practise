package threadcoreknowledge.threadobjectMethod;

/**
 * @Description: wait只释放当前那把锁
 * @Author: mtdp
 * @Date: 2020-07-16
 */

public class WaitNotifyOwn {

    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + "获得锁object1");
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + "获得锁object2");
                    try {
                        System.out.println(Thread.currentThread().getName() + "释放锁object2");
                        object2.wait();
                        System.out.println(Thread.currentThread().getName() + "重新获得锁object2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        Thread.sleep(100);

        new Thread(() -> {
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + "获得锁object2");
                System.out.println(Thread.currentThread().getName() + "尝试获得锁object1");
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName() + "获得锁object1");
                }
            }
        }).start();
    }
}
