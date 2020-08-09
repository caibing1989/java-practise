package threadcoreknowledge.sixstates;

import static java.lang.Thread.sleep;

/**
 * @Description: 展示block、waiting、TimesWaiting
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class BlockedWaitingTimedWaiting implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting blockedWaitingTimedWaiting = new BlockedWaitingTimedWaiting();

        Thread thread1 = new Thread(blockedWaitingTimedWaiting);
        thread1.start();
        Thread thread2 = new Thread(blockedWaitingTimedWaiting);
        thread2.start();
        System.out.println("thread1 " + thread1.getState());
        System.out.println("thread2 " + thread2.getState());
        Thread.sleep(1500);
        System.out.println("thread1 " + thread1.getState());
        System.out.println("thread2 " + thread2.getState());
        Thread.sleep(1500);
        System.out.println("thread1 " + thread1.getState());
        System.out.println("thread2 " + thread2.getState());
    }

    @Override
    public void run() {
        sync();
    }

    private synchronized void sync() {
        try {
            sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
