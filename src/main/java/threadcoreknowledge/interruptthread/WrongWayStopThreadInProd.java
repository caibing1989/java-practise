package threadcoreknowledge.interruptthread;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class WrongWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("go");
            throwInMethod();
        }
    }

    private void throwInMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WrongWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
