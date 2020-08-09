package threadPool.rejectExecution;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-08
 */

public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        new Thread(runnable, "额外新增线程").start();
    }
}
