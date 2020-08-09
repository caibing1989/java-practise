package singleton;

/**
 * @Description: 静态内部类的形式，从大的范围归属为懒汉，在类加载时，jvm不会将内部类的静态代码初始化的，这样饿汉的缺点不存在，只有在真正调用获取方法的时候才会初始化。
 * 并且类的加载性质，jvm保证了该过程的线程安全性 （可用）
 * @Author: mtdp
 * @Date: 2020-07-26
 */

public class StaticInnerClassSingleton {

    static class InnerClass {
        private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton getInstance() {
        return InnerClass.instance;
    }
}
