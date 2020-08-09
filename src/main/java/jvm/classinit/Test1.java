package jvm.classinit;

import jvm.MyClassLoader;

/**
 * @Description: 如果类存在父类，且父类没有初始化，就先初始化父类
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException {
        new MyChild();

        // 调用ClassLoader类的loadClass方法来装载一个类，并不会初始化这个类，不是对类的主动使用
        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class myClass = myClassLoader.loadClass("jvm.classinit.MyChild");

        System.out.println("over");
    }
}
