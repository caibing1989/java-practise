package cache.highperformancecache.computable;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 耗时计算的实现类，实现了Computable接口，但是本身不具备缓存的能力
 * 而且也不需要考虑缓存的能力
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class ExpensiveFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(arg);
    }
}
