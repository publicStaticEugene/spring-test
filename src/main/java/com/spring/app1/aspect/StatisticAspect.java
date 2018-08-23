package com.spring.app1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component("statisticAspect")
@Aspect
public class StatisticAspect {

    @Resource(name = "statisticMap")
    private final Map<String, AtomicInteger> statisticMap;

    public StatisticAspect(final Map<String, AtomicInteger> statisticMap) {
        this.statisticMap = statisticMap;
    }

    @Pointcut("execution(* com.spring.app1.logger.*.logEvent(..))")
    public void allLoggerUsageStatistic() {
    }

    @After("allLoggerUsageStatistic()")
    public void successfulLogEventExecution(final JoinPoint joinPoint) {
        final Class<?> loggerClass = joinPoint.getTarget().getClass();
        final Component loggerClassAnnotation = loggerClass.getAnnotation(Component.class);
        final String annotationValue = loggerClassAnnotation.value();
        statisticMap.get(annotationValue).incrementAndGet();
    }

    @PreDestroy
    public void showStatistic() {
        statisticMap.forEach((logger, count) -> System.out.println(
                String.format("Usage of %s: %s", logger, count)));
    }
}
