package threadcoreknowledge.uncaughtexception;

/**
 * @Description: 单线程有异常，抛出，打印堆栈；子线程出现异常，会怎么样呢
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class ExceptionInChildThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Do you find me?");
    }

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
