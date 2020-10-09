package org.simpleframework.aop;

import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.AspectInfo;
import org.simpleframework.aop.aspect.DefaultAspect;
import org.simpleframework.core.BeanContainer;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

import static org.simpleframework.util.ValidationUtil.isEmpty;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-06
 */

public class AspectWeaver {
    private BeanContainer beanContainer;

    public AspectWeaver() {
        this.beanContainer = BeanContainer.getInstance();
    }

    /**
     * 该方法已废弃，请使用doAop()
     */
    @Deprecated
    public void doAopReleaseOne() {
        // 1、获取所有的切面类
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
        // 2、将切面类按照不同的织入目标进行切分 key=待切入的目标，value=织入目标的aspect列表
        Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap = new HashMap<>();
        if (isEmpty(aspectSet)) {
            return;
        }
        for (Class<?> aspectClass : aspectSet) {
            if (verifyAspectReleaseOne(aspectClass)) {
                categorizedAspect(categorizedMap, aspectClass);
            } else {
                throw new RuntimeException("@Aspect and @Order have not been added to the Aspect class, " +
                        "or Aspect class does not extend from DefaultAspect.class, " +
                        "or the value in Aspect Tag equals @Aspect.");
            }
        }
        // 3、按照不同的织入目标分别去按序织入Aspect的逻辑
        if (isEmpty(categorizedMap)) {
            return;
        }
        for (Class<? extends Annotation> category : categorizedMap.keySet()) {
            weaveByCategory(category, categorizedMap.get(category));
        }
    }

    public void doAop() {
        // 1、获取所有的切面类
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
        if (isEmpty(aspectSet)) {
            return;
        }
        // 2、拼装AspectInfoList
        List<AspectInfo> aspectInfoList = packAspectInfoList(aspectSet);
        // 3、遍历容器里的类
        Set<Class<?>> classSet = beanContainer.getClasses();
        for (Class<?> targetClass : classSet) {
            // 排除Aspect.Class自身，否则会造成死循环
            if (targetClass.isAnnotationPresent(Aspect.class)) continue;
            // 4、粗筛符合条件的Aspect
            List<AspectInfo> roughMatchedAspectList
                    = collectRoughMatchedAspectListForSpecificClass(aspectInfoList, targetClass);
            // 5、尝试进行Aspect的织入
            wrapIfNecessary(roughMatchedAspectList, targetClass);
        }
    }

    private void wrapIfNecessary(List<AspectInfo> roughMatchedAspectList, Class<?> targetClass) {
        if (isEmpty(roughMatchedAspectList)) return;
        // 创建动态代理对象
        AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, roughMatchedAspectList);
        Object proxyBean = ProxyCreator.createProxy(targetClass, aspectListExecutor);
        // 将动态代理对象实例添加到容器里，取代未被代理前的类实例
        beanContainer.addBean(targetClass, proxyBean);
    }

    private List<AspectInfo> collectRoughMatchedAspectListForSpecificClass(List<AspectInfo> aspectInfoList, Class<?> targetClass) {
        return aspectInfoList.stream()
                .filter(aspectInfo -> aspectInfo.getPointcutLocator().roughMatches(targetClass))
                .collect(Collectors.toList());
    }

    private List<AspectInfo> packAspectInfoList(Set<Class<?>> aspectSet) {
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        for (Class<?> aspectClass : aspectSet) {
            if (verifyAspect(aspectClass)) {
                Order orderTag = aspectClass.getAnnotation(Order.class);
                Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
                DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
                // 初始化表达式定位器
                PointcutLocator pointcutLocator = new PointcutLocator(aspectTag.pointcut());
                AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect, pointcutLocator);
                aspectInfoList.add(aspectInfo);
            } else {
                throw new RuntimeException("@Aspect and @Order have not been added to the Aspect class, " +
                        "or Aspect class does not extend from DefaultAspect.class");
            }
        }
        return aspectInfoList;
    }

    private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfoList) {
        // 1、获取被代理类的集合
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
        if (isEmpty(classSet)) return;
        // 2、遍历被代理类，分别为每个被代理类生成动态代理实例
        for (Class<?> targetClass : classSet) {
            // 创建动态代理对象
            AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, aspectInfoList);
            Object proxyBean = ProxyCreator.createProxy(targetClass, aspectListExecutor);
            // 3、将动态代理对象实例添加到容器里，取代未被代理前的类实例
            beanContainer.addBean(targetClass, proxyBean);
        }
    }

    /**
     * 将切面类按照不同的织入目标进行切分
     *
     * @param categorizedMap
     * @param aspectClass
     */
    private void categorizedAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap, Class<?> aspectClass) {
        Order orderTag = aspectClass.getAnnotation(Order.class);
        Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
        DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
        AspectInfo aspectInfo = new AspectInfo();
        aspectInfo.setOrderIndex(orderTag.value());
        aspectInfo.setAspectObject(aspect);

        if (!categorizedMap.containsKey(aspectTag.value())) {
            // 如果织入的joinPoint第一次出现，则以该joinPoint为key，以新创建的List<AspectInfo>为value
            List<AspectInfo> aspectInfoList = new ArrayList<>();
            aspectInfoList.add(aspectInfo);
            categorizedMap.put(aspectTag.value(), aspectInfoList);
        } else {
            // 如果织入的joinPoint不是第一次出现，则往joinPoint对应的value中添加新的Aspect逻辑
            List<AspectInfo> aspectInfoList = categorizedMap.get(aspectTag.value());
            aspectInfoList.add(aspectInfo);
        }
    }

    /**
     * 该方法主要是来验证aspectClass是否满足aop框架的要求
     * 框架中一定要遵循给aspect类添加@Aspect和@Order标签的规范，同时必须继承来自DefaultAspect.class
     * 此外@Aspect的属性值不能是它本身，否则针对切面去做切面逻辑，会陷入死循环中
     *
     * @param aspectClass
     * @return
     */
    @Deprecated
    private boolean verifyAspectReleaseOne(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass) &&
                !Objects.equals(aspectClass.getAnnotation(Aspect.class).value(), Aspect.class);
    }


    /**
     * 该方法主要是来验证aspectClass是否满足aop框架的要求
     * 框架中一定要遵循给aspect类添加@Aspect和@Order标签的规范，同时必须继承来自DefaultAspect.class
     *
     * @param aspectClass
     * @return
     */
    private boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass);
    }

}
