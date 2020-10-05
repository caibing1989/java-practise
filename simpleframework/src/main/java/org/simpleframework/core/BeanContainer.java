package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.simpleframework.util.ValidationUtil.isEmpty;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-04
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {
    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 获取Bean容器实例
     *
     * @return
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    // 保存Class对象及其实例的载体
    /**
     * 存放所有被配置标记的目标对象的Map, key是class，value是通过class创建出来的实例
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    // 容器的加载
    // 配置的管理和获取
    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class);
    // 获取指定范围的class对象
    // 依据配置提取的class对象，连同实例一并存入容器

    /**
     * 扫描加载所有的bean
     */
    public synchronized void loadBeans(String packageName) {
        // 判断bean是否被加载过
        if (isLoaded()) {
            log.warn("BeanContainer has been loaded");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (isEmpty(classSet)) {
            log.warn("extract nothing from packageName");
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                // 如果类上面标记了自定义注解
                if (clazz.isAnnotationPresent(annotation)) {
                    // 将目标类本身作为键，目标类的实例作为值，放入到beanMap中
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     * 容器是否已经加载过bean
     */
    private boolean loaded = false;

    /**
     * 是否已经加载过bean
     *
     * @return
     */
    public boolean isLoaded() {
        return loaded;
    }

    // 容器的操作方式
    // 增加、删除操作

    /**
     * 添加一个class对象及其Bean实例
     *
     * @param clazz
     * @param bean
     * @return 原有的Bean实例，没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz
     * @return 删除的Bean实例，没有则返回null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    // 根据class获取对应实例

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }
    // 获取所有的class和实例

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return class集合
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();

    }

    /**
     * 获取所有Bean集合
     *
     * @return bean集合
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    // 通过注解来获取被注解标注的class

    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation 注解
     * @return class集合 为空时返回null
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        // 获取beanMap的所有class对象
        Set<Class<?>> classSet = getClasses();
        if (isEmpty(classSet)) {
            log.warn("nothing in beanMap");
            return null;
        }

        // 通过注解筛选被注解标记的class对象，并添加到classSet中
        Set<Class<?>> resultSet = new HashSet<>();
        resultSet = classSet.stream()
                .filter(e -> e.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());

        return resultSet.size() == 0 ? null : resultSet;
    }

    // 通过超类获取对应的子类

    /**
     * 通过接口或者父类获取实现类或者子类的class集合，不包括其本身
     *
     * @param interfaceOrClass
     * @return class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        // 获取beanMap的所有class对象
        Set<Class<?>> classSet = getClasses();
        if (isEmpty(classSet)) {
            log.warn("nothing in beanMap");
            return null;
        }

        // 判断classSet中的元素是否是interfaceOrClass的子类
        Set<Class<?>> resultSet = new HashSet<>();
        resultSet = classSet.stream()
                .filter(e -> e.isAssignableFrom(interfaceOrClass))
                .collect(Collectors.toSet());

        return resultSet.size() == 0 ? null : resultSet;
    }


    // 获取容器载体保存的class数量

    /**
     * Bean实例的数量
     *
     * @return
     */
    public int size() {
        return beanMap.size();
    }
}
