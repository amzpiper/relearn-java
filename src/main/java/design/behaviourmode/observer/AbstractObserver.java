package design.behaviourmode.observer;

/**
 * 抽象类
 *
 * @author guoyh
 * @date 2021/02/19
 */
public abstract class AbstractObserver {

    /**
     * 被观察者
     */
    protected Subject subject;

    /**
     * 接收通知的方法
     * 当被观察者修改属性时调用该方法
     */
    public abstract void update();
}
