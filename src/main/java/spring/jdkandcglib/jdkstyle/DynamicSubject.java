package spring.jdkandcglib.jdkstyle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description: DynamicSubject这个类的作用是起到解耦的作用，使client不用关心server的对象长什么样子，server也不用关心client是什么
 * @Author: mtdp
 * @Date: 2020-08-09
 */

public class DynamicSubject implements InvocationHandler {
    // 这是动态代理的好处，被封装的对象是Object类型，接受任意类型的对象
    private Object object;

    public DynamicSubject() {
    }

    public DynamicSubject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before calling " + method);
        method.invoke(object, objects);
        System.out.println("after calling " + method);
        return null;
    }
}
