package singleton;

/**
 * @Description: 饿汉模式 静态
 * @Author: mtdp
 * @Date: 2020-07-24
 */

public class HungryStaticSingleton {
    private static HungryStaticSingleton instance = new HungryStaticSingleton();

    private HungryStaticSingleton() {
    }

    public static HungryStaticSingleton getInstance() {
        return instance;
    }
}
