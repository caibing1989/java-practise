package threadcoreknowledge.synchronizedLearn;

/**
 * @Description: 方法调用其它方法的可重入性验证
 * @Author: mtdp
 * @Date: 2020-07-14
 */

public class OtherMethodRecursionBlock implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new OtherMethodRecursionBlock());
        thread.start();
    }

    @Override
    public void run() {
        method1();
    }

    private synchronized void method1() {
        System.out.println("我是方法1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("我是方法2");
    }
}
