package parttern.observer;

/**
 * @ClassName Observer
 * @Description TODO
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public abstract class Observer {
    protected Subject subject;
    public abstract void stateUpdate(Integer state);
}
