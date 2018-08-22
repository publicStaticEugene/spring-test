package com.spring.app1.config;

import com.spring.app1.logger.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.*;

//@Configuration
//@PropertySource(value = {
//        "classpath:props/client.properties",
//        "classpath:props/fileLogger.properties"})
public class AppConfig {

    @Bean
    @Autowired
    @Lazy
    public List<EventLogger> loggerList(final ConsoleEventLogger consoleEventLogger,
                                        final FileEventLogger fileEventLogger) {
        final List<EventLogger> loggers = new ArrayList<>();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    @Autowired
    @Lazy
    public Map<EventType, EventLogger> loggerMap(final ConsoleEventLogger consoleEventLogger,
                                                 final CombinedEventLogger combinedEventLogger) {
        final Map<EventType, EventLogger> loggers = new HashMap<>();
        loggers.put(EventType.INFO, consoleEventLogger);
        loggers.put(EventType.ERROR, combinedEventLogger);
        return loggers;
    }

    @Bean
    @Scope("prototype")
    public Date date() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getInstance();
    }
}
