package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 演示可重入锁
 * @Author: mtdp
 * @Date: 2020-07-30
 */

public class GetHoldCount {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println("-------开始入锁-------");
        reentrantLock.lock();
        System.out.println("重入了" + reentrantLock.getHoldCount() +"次");
        reentrantLock.lock();
        System.out.println("重入了" + reentrantLock.getHoldCount() +"次");
        reentrantLock.lock();
        System.out.println("重入了" + reentrantLock.getHoldCount() +"次");
        reentrantLock.lock();
        System.out.println("重入了" + reentrantLock.getHoldCount() +"次");
        reentrantLock.lock();
        System.out.println("重入了" + reentrantLock.getHoldCount() +"次");
        System.out.println("-------开始出锁-------");
        reentrantLock.unlock();
        System.out.println("还剩下" + reentrantLock.getHoldCount() +"次");
        reentrantLock.unlock();
        System.out.println("还剩下" + reentrantLock.getHoldCount() +"次");
        reentrantLock.unlock();
        System.out.println("还剩下" + reentrantLock.getHoldCount() +"次");
        reentrantLock.unlock();
        System.out.println("还剩下" + reentrantLock.getHoldCount() +"次");
        reentrantLock.unlock();
        System.out.println("还剩下" + reentrantLock.getHoldCount() +"次");
    }
}
