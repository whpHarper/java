package parttern.creational.builder;

/**
 * @author whp 18-4-20
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "chickenBurger";
    }

    @Override
    public float price() {
        return (float) 12.0;
    }
}
