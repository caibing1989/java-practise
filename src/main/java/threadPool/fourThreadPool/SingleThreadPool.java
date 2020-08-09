package threadPool.fourThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: new Executors.FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()))
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class SingleThreadPool implements Runnable {
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executorService.execute(new SingleThreadPool());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
