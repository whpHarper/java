package parttern.creational.builder;

/**
 * @author whp 18-4-20
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "pepsi";
    }

    @Override
    public float price() {
        return (float) 11.0;
    }
}
