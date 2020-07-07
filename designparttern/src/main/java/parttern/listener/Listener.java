package parttern.listener;

import java.util.EventListener;

public interface Listener extends EventListener {
    void eventHandler(EventObject eventObject);
}
