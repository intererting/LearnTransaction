package com.yly.client.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
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
@Order(2)
/**
 * 正常流程
 * 2022-06-30 17:40:08.152  INFO 31661 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice around before
 * 2022-06-30 17:40:08.152  INFO 31661 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice begin
 * 2022-06-30 17:40:08.155  INFO 31661 --- [nio-8082-exec-2] c.y.c.service.impl.LocalServiceImpl      : aop function invoke
 * 2022-06-30 17:40:08.155  INFO 31661 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice afterReturning
 * 2022-06-30 17:40:08.157  INFO 31661 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice after
 * 2022-06-30 17:40:08.157  INFO 31661 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice around after
 *
 *
 * 有异常流程
 * 2022-06-30 17:42:08.619  INFO 32069 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice around before
 * 2022-06-30 17:42:08.619  INFO 32069 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice begin
 * 2022-06-30 17:42:08.622  INFO 32069 --- [nio-8082-exec-2] c.y.c.service.impl.LocalServiceImpl      : aop function invoke
 * 2022-06-30 17:42:08.623  INFO 32069 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice afterThrowing
 * 2022-06-30 17:42:08.623  INFO 32069 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice after
 *
 * 多个,Order越小,优先级越高,事务优先级最低
 * 2022-06-30 17:47:41.720  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdviceO          : MyAdviceO around before
 * 2022-06-30 17:47:41.720  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdviceO          : MyAdviceO begin
 * 2022-06-30 17:47:41.720  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice around before
 * 2022-06-30 17:47:41.721  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice begin
 * 2022-06-30 17:47:41.723  INFO 464 --- [nio-8082-exec-2] c.y.c.service.impl.LocalServiceImpl      : aop function invoke
 * 2022-06-30 17:47:41.724  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice afterReturning
 * 2022-06-30 17:47:41.724  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice after
 * 2022-06-30 17:47:41.724  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdvice           : MyAdvice around after
 * 2022-06-30 17:47:41.724  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdviceO          : MyAdviceO afterReturning
 * 2022-06-30 17:47:41.724  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdviceO          : MyAdviceO after
 * 2022-06-30 17:47:41.724  INFO 464 --- [nio-8082-exec-2] com.yly.client.advice.MyAdviceO          : MyAdviceO around after
 *
 */
public class MyAdvice {

    @Pointcut("@annotation(com.yly.client.advice.MyAnnotation)")
    private void myPointCut() {

    }

    /**
     * 前置通知：在目标方法执行前调用
     */
    @Before("myPointCut()")
    public void begin() {
        log.info("MyAdvice begin");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning(value = "myPointCut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info("MyAdvice afterReturning");
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现异常都会在它之后调用
     */
    @After("myPointCut()")
    public void after() {
        log.info("MyAdvice after");
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing(value = "myPointCut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        log.info("MyAdvice afterThrowing");
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
        log.info("MyAdvice around before");
        Object result = joinPoint.proceed();
        log.info("MyAdvice around after");
        return result;
    }
}
