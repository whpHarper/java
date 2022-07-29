package com.java.function_program.announcement;

import com.java.function_program.listener.Event;

import java.util.function.Consumer;

public class ProvinceAnnouncement implements IAnnouncement{


    @Override
    public void publish() {
        System.out.println("==省级公告==");
    }

    @Override
    public boolean canDispatchEvent() {
        return true;
    }

    @Override
    public <T extends Event> void execute(Class<T> listenerClass, Consumer<T> event) {
        if(listenerClass.isAssignableFrom(IAnnouncement.class)){
            event.accept((T)this);
        }
    }
}
