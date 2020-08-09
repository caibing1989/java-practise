package threadcoreknowledge.sixstates;

/**
 * @Description: 展示线程的new、runnable、terminated状态
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class NewRunnableTerminated implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(13);
        System.out.println(thread.getState());
        Thread.sleep(1000);
        System.out.println(thread.getState());
    }
}
