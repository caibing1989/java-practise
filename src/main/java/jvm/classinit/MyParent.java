package jvm.classinit;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class MyParent {
    protected static String home = "fuzhou";

    static {
        System.out.println("my parent class init");
    }
}
