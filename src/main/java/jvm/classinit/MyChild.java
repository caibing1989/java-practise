package jvm.classinit;

/**
 * @Description: 如果类中存在初始化语句，就依次初始化这些语句
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class MyChild extends MyParent {
    static {
        System.out.println("my child class init");
    }

    static {
        System.out.println("my child static block 22222");
    }

    public static int a = 5;

    // 常量在编译阶段就会存入到常量池中，它没有直接引用到定义常量的类，因此它不会触发定义这个常量所属类的初始化
    public final static int b = 4;

    public static void staticMethod() {
        System.out.println("CallStaticMethodOfClass");
    }

    static {
        System.out.println("my child static block 333" + a);
    }
}
