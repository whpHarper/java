package parttern.observer;

/**
 * @ClassName BinaryObserver
 * @Description TODO
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject=subject;
        subject.attach(this);
    }

    @Override
    public void stateUpdate(Integer state) {
        System.out.println("binary boject state:"+state);
    }
}
