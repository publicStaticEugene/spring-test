package com.spring.app1.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private final int id;
    private final Date date;
    private final DateFormat df;
    private String msg;

    public Event(final Date date, final DateFormat df) {
        this.id = new Random().nextInt();
        this.date = date;
        this.df = df;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", df=" + df +
                ", msg='" + msg + '\'' +
                '}';
    }
}
