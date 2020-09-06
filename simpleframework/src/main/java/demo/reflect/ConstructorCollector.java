package demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: 通过Class对象可以获取某个类中的构造方法
 * 获取构造方法：
 * 1、批量的方法：
 * public Constructor[] getConstructors() 获取所有公有的构造方法
 * public Constructor[] getDeclaredConstructors() 获取所有的构造方法
 * 2、获取单个的方法，并调用
 * public Constructor getConstructor(Class... parameterTypes) 获取单个的公有构造方法
 * public Constructor getDeclaredConstructor(Class... parameterTypes) 获取某个构造方法
 * <p>
 * 调用构造方法：
 * Constructor --> newInstance(Object... initargs)
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class ConstructorCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("demo.reflect.ReflectTarget");

        System.out.println("--------------获取的所有公有构造方法----------------");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }

        System.out.println("--------------获取的所有构造方法----------------");
        constructors = clazz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }

        System.out.println("--------------获取单个带两个参数的构造方法----------------");
        Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
        System.out.println("constructor = " + constructor);

        System.out.println("--------------获取单个带参数的私有构造方法----------------");
        constructor = clazz.getDeclaredConstructor(int.class);
        System.out.println("private constructor = " + constructor);

        System.out.println("--------------调用私有构造方法创建实例----------------");
        // 暴力访问，忽略掉访问修饰符
        constructor.setAccessible(true);
        ReflectTarget reflectTarget = (ReflectTarget) constructor.newInstance(1);
    }
}
