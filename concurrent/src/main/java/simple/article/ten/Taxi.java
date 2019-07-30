package simple.article.ten;

import java.awt.*;

public class Taxi {
    private Point location,destination;
    private final DisPatcher disPatcher;

    public Taxi(DisPatcher disPatcher) {
        this.disPatcher = disPatcher;
    }

    /**
     * 和车队类Dispatcher发生死锁
     * @param location
     */
    public synchronized void setLocation(Point location){
        this.location=location;
        if(location.equals(destination)){
            disPatcher.notifyAvaliable(this);
        }
    }


}
