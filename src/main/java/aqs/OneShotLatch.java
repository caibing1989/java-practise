package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description: 用AQS实现一个简单的线程协作器，起初这个门栓是关闭的，很多线程进来都会去调用await方法，谁调用谁就进入等待
 * 直到后续有线程调用signal方法，将这个门栓打开了，此时之前所有等待的线程都会被释放
 * @Author: mtdp
 * @Date: 2020-08-04
 */

public class OneShotLatch {

    private Sync sync = new Sync();

    private void signal() {
        sync.releaseShared(0);
    }

    public void await() {
        sync.acquireShared(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int i) {
            // 如果当前state=1，代表门已经打开了，可以进去了；否则是没有打开，必须等待，进入阻塞
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int i) {
            // 打开闸门
            setState(1);
            // 唤醒已被阻塞的线程
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "尝试通过门闸进入房间");
                oneShotLatch.await();
                System.out.println(Thread.currentThread().getName() + "已经进入房间");
            }).start();
        }

        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + "主人沉睡了5秒，来开门");
        oneShotLatch.signal();
    }
}
