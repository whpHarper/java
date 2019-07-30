package parttern.creational.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whp 18-4-20
 */
public class Meal {
    List<Item> items=new ArrayList<Item>();
    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost=0.0f;
        for(Item item:items){
            cost+=item.price();
        }
        return cost;
    }

    public void showItems(){
        for(Item item:items){
            System.out.println("item:"+item.name());
            System.out.println(",Packing:"+item.packing().pack());
            System.out.println(",price:"+item.price());
        }
    }
}
