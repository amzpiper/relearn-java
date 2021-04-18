package refect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Java标准库提供了动态代理功能，允许在运行期动态创建一个接口的实例；
 * @author DELL
 */
public class ProxyRefectDemo {
    public static void main(String[] args) throws Throwable {
        Hello hello = new HelloWorld();
        hello.morning("Bob");

        // 仍然先定义了接口Hello，但是我们并不去编写实现类，
        // 而是直接通过JDK提供的一个Proxy.newProxyInstance()创建了一个Hello接口对象。
        // 这种没有实现类但是在运行期动态创建了一个接口对象的方式，我们称为动态代码
        Hello hello1 = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),// 传入ClassLoader
                new Class[]{Hello.class},    // 传入要实现的接口
                new InvocationHandler() {    // 传入处理调用方法的InvocationHandler
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method);
                        if (method.getName().equals("morning")) {
                            System.out.println("Good morning, " + args[0]);
                        }
                        return null;
                    }
                }
        );
        hello1.morning("guoyuhang");
        //在运行期动态创建一个interface实例的方法如下：
        //1.定义一个InvocationHandler实例，它负责实现接口的方法调用；
        //2.通过Proxy.newProxyInstance()创建interface实例，它需要3个参数：
        //2.1.使用的ClassLoader，通常就是接口类的ClassLoader；
        //2.1.需要实现的接口数组，至少需要传入一个接口进去；
        //2.1.用来处理接口方法调用的InvocationHandler实例。
        //3.将返回的Object强制转型为接口。
        //动态代理实际上是JVM在运行期动态创建class字节码并加载的过程


        InvocationHandler handler = new InvocationHandler() {    // 传入处理调用方法的InvocationHandler
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning2, " + args[0]);
                }
                return null;
            }
        };
        HelloDynamicProxy helloDynamicProxy = new HelloDynamicProxy(handler);
        helloDynamicProxy.morning("guoyuhang");
    }
}
class HelloWorld implements Hello {
    @Override
    public void morning(String name) {
        System.out.println("Good morning, " + name);
    }
}
interface Hello {
    void morning(String name) throws Throwable;
}

class HelloDynamicProxy implements Hello {
    InvocationHandler handler;

    public HelloDynamicProxy(InvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void morning(String name) throws Throwable {
        handler.invoke(
                this,
                Hello.class.getMethod("morning", String.class),
                new Object[]{name}
        );
    }
}