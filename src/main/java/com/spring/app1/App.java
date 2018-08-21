package com.spring.app1;

import com.spring.app1.bean.Client;
import com.spring.app1.bean.Event;
import com.spring.app1.config.AppConfig;
import com.spring.app1.logger.EventLogger;
import com.spring.app1.logger.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("app")
public class App {
    private static AnnotationConfigApplicationContext context;

    private final Client client;
    private final EventLogger eventLogger;
    private final Map<EventType, EventLogger> loggers;

    @Autowired
    public App(final Client client,
               @Qualifier("cacheFileEventLogger") final EventLogger eventLogger,
               final Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(final String[] args) {
//        context = new ClassPathXmlApplicationContext("contexts/annotationconfig/spring-app-context.xml");
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.scan("com.spring.app1");

        for (String s : context.getBeanDefinitionNames()) {
            System.out.println(s);
        }
        final App app = context.getBean("app", App.class);

        app.logEvent("event for 1", EventType.INFO);
        app.logEvent("event for 2", EventType.ERROR);
        app.logEvent("event for 3", null);

        context.close();
    }

//    @Resource(name = "loggerMap")
//    public void setLoggers(final Map<EventType, EventLogger> loggers) {
//        this.loggers = loggers;
//    }

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
