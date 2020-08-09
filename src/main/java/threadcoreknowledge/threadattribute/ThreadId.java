package threadcoreknowledge.threadattribute;

/**
 * @Description: 线程id从1开始，但是jvm运行起来之后，我们自己创建的线程id早已不是2，因为jvm自己已经后台创建了很多线程
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class ThreadId {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程id " + Thread.currentThread().getId());
        System.out.println("子线程id " + thread.getId());
    }

}
