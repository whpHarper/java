package parttern.creational.builder;

/**
 * @author whp 18-4-20
 */
public abstract class Burger implements Item{
    @Override
    public Packing packing(){
            return new Wrapper();
    }
    @Override
    public abstract float price();
}
