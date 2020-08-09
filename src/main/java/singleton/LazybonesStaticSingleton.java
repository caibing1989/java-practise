package singleton;

/**
 * @Description: 懒汉 静态 线程不安全
 * @Author: mtdp
 * @Date: 2020-07-24
 */

public class LazybonesStaticSingleton {
    private static LazybonesStaticSingleton instance;

    private LazybonesStaticSingleton() {
    }

    public static LazybonesStaticSingleton getInstance() {
        if (instance == null) {
            instance = new LazybonesStaticSingleton();
        }
        return instance;
    }
}