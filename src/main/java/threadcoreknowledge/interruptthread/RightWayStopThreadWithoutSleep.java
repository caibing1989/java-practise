package threadcoreknowledge.interruptthread;

/**
 * @Description: run方法内没有sleep或wait时，停止线程
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int number = 0;

        while (number <= Integer.MAX_VALUE / 2) {
            if (number % 10000 == 0 && !Thread.currentThread().isInterrupted()) {
                System.out.println(number + "是10000的倍数");
            }
            number++;
        }

        System.out.println("任务结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
