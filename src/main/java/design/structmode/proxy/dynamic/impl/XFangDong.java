package design.structmode.proxy.dynamic.impl;

import design.structmode.proxy.dynamic.TakeHouse;

/**
 * 真实类：房东
 *
 * @author guoyha
 * @date 2021/02/19
*/
public class XFangDong implements TakeHouse {
    @Override
    public void rentRoom() {
        System.out.println("向XFangDong租一间屋子");
    }
}
