package com.spring.app1.logger;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(final String msg) {
        System.out.println(msg);
    }
}
