package parttern.creational.builder;

/**
 * @author whp 18-4-20
 */
public class VegBurger extends Burger{
    @Override
    public String name() {
        return "vegBurger";
    }

    @Override
    public float price() {
        return (float) 23.0;
    }
}
