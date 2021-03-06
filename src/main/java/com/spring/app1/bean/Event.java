package com.spring.app1.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

@Component("event")
@Scope("prototype")
public class Event {
    private final int id;
    private final Date date;
    private final DateFormat df;
    private String msg;

    @Autowired
    public Event(final Date date, final DateFormat df) {
        this.id = new Random().nextInt();
        this.date = date;
        this.df = df;
    }

    public static boolean isDay() {
        final int start = 8;
        final int end = 17;
        final int now = LocalTime.now().getHour();
        return now >= start && now < end;
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
