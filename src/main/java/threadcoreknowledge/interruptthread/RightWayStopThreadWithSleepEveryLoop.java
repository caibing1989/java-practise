package threadcoreknowledge.interruptthread;

/**
 * @Description: 不需要每次循环都监测中断，因为sleep会响应中断
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            int number = 0;

            try {
                while (number <= 30000) {
                    if (number % 100 == 0) {
                        System.out.println(number + "是100的倍数");
                    }
                    number++;
                    Thread.sleep(10);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
