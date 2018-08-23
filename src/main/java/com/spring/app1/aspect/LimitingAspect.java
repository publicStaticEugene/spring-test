package com.spring.app1.aspect;

import com.spring.app1.bean.Event;
import com.spring.app1.logger.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component("limitingAspect")
@Aspect
public class LimitingAspect {
    private final Integer MAX_CONSOLE_LOGGER_INVOCATION;
    private final EventLogger fileEventLogger;
    private Map<Class, AtomicInteger> statisticMap;

    @Autowired
    public LimitingAspect(@Value("${maxConsoleLoggerInvocation}")
                          final Integer MAX_CONSOLE_LOGGER_INVOCATION,
                          @Qualifier("fileEventLogger")
                          final EventLogger fileEventLogger) {
        this.MAX_CONSOLE_LOGGER_INVOCATION = MAX_CONSOLE_LOGGER_INVOCATION;
        this.fileEventLogger = fileEventLogger;
    }

    @Resource(name = "statisticMap")
    public void setStatisticMap(final Map<Class, AtomicInteger> statisticMap) {
        this.statisticMap = statisticMap;
    }

    @Pointcut("execution(* com.spring.app1.logger.ConsoleEventLogger.logEvent(..))")
    public void consoleLoggerInvocation() {
    }

    @Around("consoleLoggerInvocation() && args(event)")
    public void limitConsoleLoggerInvocation(final ProceedingJoinPoint joinPoint,
                                             final Event event) {
        final Class<?> consoleLoggerClass = joinPoint.getTarget().getClass();
        final int invocationCount = statisticMap
                .getOrDefault(consoleLoggerClass, new AtomicInteger(0)).get();

        if (invocationCount < MAX_CONSOLE_LOGGER_INVOCATION) {
            try {
                joinPoint.proceed(new Object[]{event});
            } catch (final Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            fileEventLogger.logEvent(event);
        }
    }
}
