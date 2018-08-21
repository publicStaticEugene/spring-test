package com.spring.app1.logger;

import com.spring.app1.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("combinedEventLogger")
public class CombinedEventLogger implements EventLogger {
    @Resource(name = "loggerList")
    private final List<EventLogger> loggers;

    public CombinedEventLogger(final List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(final Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
