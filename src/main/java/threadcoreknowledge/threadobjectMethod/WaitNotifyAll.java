package threadcoreknowledge.threadobjectMethod;

/**
 * @Description: 测试wait和notifyAll
 * @Author: mtdp
 * @Date: 2020-07-15
 */

public class WaitNotifyAll implements Runnable {
    static final Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                System.out.println(Thread.currentThread().getName() + "进入阻塞，等待restart");
                object.wait();
                System.out.println(Thread.currentThread().getName() + "结束阻塞，restart完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll waitNotifyAll = new WaitNotifyAll();
        Thread thread1 = new Thread(waitNotifyAll);
        Thread thread2 = new Thread(waitNotifyAll);
        Thread thread3 = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始唤醒所有阻塞线程");
                object.notifyAll();
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(200);
        System.out.println(thread1.getName() + "的状态是" + thread1.getState());
        System.out.println(thread2.getName() + "的状态是" + thread2.getState());
        thread3.start();
    }
}
