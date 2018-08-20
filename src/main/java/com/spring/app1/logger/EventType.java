package com.spring.app1.logger;

public enum EventType {

    /**
     * INFO event type means that {@link ConsoleEventLogger} will be used
     *
     * @see com.spring.app1.logger.ConsoleEventLogger
     */
    INFO,

    /**
     * ERROR event type means that {@link ConsoleEventLogger} and
     * {@link FileEventLogger} will be used
     *
     * @see com.spring.app1.logger.ConsoleEventLogger
     * @see com.spring.app1.logger.FileEventLogger
     */
    ERROR
}
