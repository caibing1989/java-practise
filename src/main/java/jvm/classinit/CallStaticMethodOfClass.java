package jvm.classinit;

/**
 * @Description: 演示调用类的静态方法会初始化这个类
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class CallStaticMethodOfClass {
    public static void main(String[] args) {
        MyChild.staticMethod();
    }
}
