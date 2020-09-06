package demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class FieldCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获取Class对象
        Class clazz = Class.forName("demo.reflect.ReflectTarget");

        System.out.println("--------------获取所有公有的字段----------------");
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        System.out.println("--------------获取所有的字段----------------");
        fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }

        System.out.println("--------------获取公有字段并调用----------------");
        Field f = clazz.getField("name");
        System.out.println("公有的字段 name：" + f);
        ReflectTarget reflectTarget = (ReflectTarget) clazz.getConstructor().newInstance();
        System.out.println("--------------给获取到的field赋值----------------");
        f.set(reflectTarget, "待反射1号");
        System.out.println("验证 name：" + reflectTarget.name);

        System.out.println("--------------获取单个私有的字段----------------");
        f = clazz.getDeclaredField("targetInfo");
        f.setAccessible(true);
        System.out.println(f);
        f.set(reflectTarget, "18301230790");

    }
}
