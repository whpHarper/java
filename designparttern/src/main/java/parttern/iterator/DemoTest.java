package parttern.iterator;

public class DemoTest {
    public static void main(String[] args) {
        PepoleContainer pepoleContainer = new PepoleContainer();
        Iterator iterator = pepoleContainer.iterator();
        while(iterator.hasNext()){
            System.out.println("name:"+iterator.next().toString());
        }
    }
}
