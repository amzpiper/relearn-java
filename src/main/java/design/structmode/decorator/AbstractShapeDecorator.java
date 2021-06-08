package design.structmode.decorator;

/**
 * 实现了 Shape 接口的抽象装饰类。
 *
 * @author guoyh
 * @date 2021/03/02
 */
public abstract class AbstractShapeDecorator {
    protected Shape decoratedShape;

    public AbstractShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}
