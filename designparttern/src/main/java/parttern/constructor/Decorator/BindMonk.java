package parttern.constructor.Decorator;

/**
 * @author whp 18-4-13
 */
public class BindMonk implements Hero{

    private String name;
    public BindMonk(String name){
        this.name=name;
    }

    public void learnSkills() {
        System.out.println(name+"学习如下技能");
    }
}
