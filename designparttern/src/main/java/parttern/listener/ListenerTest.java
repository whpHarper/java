package parttern.listener;

/**
 * @Classname ListenerTest
 * @Describe TODO
 * @Date 2020/6/19
 * @Auth whp
 * @Version 1.0
 */
public class ListenerTest {
    public static void main(String[] args) {
        Runtime.getRuntime();
        Boolean boole;
        Sources sources=new Sources();
        sources.addListener( new Listener() {
            @Override
            public void eventHandler(parttern.listener.EventObject eventObject) {
                eventObject.doEvent();
                if(eventObject.getSource().equals( "close" )){
                    System.out.println("系统关闭");
                }
            }
        } );
        EventObject eventObject=new EventObject( "close" );
        sources.notifyListner( eventObject );
    }
}
