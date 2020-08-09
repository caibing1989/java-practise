package threadcoreknowledge.interruptthread;

/**
 * @Description: 不应该屏蔽中断，吞了中断信号
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class CannotStopThread {

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            int number = 0;

                // sleep 响应中断之后isInterrupted会被清除
                while (number <= 30000 && !Thread.currentThread().isInterrupted()) {
                    if (number % 100 == 0) {
                        System.out.println(number + "是100的倍数");
                    }
                    number++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 恢复设置中断状态，以便在后续的动作中去发现中断
                        // Thread.currentThread().interrupt();
                    }
                }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
