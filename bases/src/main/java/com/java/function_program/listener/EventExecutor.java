package com.java.function_program.listener;

import com.java.util.ClassUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 事件执行器
 */
public class EventExecutor {
    //事件监听器，intf->list<? implements intf>
    private Map<Class<? extends Event>, List<Event>> listenerMap=new HashMap<>();

    public void addListener(Object cls){
        List<Class<?>> allInterfaces = ClassUtil.getAllInterfaces(cls.getClass());
        for(Class event:allInterfaces){
            if(Event.class.isAssignableFrom(event)) {
                List classes = listenerMap.get(event);
                if (classes == null) {
                    classes = new ArrayList<>();
                    listenerMap.put(event, classes);
                }
                if(!classes.contains(cls)){
                    classes.add(cls);
                }
            }
        }
    }

    public void remove(Object cls){
        for(Class key: listenerMap.keySet()){
            List<Event> events = listenerMap.get(key);
            events.remove(cls);
        }
    }

    public <T extends Event> void execute(Class<T> eventClass,Consumer<T> eventMethod){
        List<Event>  listeners= listenerMap.get(eventClass);
        for(Event listener:listeners){
            eventMethod.accept((T)listener);
            if(listener.canDispatchEvent()){
                listener.execute(eventClass,eventMethod);
            }
        }
    }

    /**
     * 有一个执行错误，则返回false
     * @param eventClass
     * @param eventMethod
     * @return
     * @param <T>
     */
    public <T extends Event> boolean execute(Class<T> eventClass, Function<T, Boolean> eventMethod){
        List<Event> listeners = listenerMap.get(eventClass);
        for(Event listener:listeners){
            Boolean apply = eventMethod.apply((T) listener);
            if(!apply){
                return false;
            }
        }
        return true;
    }
}
