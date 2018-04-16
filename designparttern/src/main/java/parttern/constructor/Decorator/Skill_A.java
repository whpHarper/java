package parttern.constructor.Decorator;

/**
 * @author whp 18-4-13
 */
public class Skill_A extends Skills{

    public Skill_A(Hero hero) {
        super(hero);
    }

    @Override
    public void learnSkills(){
        super.learnSkills();
        System.out.println("learn the skill_a");
    }
}
