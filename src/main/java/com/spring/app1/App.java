package com.spring.app1;

import com.spring.app1.bean.Client;
import com.spring.app1.bean.Event;
import com.spring.app1.logger.EventLogger;
import com.spring.app1.logger.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private static ConfigurableApplicationContext context;

    private final Client client;
    private final EventLogger eventLogger;
    private final Map<EventType, EventLogger> loggers;

    public App(final Client client,
               final EventLogger eventLogger,
               final Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(final String[] args) {
        context = new ClassPathXmlApplicationContext("spring-app-context.xml");
        final App app = context.getBean("app", App.class);

        app.logEvent("event for 1", EventType.INFO);
        app.logEvent("event for 2", EventType.ERROR);
        app.logEvent("event for 3", null);

        context.close();
    }

    private void logEvent(final String msg, final EventType eventType) {
        final String message = msg.replaceAll(client.getId() + "", client.getFullName());
        final Event event = context.getBean("event", Event.class);
        event.setMsg(message);

        if (eventType == null) {
            eventLogger.logEvent(event);
            return;
        }

        switch (eventType) {
            case INFO:
                loggers.get(EventType.INFO).logEvent(event);
                break;
            case ERROR:
                loggers.get(EventType.ERROR).logEvent(event);
                break;
        }
    }
}
