package spring.jdkandcglib.jdkstyle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description: 生成代理实例，并调用了request()方法
 * @Author: mtdp
 * @Date: 2020-08-09
 */

public class TestJdk {

    public static void main(String[] args) {
        // 这里指定被代理类
        RealSubject realSubject = new RealSubject();

        // 动态代理对象
        InvocationHandler invocationHandler = new DynamicSubject(realSubject);

        Class<?> cls = realSubject.getClass();
        // 根据参数loader和interfaces调用方法 getProxyClass(loader, interfaces)创建代理类$Proxy0.$Proxy0类 实现了interfaces的接口,并继承了Proxy类.
        // 实例化$Proxy0并在构造方法中把DynamicSubject传过去,接着$Proxy0调用父类Proxy的构造器,为h赋值
        Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandler);

        System.out.println(subject instanceof Proxy);
        System.out.println("subject的class类是：" + subject.getClass().toString());
        System.out.println("subject的父类是：" + subject.getClass().getSuperclass().toString());

        System.out.println("\n"+"subject实现的接口是：");
        for (Class face : subject.getClass().getInterfaces()) {
            System.out.println(face.getSimpleName());
        }

        System.out.println("\n\n"+"运行结果为：");
        // 不需要显式的调用invoke()方法，因为里面有invoke方法
        subject.request();
    }
}
