package Immutable;

/**
 * @Description: 演示栈封闭的两种情况，基本变量和对象，先演示线程争抢带来的错误结果，然后把变量放到方法内，情况就变了
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class StackConfinement implements Runnable {
    int index = 0;

    private void inThread() {
        // 方法内的变量是局部变量，可以放心大胆的使用
        int neverGoOut = 0;

        for (int i = 0; i < 10000; i++) {
            neverGoOut++;
        }
        System.out.println("栈内保护的数字是线程安全的" + neverGoOut);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException {
        StackConfinement stackConfinement = new StackConfinement();

        Thread thread1 = new Thread(stackConfinement);
        Thread thread2 = new Thread(stackConfinement);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("index: " + stackConfinement.index);
    }
}
