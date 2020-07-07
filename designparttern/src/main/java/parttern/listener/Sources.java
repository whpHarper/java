package parttern.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Sources
 * @Describe TODO
 * @Date 2020/6/19
 * @Auth whp
 * @Version 1.0
 */
public class Sources {
    List<Listener> listeners=new ArrayList<>(  );
    public void addListener(Listener listener){
        listeners.add( listener );
    }

    public void removeListener(Listener listener){
        listeners.remove( listener );
    }

    public void notifyListner(EventObject eventObject){
        for (Listener listener:listeners) {
            listener.eventHandler( eventObject );
        }
    }
}
