package threadcoreknowledge.threadsafe;


/**
 * @Description: 初始化过程中的线程安全问题，还没有初始化完毕就将这个对象打印出来，可以用工厂模式解决
 * @Author: mtdp
 * @Date: 2020-07-19
 */

public class ThisThreadError {
    static Point point;

    public static void main(String[] args) {
        new MyThread().start();
        try {
//            Thread.sleep(100);
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (point != null) {
            System.out.println(point.toString());
        }
    }
}

class Point {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        ThisThreadError.point = this;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        new Point(1, 1);
    }
}
