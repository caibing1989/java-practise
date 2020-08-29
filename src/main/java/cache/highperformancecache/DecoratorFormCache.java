package cache.highperformancecache;

import cache.highperformancecache.computable.Computable;
import cache.highperformancecache.computable.ExpensiveFunction;

import java.util.HashMap;

/**
 * @Description: 用装饰者模式，给计算器自动添加缓存功能
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public class DecoratorFormCache<A, V> implements Computable<A, V> {
    private final HashMap<A, V> cache = new HashMap<>();

    private final Computable<A, V> computable;

    public DecoratorFormCache(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        DecoratorFormCache<String, Integer> decoratorFormCache = new DecoratorFormCache<>(new ExpensiveFunction());
        System.out.println("开始进行计算了");
        Integer result = decoratorFormCache.compute("13");
        System.out.println("第一次计算结果：" + result);
        result = decoratorFormCache.compute("13");
        System.out.println("第二次计算结果：" + result);
    }
}
