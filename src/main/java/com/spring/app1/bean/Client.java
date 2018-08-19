package com.spring.app1.bean;

public class Client {
    private final int id;
    private final String fullName;

    public Client(final int id, final String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

}
