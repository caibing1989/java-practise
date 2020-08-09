package threadcoreknowledge.threadobjectMethod;


/**
 * @Description: sleep不释放synchronized的monitor锁，等sleep时间到了之后，才会释放
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class SleepDoNotReleaseMonitor implements Runnable {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "获得了monitor");

            try {
                System.out.println(Thread.currentThread().getName() + "睡着了");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + "睡醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "释放了monitor");
        }
    }

    public static void main(String[] args) {
        SleepDoNotReleaseMonitor sleepDoNotReleaseMonitor = new SleepDoNotReleaseMonitor();
        new Thread(sleepDoNotReleaseMonitor).start();
        new Thread(sleepDoNotReleaseMonitor).start();
    }
}
