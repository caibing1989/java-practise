package cas;

/**
 * @Description: 用cas等价代码来演示两个竞争者的操作
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class TwoThreadCompetition implements Runnable {
    private volatile int value;

    private synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            oldValue = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        value = compareAndSwap(0, 1);
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompetition twoThreadCompetition = new TwoThreadCompetition();
        Thread thread1 = new Thread(twoThreadCompetition);
        Thread thread2 = new Thread(twoThreadCompetition);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(twoThreadCompetition.value);
    }
}
