package com.cb.aspect;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.aspect.DefaultAspect;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-06
 */

@Slf4j
@Aspect(pointcut = "within(com.cb.controller.superadmin.*)")
@Order(10)
public class ControllerInfoRecordAspect extends DefaultAspect {
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("开始计时，执行的类是[{}]，执行的方法是[{}]，参数是[{}]", targetClass.getName(), method.getName(), args);
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        log.info("计时结束，执行的类是[{}]，执行的方法是[{}]，参数是[{}]，返回的值是[{}]",
                targetClass.getName(), method.getName(), args, returnValue);
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
        log.info("计时结束，执行的类是[{}]，执行的方法是[{}]，参数是[{}]，异常是[{}]",
                targetClass.getName(), method.getName(), args, e);
    }
}
