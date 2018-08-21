package com.spring.app1.logger;

import com.spring.app1.bean.Event;
import org.springframework.stereotype.Component;

@Component("consoleEventLogger")
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(final Event event) {
        System.out.println(event);
    }
}
