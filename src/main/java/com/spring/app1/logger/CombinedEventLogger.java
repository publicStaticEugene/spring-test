package com.spring.app1.logger;

import com.spring.app1.bean.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private final List<EventLogger> loggers;

    public CombinedEventLogger(final List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(final Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
