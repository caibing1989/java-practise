package cache.highperformancecache.computable;

/**
 * @Description: 有一个计算函数compute，用来代表耗时计算，每个计算器都要实现这个接口，
 * 这样就可以无侵入实现缓存功能
 * @Author: mtdp
 * @Date: 2020-08-29
 */

public interface Computable<A, V> {
    V compute(A arg) throws Exception;
}
