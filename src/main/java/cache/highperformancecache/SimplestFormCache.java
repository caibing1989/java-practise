package cache.highperformancecache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 最简单形式的缓存:hashmap
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class SimplestFormCache {
    private final HashMap<String, Integer> cache = new HashMap<>();

    public Integer computer(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        // 先检查hashmap里面有没有保存过之前的计算结果
        if (result == null) {
            // 如果缓存中找不到，需要现在来计算，并保存到hashmap中
            result = doComputer(userId);
            cache.put(userId, result);
        }
        return result;
    }

    private Integer doComputer(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        SimplestFormCache simplestFormCache = new SimplestFormCache();
        System.out.println("开始进行计算了");
        Integer result = simplestFormCache.computer("13");
        System.out.println("第一次计算结果：" + result);
        result = simplestFormCache.computer("13");
        System.out.println("第二次计算结果：" + result);
    }
}

/**
 * 缺点：存在并发安全问题、复用性差、代码的侵入性高
 *
 */
