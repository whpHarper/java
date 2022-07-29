package com.java.function_program.announcement;

import com.java.function_program.listener.EventExecutor;

public class AnnouncementTest {
    public static void main(String[] args) {
        EventExecutor eventExecutor=new EventExecutor();
        eventExecutor.addListener(new ProvinceAnnouncement());
        eventExecutor.addListener(new CenterAnnouncement());
        eventExecutor.execute(IAnnouncement.class,t->t.publish());
    }
}
