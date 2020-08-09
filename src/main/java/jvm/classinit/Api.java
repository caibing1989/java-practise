package jvm.classinit;

/**
 * @Description: 演示接口的初始化
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public interface Api {
    public static String str = "now in api";

    public void t1();

    // 定义了default方法的接口，当接口实现类初始化时，该接口也会被初始化
    public default void t3() {
        System.out.println("now in api t3()");
    }
}
