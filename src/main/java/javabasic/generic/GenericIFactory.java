package javabasic.generic;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public interface GenericIFactory<T, N> {
    T nextObject();

    N nextNumber();
}
