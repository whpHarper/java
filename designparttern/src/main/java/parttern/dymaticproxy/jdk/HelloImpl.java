package parttern.dymaticproxy.jdk;

/**
 * @author whp 19-7-18
 */
public class HelloImpl implements IHello{

    @Override
    public void sayHellow() {
        System.out.println("Hello World");
    }
}
