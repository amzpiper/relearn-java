package design.behaviourmode.strategy;

/**
 * 活动的 Strategy 接口
 *
 * @author guoyha
 * @date 2021/02/19
 */
public interface Strategy {

    /**
     * 策略方法
     * @param num1
     * @param num2
     * @return int
     */
    public int doOperation(int num1, int num2);
}
