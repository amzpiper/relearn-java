package design.proxy.statics;

import design.proxy.statics.impl.ZhongJie;

/**
 * 租客
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class ZuKe {
    public static void main(String[] args) {
        ZhongJie zhongJie = new ZhongJie();
        zhongJie.rentRoom();
    }
}
