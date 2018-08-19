package com.spring.app1.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private final int id;
    private String msg;
    private final Date date;

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public Event(final Date date, final DateFormat df) {
        this.id = new Random().nextInt();
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
