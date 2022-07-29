package com.java.function_program.listener;

import java.util.function.Consumer;

public interface Event {

    default boolean canDispatchEvent(){
        return false;
    }

    default <T extends Event> void execute(Class<T> listenerClass, Consumer<T> event){}
}
