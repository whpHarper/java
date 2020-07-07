package parttern.singleton;

/**
 * @Classname SingletonLanh
 * @Describe 懒汉式单例模式
 * @Date 2020/6/29
 * @Auth whp
 * @Version 1.0
 */
public class SingletonLanh {
    private static volatile SingletonLanh instance;
    private SingletonLanh(){}
    public static SingletonLanh getSingletonLanh(){
        if(instance!=null){
            synchronized (SingletonLanh.class){
                instance=new SingletonLanh();
            }
        }
        return instance;
    }
}
