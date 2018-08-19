package com.spring.app1;

public class App {
    private static Client client;
    private static ConsoleEventLogger eventLogger;

    public static void main(String[] args) {
        App app = new App();

        client = new Client(1, "client 1");
        eventLogger = new ConsoleEventLogger();

        app.logEvent("event for 1");
        app.logEvent("event for 2");
    }

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId() + "", client.getFullName());
        eventLogger.logEvent(message);
    }
}
