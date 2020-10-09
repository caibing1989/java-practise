package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

import static org.simpleframework.util.ClassUtil.setField;
import static org.simpleframework.util.ValidationUtil.isEmpty;


/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-05
 */

@Slf4j
public class DependencyInjector {
    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行IOC
     */
    public void doIoc() {
        // 1、遍历Bean容器中所有的Class对象
        if (isEmpty(beanContainer.getClasses())) {
            log.warn("empty class set in beanContainer");
            return;
        }
        for (Class<?> clazz : beanContainer.getClasses()) {
            // 2、遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                // 3、找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();

                    // 4、获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    // 5、获取这些成员变量的类型在容器里的实例
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue);
                    if (fieldValue == null) {
                        throw new RuntimeException("unable to inject relevant type, target class is " + fieldClass.getName()
                                + " autowired's value is " + autowiredValue);
                    }
                    // 6、通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                    Object targetBean = beanContainer.getBean(clazz);
                    setField(field, targetBean, fieldValue, true);
                }
            }
        }
    }

    /**
     * 根据Class在beanContainer里获取其实例或者实现类
     *
     * @param fieldClass
     * @param autowiredValue
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue != null) {
            return fieldValue;
        } else {
            Class<?> implementClass = getImplementClass(fieldClass, autowiredValue);
            if (implementClass != null) {
                return beanContainer.getBean(implementClass);
            } else {
                return null;
            }
        }
    }

    /**
     * 获取接口的实现类
     *
     * @param fieldClass
     * @param autowiredValue
     * @return
     */
    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (isEmpty(classSet)) {
            return null;
        }
        if (isEmpty(autowiredValue)) {
            // 用户并没有精确地告诉框架，该返回哪个具体类型的实现类
            if (classSet.size() == 1) {
                return classSet.iterator().next();
            } else {
                // 如果多于两个实现类，且用户未指定其中一个实现类，则抛出异常
                throw new RuntimeException("multiple implemented classes for " + fieldClass.getName()
                        + " please set @Autowired's value to pick one");
            }
        } else {
            for (Class<?> clazz : classSet) {
                if (Objects.equals(autowiredValue, clazz.getSimpleName())) {
                    return clazz;
                }
            }
            return null;
        }
    }
}
