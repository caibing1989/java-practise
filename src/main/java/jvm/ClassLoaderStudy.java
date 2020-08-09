package jvm;

/**
 * @Description: 演示class loader
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class ClassLoaderStudy {
    public static void main(String[] args) throws ClassNotFoundException {
        // 采用启动类加机器
        String str = "hello, class loader";
        System.out.println("str class loader: " + str.getClass().getClassLoader());

        Class driver = Class.forName("java.sql.Driver");
        System.out.println("driver class loader: " + driver.getClassLoader());

        // 应用类加载器
        System.out.println("ClassLoaderStudy class loader: " + ClassLoaderStudy.class.getClassLoader());
        System.out.println("ClassLoaderStudy class parent loader: " + ClassLoaderStudy.class.getClassLoader().getParent());
        System.out.println("ClassLoaderStudy class parent.parent loader: " + ClassLoaderStudy.class.getClassLoader().getParent().getParent());

        MyClassLoader myClassLoader = new MyClassLoader("myClassLoader");
        Class myClass = myClassLoader.loadClass("jvm.MyClassTestForMyClassLoader");
        System.out.println("myClass class loader: " + myClass.getClassLoader());
        System.out.println("myClass class parent loader: " + myClass.getClassLoader().getParent());
        System.out.println("myClass class parent.parent loader: " + myClass.getClassLoader().getParent().getParent());

        Class driverManager = Class.forName("java.sql.DriverManager");
        System.out.println("driverManager class loader: " + driverManager.getClassLoader());
    }
}
