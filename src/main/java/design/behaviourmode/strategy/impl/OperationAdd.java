package design.behaviourmode.strategy.impl;

import design.behaviourmode.strategy.Strategy;

/**
 * 实现了 Strategy 接口的实体策略类
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class OperationAdd implements Strategy {

    /**
     * @param num1
     * @param num2
     * @return int
     */
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
