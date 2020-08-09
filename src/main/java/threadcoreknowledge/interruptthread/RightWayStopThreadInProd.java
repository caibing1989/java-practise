package threadcoreknowledge.interruptthread;

/**
 * @Description: 恢复中断，以便后续的程序能够发现中断
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupt");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

}
