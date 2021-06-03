package design.singleton;

/**
 * SingleObject class
 * 单例类
 *
 * @author guoyha
 * @date 2021/02/19
 */
public class SingleObject {

    //创建 SingleObject 的一个对象
    //饿汉式
    private static SingleObject INSTANCE = new SingleObject();
    private int number;
    
    /**
     * 让构造函数为 private，这样该类就不会被实例化
     */
    private SingleObject() {
    }

    /**
     * 方式一、饿汉式
     * 获取唯一可用的对象
     *
     * @return SingleObject
     */
    public static SingleObject getInstance() {
        return INSTANCE;
    }

    /**
     * 获取唯一可用的对象
     * 懒汉式
     *
     * @return SingleObject
     */
    public static SingleObject getInstance2() {
        if (INSTANCE == null) {
            INSTANCE = new SingleObject();
            return INSTANCE;
        }else {
            return INSTANCE;
        }
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }

    public void setNumber(int n){
        this.number = n;
    }
    
    public int getNumber(){
        return this.number;
    }
}
