package org.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Description: 定义供外部使用的横切逻辑骨架
 * @Author: mtdp
 * @Date: 2020-10-06
 */

public abstract class DefaultAspect {
    /**
     * 事前拦截
     *
     * @param targetClass 被代理的目标类
     * @param method      被代理的目标方法
     * @param args        被代理的目标方法对应的参数列表
     * @throws Throwable
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {

    }

    /**
     * 事后拦截
     *
     * @param targetClass 被代理的目标类
     * @param method      被代理的目标方法
     * @param args        被代理的目标方法对应的参数列表
     * @param returnValue 被代理的目标方法执行后的返回值
     * @return
     * @throws Throwable
     */
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        return returnValue;
    }

    /**
     * 抛出异常后的事后拦截
     *
     * @param targetClass 被代理的目标类
     * @param method      被代理的目标方法
     * @param args        被代理的目标方法对应的参数列表
     * @param e           被代理的目标方法执行后抛出的异常
     * @throws Throwable
     */
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {

    }
}
