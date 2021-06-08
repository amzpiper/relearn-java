package design.behaviourmode.strategy;

import design.behaviourmode.strategy.impl.OperationAdd;
import design.behaviourmode.strategy.impl.OperationMultiply;
import design.behaviourmode.strategy.impl.OperationSubtract;

/**
 * 策略模式测试类
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 10 = "+ context.executeStrategy(10,10));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 10 = "+ context.executeStrategy(10,10));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 10 = "+ context.executeStrategy(10,10));
    }
}
