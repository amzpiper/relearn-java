package com.proxy.statics.impl;

import com.proxy.statics.TakeHouse;

/**
 * 代理类：中介
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class ZhongJie implements TakeHouse {

    private TakeHouse takeHouse;

    /**
     * 默认是xFangDong的代理
     */
    public ZhongJie() {
        this.takeHouse = new XFangDong();
    }

    /**
     * 还可以是任何一个实现TakeHouse的房东的代理
     *
     * @param takeHouse
     */
    public ZhongJie(TakeHouse takeHouse) {
        this.takeHouse = takeHouse;
    }

    /**
     * 中介代理房东做租房子
     */
    @Override
    public void rentRoom() {
        System.out.println("中介代理处理");
        this.takeHouse.rentRoom();
        System.out.println("中介代理处理完成");
    }
}
