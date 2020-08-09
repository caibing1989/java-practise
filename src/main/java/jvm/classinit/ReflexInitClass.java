package jvm.classinit;

/**
 * @Description: 演示反射也可以初始化这个类
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class ReflexInitClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Class myChildClass = Class.forName("jvm.classinit.MyChild");
    }
}
