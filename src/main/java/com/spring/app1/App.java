package com.spring.app1;

import com.spring.app1.logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private final Client client;
    private final EventLogger eventLogger;

    public App(final Client client, final EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(final String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("spring-app-context.xml");
        final App app = context.getBean("app", App.class);

        app.logEvent("event for 1");
        app.logEvent("event for 2");
    }

    private void logEvent(final String msg) {
        final String message = msg.replaceAll(client.getId() + "", client.getFullName());
        eventLogger.logEvent(message);
    }
}
