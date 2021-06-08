package design.behaviourmode.observer;

/**
 * 观察者模式测试
 * 使用 Subject 和实体观察者对象
 *
 * @author guoyh
 * @date 2021/02/19
 */
public class ObserverPatternDemo {

    public static void main(String[] args) {
        //被观察者
        Subject subject = new Subject();

        //观察者
        new BinaryObserver(subject);

        //被观察者修改属性值
        System.out.println("First state change: 15");
        subject.setState(15);
    }
}
