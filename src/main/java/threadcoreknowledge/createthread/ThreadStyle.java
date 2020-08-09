package threadcoreknowledge.createthread;

/**
 * @Description: 用Thread方式实现线程
 * @Author: mtdp
 * @Date: 2020-07-10
 */

public class ThreadStyle extends Thread {
    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        threadStyle.start();
    }

    @Override
    public void run() {
        System.out.println("用Thread实现线程");
    }
}
