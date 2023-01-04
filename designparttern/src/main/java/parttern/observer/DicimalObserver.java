package parttern.observer;

/**
 * @ClassName DicimalObserver
 * @Description TODO
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public class DicimalObserver extends Observer{
    public DicimalObserver(Subject subject){
        this.subject=subject;
        subject.attach(this);
    }
    @Override
    public void stateUpdate(Integer state) {
        System.out.println("DecimalObject state:"+state);
    }
}
