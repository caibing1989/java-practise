package threadcoreknowledge.createthread;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class LambdaStyle {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
