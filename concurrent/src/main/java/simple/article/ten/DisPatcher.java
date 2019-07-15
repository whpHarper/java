package simple.article.ten;

import java.util.HashSet;
import java.util.Set;

public class DisPatcher {
    private final Set<Taxi> taxiSet;
    private final Set<Taxi> arrivalTaxiSet;


    public DisPatcher() {
        taxiSet = new HashSet<Taxi>();
        arrivalTaxiSet=new HashSet<Taxi>();
    }

    public synchronized void notifyAvaliable(Taxi taxi){
        arrivalTaxiSet.add(taxi);
    }

}
