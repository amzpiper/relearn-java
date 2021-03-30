package design.proxy.statics.impl;

import design.proxy.statics.TakeHouse;

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
