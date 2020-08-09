package threadcoreknowledge.threadobjectMethod;

/**
 * @Description: join等价代码
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class JoinPrinciple implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "结束执行");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new JoinPrinciple());
        thread.start();
        synchronized (thread) {
            thread.wait();
        }
        System.out.println(Thread.currentThread().getName() + "结束执行");
    }
}
