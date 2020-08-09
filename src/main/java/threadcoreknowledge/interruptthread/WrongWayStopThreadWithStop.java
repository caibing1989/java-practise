package threadcoreknowledge.interruptthread;

/**
 * @Description: 使用废弃的stop方法停止线程，会造成数据不一致 不安全的问题
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class WrongWayStopThreadWithStop implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            System.out.println("开始发放第" + i + "个连队");

            for (int j = 0; j < 10; j++) {
                System.out.println("开始发放第" + j + "个士兵");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("第" + i + "个连队发放完毕");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WrongWayStopThreadWithStop());
        thread.start();
        Thread.sleep(150);
        thread.stop();
    }
}
