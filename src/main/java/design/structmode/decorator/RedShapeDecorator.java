package design.structmode.decorator;

/**
 * 扩展了 ShapeDecorator 类的实体装饰类。
 *
 * @author guoyh
 * @date 2021/03/02
 */
public class RedShapeDecorator extends AbstractShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    /**
     * 装饰
     * @param decoratorShape
     */
    private void setRedBorder(Shape decoratorShape) {
        System.out.println("Border Color: Red");
    }
}
