package javabasic.generic;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

public class GenericIFactoryImpl<T, N> implements GenericIFactory<T, N> {
    @Override
    public T nextObject() {
        return null;
    }

    @Override
    public N nextNumber() {
        return null;
    }
}
