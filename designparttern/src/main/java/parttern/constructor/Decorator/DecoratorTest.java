package parttern.constructor.Decorator;

/**
 * 建造者模式，实例是动态给英雄添加技能
 * 新添加技能时，先添加技能实现类，然后在main方法中new该技能
 * @author whp 18-4-13
 */
public class DecoratorTest {
    public static void main(String[] args){
        Hero hero=new BindMonk("whp");
        Skills a= new Skill_A(hero);
        Skills b=new Skill_B(a);
        b.learnSkills();
    }
}
