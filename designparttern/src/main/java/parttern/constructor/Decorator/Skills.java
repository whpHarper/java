package parttern.constructor.Decorator;

/**
 * @author whp 18-4-13
 */
public class Skills implements Hero{
    private Hero hero;
    public Skills(Hero hero){
        this.hero=hero;
    }
    public void learnSkills() {
        if(hero!=null)
        hero.learnSkills();
    }
}
