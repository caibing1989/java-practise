package threadcoreknowledge.threadobjectMethod;

/**
 * @Description:  获取join时主线程的状态
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class JoinThreadState implements Runnable {
    private Thread mainThread = Thread.currentThread();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行");

        try {
            Thread.sleep(1000);
            System.out.println(mainThread.getName() + "的状态为：" + mainThread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new JoinThreadState());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
}
