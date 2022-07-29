package com.java.function_program.announcement;

public class CenterAnnouncement implements IAnnouncement{
    @Override
    public void publish() {
        System.out.println("===中央发布公告==");
    }
}
