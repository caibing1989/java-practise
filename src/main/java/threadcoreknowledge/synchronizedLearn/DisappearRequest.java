package threadcoreknowledge.synchronizedLearn;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class DisappearRequest implements Runnable {
    private static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DisappearRequest disappearRequest = new DisappearRequest();
        Thread thread1 = new Thread(disappearRequest);
        Thread thread2 = new Thread(disappearRequest);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("i = " + i);
    }
}
