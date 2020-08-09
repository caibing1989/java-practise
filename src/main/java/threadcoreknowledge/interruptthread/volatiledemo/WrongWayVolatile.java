package threadcoreknowledge.interruptthread.volatiledemo;

/**
 * @Description: 看似可行的停止线程的方式
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class WrongWayVolatile implements Runnable {
    private volatile boolean isCancel = false;

    @Override
    public void run() {
        int number = 0;

        try {
            while (number <= 5000 && !isCancel) {
                if (number % 100 == 0) {
                    System.out.println(number + "是100的倍数");
                }
                Thread.sleep(10);
                number++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.isCancel = true;
    }
}
