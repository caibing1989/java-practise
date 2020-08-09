package singleton;

/**
 * @Description: 懒汉 静态 线程安全 效率低
 * @Author: mtdp
 * @Date: 2020-07-24
 */

public class LazybonesStaticSafeSingleton {
    private static LazybonesStaticSafeSingleton instance;

    private LazybonesStaticSafeSingleton() {
    }

    public synchronized static LazybonesStaticSafeSingleton getInstance() {
        if (instance == null) {
            instance = new LazybonesStaticSafeSingleton();
        }
        return instance;
    }
}
