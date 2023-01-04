package parttern.decorator;

public abstract class ShapeDecorator implements Shape{
    protected Shape drawshape;
    protected ShapeDecorator(Shape shape){
        drawshape=shape;
    }
    public void draw(){
        drawshape.draw();
    }
}
