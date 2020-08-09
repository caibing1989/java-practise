package threadPool.rejectExecution;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-07
 */

public class ExecutorTask {
    static ThreadPoolExecutor abortThreadPoolExecutor = new ThreadPoolExecutor(1, 2, 0,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(2),
            new ThreadPoolExecutor.AbortPolicy());

    static ThreadPoolExecutor callerRunsPolicyExecutor = new ThreadPoolExecutor(1, 2, 0,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2),
            new ThreadPoolExecutor.CallerRunsPolicy());

    static ThreadPoolExecutor discardPolicyExecutor = new ThreadPoolExecutor(1, 2, 0,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2),
            new ThreadPoolExecutor.DiscardPolicy());

    static ThreadPoolExecutor discardOldestPolicyExecutor = new ThreadPoolExecutor(1, 2, 0,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    static ThreadPoolExecutor myPolicyExecutor = new ThreadPoolExecutor(1, 2, 0,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2),
            new MyRejectedExecutionHandler());
}
