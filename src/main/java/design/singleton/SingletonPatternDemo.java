package design.singleton;

/**
 * SingletonPatternDemo class
 * 测试单例类
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {

        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();
        SingleObject object2 = SingleObject.getInstance2();

        //显示消息
        object.showMessage();
    }
}
