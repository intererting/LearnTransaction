package com.yly.client.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yiliyang
 * @version 1.0
 * @date 2022/6/30 下午5:31
 * @since 1.0
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class MyAdviceO {

    @Pointcut("@annotation(com.yly.client.advice.MyAnnotation)")
    private void myPointCut() {

    }

    /**
     * 前置通知：在目标方法执行前调用
     */
    @Before("myPointCut()")
    public void begin() {
        log.info("MyAdviceO begin");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning(value = "myPointCut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info("MyAdviceO afterReturning");
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现异常都会在它之后调用
     */
    @After("myPointCut()")
    public void after() {
        log.info("MyAdviceO after");
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing(value = "myPointCut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        log.info("MyAdviceO afterThrowing");
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     */
    @Around("myPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        log.info("MyAdviceO around before");
        Object result = joinPoint.proceed();
        log.info("MyAdviceO around after");
        return result;
    }
}
