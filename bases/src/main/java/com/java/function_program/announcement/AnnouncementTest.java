package com.java.function_program.announcement;

import com.java.function_program.listener.EventExecutor;

import java.util.function.Consumer;

public class AnnouncementTest {
    public static void main(String[] args) {
        EventExecutor eventExecutor=new EventExecutor();
        eventExecutor.addListener(new ProvinceAnnouncement());
        eventExecutor.addListener(new CenterAnnouncement());
        eventExecutor.execute(IAnnouncement.class, (Consumer<IAnnouncement>) t->t.publish());
        StringBuilder builder=new StringBuilder("sss");
        builder.delete(10,20);
    }
}
