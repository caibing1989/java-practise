package demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 获取成员方法并调用：
 * 1、批量的：
 * public Method[] getMethods()，获取所有的公有方法，包含父类的方法也包含Object类
 * public Method[] getDeclaredMethods(), 获取所有的成员方法，包括私有的，不包括继承的
 * 2、获取单个的：
 * public Method getMethod(String name, Class<?>... parameterTypes)
 * public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
 * <p>
 * 调用方法：
 * Method --> public Object invoke(Object obj, Object args)
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class MethodCollector {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        // 获取Class对象
        Class clazz = Class.forName("demo.reflect.ReflectTarget");

        System.out.println("--------------获取的所有公有方法----------------");
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        System.out.println("--------------获取该类的所有方法----------------");
        methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }

        System.out.println("--------------获取单个公有方法----------------");
        Method method = clazz.getMethod("show1", String.class);
        System.out.println(method);
        ReflectTarget reflectTarget = (ReflectTarget) clazz.getConstructor().newInstance();
        method.invoke(reflectTarget, "待反射方法1号");

        System.out.println("--------------获取一个私有的成员方法----------------");
        method = clazz.getDeclaredMethod("show4", int.class);
        method.setAccessible(true);
        String resultStr = String.valueOf(method.invoke(reflectTarget, 20));
        System.out.println(resultStr);
    }
}
