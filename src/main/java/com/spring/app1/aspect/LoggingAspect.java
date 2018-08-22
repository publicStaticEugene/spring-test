package com.spring.app1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component("loggingAspect")
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..)) && " +
            "!within(com.spring.app1.App)")
    public void allLogEventMethods() {
    }

    @Before("allLogEventMethods()")
    public void beforeLog(final JoinPoint joinPoint) {
        System.out.println(String.format("BEFORE: %s %s",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()));
    }

    @After("allLogEventMethods()")
    public void afterLog(final JoinPoint joinPoint) {
        System.out.println(String.format("AFTER: %s %s\n",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName()));
    }
}
