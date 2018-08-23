package com.spring.app1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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

    private Map<Class, AtomicInteger> statisticMap;

    @Pointcut("execution(* com.spring.app1.logger.*.logEvent(..))")
    public void allLoggerUsageStatistic() {
    }

    @AfterReturning("allLoggerUsageStatistic()")
    public void successfulLogEventExecution(final JoinPoint joinPoint) {
        final Class<?> loggerClass = joinPoint.getTarget().getClass();

        if (!statisticMap.containsKey(loggerClass)) {
            statisticMap.put(loggerClass, new AtomicInteger(0));
        }

        statisticMap.get(loggerClass).incrementAndGet();
    }

    public Map<Class, AtomicInteger> getStatisticMap() {
        return statisticMap;
    }

    @Resource(name = "statisticMap")
    public void setStatisticMap(final Map<Class, AtomicInteger> statisticMap) {
        this.statisticMap = statisticMap;
    }

    @PreDestroy
    public void showStatistic() {
        statisticMap.forEach((logger, count) -> System.out.println(
                String.format("Usage of %s: %s", logger, count)));
    }
}
