package parttern.decorator;

public class DemoTest {
    public static void main(String[] args) {
        RedBoradDecorator decorator=new RedBoradDecorator(new CircleShape());
        decorator.draw();
    }
}
