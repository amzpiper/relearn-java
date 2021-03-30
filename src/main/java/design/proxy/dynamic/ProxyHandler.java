package design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 利用反射机制在运行时创建代理类
 * 动态代理类
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class ProxyHandler implements InvocationHandler {
    private Object object;

    public ProxyHandler() {

    }

    public ProxyHandler(Object object) {
        this.object = object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理处理"+method.getName());
        method.invoke(object, args);
        System.out.println("代理处理完成"+method.getName());
        return null;
    }
}
