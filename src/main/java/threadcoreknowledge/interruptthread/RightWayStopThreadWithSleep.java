package threadcoreknowledge.interruptthread;

/**
 * @Description: 带有sleep的中断线程的写法，线程阻塞时被中断
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class RightWayStopThreadWithSleep {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int number = 0;
            while (number <= 300 && !Thread.currentThread().isInterrupted()) {
                if (number % 100 == 0) {
                    System.out.println(number + "是100的倍数");
                }
                number++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
