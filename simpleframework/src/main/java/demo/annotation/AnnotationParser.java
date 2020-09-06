package demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class AnnotationParser {
    // 解析类的注解
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.TestAnnotation");
        // 这里获取的是Class对象的注解，而不是其里面的方法和成员变量的注解
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation) annotation;
            System.out.println("课程名: " + courseInfoAnnotation.courseName());
            System.out.println("课程标签: " + courseInfoAnnotation.courseTag());
            System.out.println("课程简介: " + courseInfoAnnotation.courseProfile());
            System.out.println("课程序号: " + courseInfoAnnotation.courseIndex());
        }
    }

    // 解析成员变量上的注解
    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.TestAnnotation");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            // 判断成员变量中是否有指定注解类型的注解
            boolean hasAnnotation = f.isAnnotationPresent(PersonInfoAnnotation.class);
            if (hasAnnotation) {
                PersonInfoAnnotation personInfoAnnotation = f.getAnnotation(PersonInfoAnnotation.class);
                System.out.println("名字: " + personInfoAnnotation.name());
                System.out.println("年龄: " + personInfoAnnotation.age());
                System.out.println("性别: " + personInfoAnnotation.gender());
                for (String language : personInfoAnnotation.language()) {
                    System.out.println("课程名: " + language);
                }
            }
        }
    }

    // 解析成员方法上的注解
    public static void parseMethodAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("demo.annotation.TestAnnotation");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            boolean hasAnnotation = m.isAnnotationPresent(CourseInfoAnnotation.class);
            if (hasAnnotation) {
                CourseInfoAnnotation courseInfoAnnotation = m.getAnnotation(CourseInfoAnnotation.class);
                System.out.println("课程名: " + courseInfoAnnotation.courseName());
                System.out.println("课程标签: " + courseInfoAnnotation.courseTag());
                System.out.println("课程简介: " + courseInfoAnnotation.courseProfile());
                System.out.println("课程序号: " + courseInfoAnnotation.courseIndex());
            }
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
        parseTypeAnnotation();
        System.out.println("--------------------");
        parseFieldAnnotation();
        System.out.println("--------------------");
        parseMethodAnnotation();
    }
}
