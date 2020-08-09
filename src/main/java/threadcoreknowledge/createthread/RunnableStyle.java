package threadcoreknowledge.createthread;

/**
 * @Description: 用Runnable方式实现线程
 * @Author: mtdp
 * @Date: 2020-07-10
 */

public class RunnableStyle implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnable实现线程");
    }
}
