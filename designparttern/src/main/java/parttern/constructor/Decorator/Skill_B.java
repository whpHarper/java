package parttern.constructor.Decorator;

/**
 * @author whp 18-4-13
 */
public class Skill_B extends Skills{

    public Skill_B(Hero hero) {
        super(hero);
    }

    @Override
    public void learnSkills(){
        super.learnSkills();
        System.out.println("learn the skill_b");
    }
}
