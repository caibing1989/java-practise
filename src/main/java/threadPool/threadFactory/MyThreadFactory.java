package threadPool.threadFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-07
 */

public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger atomicInteger = new AtomicInteger();

    // 是否是守护线程池
    // 守护线程是一种低级别的线程，服务于用户线程的线程，当没有用户线程可以服务时，守护线程会自动退出
    // jvm的垃圾回收线程就是守护线程，当没有垃圾需要清理时，该线程就会自动退出
    // 因为守护线程的退出机制和优先级，所以用户的业务线程不要轻易设置为守护线程
    private boolean isDaemon;

    public MyThreadFactory(boolean isDaemon) {
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        atomicInteger.getAndIncrement();
        MyCustomThead myCustomThead = new MyCustomThead(atomicInteger, runnable);
        myCustomThead.setDaemon(isDaemon);

        return myCustomThead;
    }
}
