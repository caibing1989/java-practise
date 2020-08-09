package threadcoreknowledge.createthread;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-11
 */

public class AnonymousStyle {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
