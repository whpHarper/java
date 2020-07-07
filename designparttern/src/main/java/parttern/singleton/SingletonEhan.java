package parttern.singleton;

/**
 * @Classname
 * @Describe TODO
 * @Date 2020/6/29
 * @Auth whp
 * @Version 1.0
 */
public class SingletonEhan {
    private static final SingletonEhan instance=new SingletonEhan();
    private SingletonEhan(){}
    public static SingletonEhan getInstance(){
        return instance;
    }
}
