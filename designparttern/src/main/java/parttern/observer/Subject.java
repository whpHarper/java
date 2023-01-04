package parttern.observer;

import scala.Int;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Subject
 * @Description 观察者模式
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public class Subject {
    List<Observer> observerList=new ArrayList<>();
    private Integer state;

    public void setState(Integer state){
        this.state=state;
        this.update(state);
    }

    public void attach(Observer observer){
        observerList.add(observer);
    }

    public void update(Integer state){
        for(Observer observer:observerList){
            observer.stateUpdate(state);
        }
    }
}
