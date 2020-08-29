package cache.highperformancecache.computable;

import java.io.IOException;

/**
 * @Description: 耗时计算的实现类，有概览计算失败
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class MayFailFunction implements Computable<String, Integer> {
    @Override
    public Integer compute(String arg) throws Exception {
        double random = Math.random();
        if (random > 0.5) {
            throw new IOException("读取文件失败");
        }
        Thread.sleep(3000);
        return new Integer(arg);
    }
}
