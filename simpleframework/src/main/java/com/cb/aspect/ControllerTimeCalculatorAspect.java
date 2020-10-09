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
@Aspect(pointcut = "execution(* com.cb.controller.frontend..*.*(..))")
@Order(0)
public class ControllerTimeCalculatorAspect extends DefaultAspect {
    private long timestampCache;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("开始计时，执行的类是[{}]，执行的方法是[{}]，参数是[{}]", targetClass.getName(), method.getName(), args);
        timestampCache = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();
        long costTime = endTime - timestampCache;
        log.info("计时结束，执行的类是[{}]，执行的方法是[{}]，参数是[{}]，返回的值是[{}]，时间是[{}]",
                targetClass.getName(), method.getName(), args, returnValue, costTime);
        return returnValue;
    }
}
