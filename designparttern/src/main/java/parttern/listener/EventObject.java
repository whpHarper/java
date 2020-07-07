package parttern.listener;

/**
 * @Classname EventObject
 * @Describe TODO
 * @Date 2020/6/19
 * @Auth whp
 * @Version 1.0
 */
public class EventObject extends java.util.EventObject {
    public EventObject(Object source) {
        super( source );
    }
    public void doEvent(){
        System.out.println("触发安全事件"+this.source.toString());
    }
}
