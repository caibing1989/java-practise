package threadcoreknowledge.synchronizedLearn;

/**
 * @Description: 方法本身的可重入性验证
 * @Author: mtdp
 * @Date: 2020-07-13
 */

public class RecursionBlock implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new RecursionBlock());
        thread.start();
    }

    @Override
    public void run() {
        method(0);
    }

    private synchronized void method(int i) {
        System.out.println("i = " + i);

        if (i == 0) {
            i++;
            method(i);
        }
    }
}
