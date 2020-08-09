package singleton;

/**
 * @Description: 双重检查锁模式，线程安全、延时加载、效率较高
 * @Author: mtdp
 * @Date: 2020-07-25
 */

public class DoubleCheckSingleton {
    // 禁止构造该对象时的重排序，避免空指针问题，构造对象时有三个步骤（分配内存、调用构造函数初始化、赋值），非原子的
    private volatile static DoubleCheckSingleton instance;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }

}
