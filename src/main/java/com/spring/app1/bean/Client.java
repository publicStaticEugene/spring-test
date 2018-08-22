package com.spring.app1.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("client")
public class Client {
    private final int id;
    private final String fullName;
    private String greeting;

    public Client(@Value("${id}") final int id,
                  @Value("${name}") final String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Value("${greeting} #{systemEnvironment['USERNAME']} #{systemProperties['user.dir']}")
    public void setGreeting(final String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

}
