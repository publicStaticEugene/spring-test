package com.spring.app1.bean;

public class Client {
    private final int id;
    private final String fullName;
    private String greeting;

    public Client(final int id, final String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(final String greeting) {
        this.greeting = greeting;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

}
