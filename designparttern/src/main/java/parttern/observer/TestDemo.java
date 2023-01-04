package parttern.observer;

/**
 * @ClassName TestDemo
 * @Description TODO
 * @Author whp
 * @Date 2022/10/26
 * @Version 1.0
 **/
public class TestDemo {
    public static void main(String[] args) {
        Subject subject=new Subject();
        BinaryObserver binaryObserver=new BinaryObserver(subject);
        DicimalObserver dicimalObserver=new DicimalObserver(subject);
        subject.setState(10);
    }
}
