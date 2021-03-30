package design.strategy;

/**
 * Context 是一个使用了某种策略的类
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
