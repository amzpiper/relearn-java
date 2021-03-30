package design.observer;

/**
 * 实体类
 *
 * @author guoyh
 * @date 2021/02/19
 */
public class BinaryObserver extends AbstractObserver{

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println(
                this.getClass().getName()
                + "Change String: "
                + subject.getState()
        );
    }
}