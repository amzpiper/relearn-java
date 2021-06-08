package design.structmode.decorator;

/**
 * 使用 RedShapeDecorator 来装饰 Shape 对象。
 *
 * @author guoyh
 * @date 2021/03/02
 */
public class DecoratorPatternDemo {

    public static void main(String[] args) {
        Shape circle = new Circle();
        AbstractShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        AbstractShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("normal");
        circle.draw();

        System.out.println("redCircle");
        redCircle.draw();

        System.out.println("redRectangle");
        redRectangle.draw();
    }
}
