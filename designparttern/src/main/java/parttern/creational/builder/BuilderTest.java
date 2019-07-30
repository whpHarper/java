package parttern.creational.builder;

/**
 * @author whp 18-4-20
 */
public class BuilderTest {
    public static void main(String[] args){
        MealBuilder mealBuilder=new MealBuilder();
        System.out.println("Veg meal");
        Meal vegMeal=mealBuilder.prepareVegMeal();
        vegMeal.showItems();
        System.out.println("Total cost:"+vegMeal.getCost());

    }
}
