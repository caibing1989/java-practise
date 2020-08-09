package threadcoreknowledge.threadobjectMethod;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class JoinInterrupt implements Runnable {
    private Thread mainThread = Thread.currentThread();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行");
        mainThread.interrupt();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "执行完成了");

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new JoinInterrupt());
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "执行完成了");
    }
}
