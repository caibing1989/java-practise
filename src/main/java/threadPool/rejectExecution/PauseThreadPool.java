package threadPool.rejectExecution;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 可暂停的线程池，勾子方法，可以用来对线程池的日志记录
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class PauseThreadPool extends ThreadPoolExecutor {
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private Condition unPaused = reentrantLock.newCondition();
    private boolean isPause;

    public PauseThreadPool(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i, i1, l, timeUnit, blockingQueue);
    }

    public PauseThreadPool(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i1, l, timeUnit, blockingQueue, threadFactory);
    }

    public PauseThreadPool(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i1, l, timeUnit, blockingQueue, rejectedExecutionHandler);
    }

    public PauseThreadPool(int i, int i1, long l, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i1, l, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        reentrantLock.lock();
        try {
            while (isPause) {
                System.out.println(thread.getName() + "被暂停了");
                unPaused.await();
                System.out.println(thread.getName() + "被恢复了");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void pause() {
        reentrantLock.lock();
        try {
            isPause = true;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void resume() {
        reentrantLock.lock();
        try {
            isPause = false;
            unPaused.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PauseThreadPool pauseThreadPool = new PauseThreadPool(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                    System.out.println("我被执行了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10000; i++) {
            pauseThreadPool.execute(runnable);
        }

        Thread.sleep(150);
        pauseThreadPool.pause();
        System.out.println("线程池被暂停了");
        Thread.sleep(1000);
        pauseThreadPool.resume();
        System.out.println("线程池被恢复了");
    }

}
