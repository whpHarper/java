package parttern.creational.builder;

/**
 * @author whp 18-4-20
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "coke";
    }

    @Override
    public float price() {
        return (float) 12.3;
    }
}
