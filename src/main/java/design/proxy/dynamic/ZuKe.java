package design.proxy.dynamic;

import design.proxy.dynamic.impl.XFangDong;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ZuKe class
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class ZuKe {
    public static void main(String[] args) {
        //是否保存生成的代理类class文件，默认false不保存
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //实体类
        TakeHouse takeHouse = new XFangDong();
        //代理工具
        InvocationHandler handler = new ProxyHandler(takeHouse);
        //代理类
        TakeHouse proxyTakeHouse = (TakeHouse) Proxy.newProxyInstance(takeHouse.getClass().getClassLoader(), takeHouse.getClass().getInterfaces(), handler);
        proxyTakeHouse.rentRoom();

    }
}
