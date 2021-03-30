package design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @author guoyh
 * @date 2021/02/19
 */
public class Subject {

    /**
     * 存储所有观察者
     */
    private List<AbstractObserver> observers = new ArrayList<AbstractObserver>();
    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    /**
     * 添加观察者
     * @param observer
     */
    public void addObserver(AbstractObserver observer) {
        observers.add(observer);
    }

    /**
     * 通知
     */
    public void notifyAllObservers() {
        for (AbstractObserver observer : observers) {
            observer.update();
        }
    }
}
