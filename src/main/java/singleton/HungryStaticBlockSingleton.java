package singleton;

/**
 * @Description: 饿汉模式 静态代码块
 * @Author: mtdp
 * @Date: 2020-07-24
 */

public class HungryStaticBlockSingleton {
    private static HungryStaticBlockSingleton instance;

    static {
        instance = new HungryStaticBlockSingleton();
    }

    private HungryStaticBlockSingleton() {
    }

    public static HungryStaticBlockSingleton getInstance() {
        return instance;
    }
}
