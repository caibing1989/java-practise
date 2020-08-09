package threadcoreknowledge.threadobjectMethod;

/**
 * @Description: 测试wait和notify的执行顺序
 * @Author: mtdp
 * @Date: 2020-07-15
 */

public class WaitNotify {

    private static final Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(this.getName() + "开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + "获得锁后又执行");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(this.getName() + "开始唤醒其它任意一个线程");
                object.notify();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName() + "唤醒其它任意一个线程结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }


}
