package parttern.decorator;

public class RedBoradDecorator extends ShapeDecorator{

    public RedBoradDecorator(Shape shape) {
        super(shape);
    }

    public void draw(){
        super.draw();
        this.addRedBord();
    }

    public void addRedBord(){
        System.out.println("add red board");
    }
}
