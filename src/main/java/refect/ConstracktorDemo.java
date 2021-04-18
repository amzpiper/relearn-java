package refect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstracktorDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        // 1、newInstance
        // 调用Class.newInstance()的局限是，它只能调用该类的public无参数构造方法。如果构造方法带有参数，或者不是public，就无法直接通过Class.newInstance()来调用。
        Person person = Person.class.newInstance();
        person.setName("guoyuhang");

        // 2、调用任意的构造方法，Java的反射API提供了Constructor对象，
        // 它包含一个构造方法的所有信息，可以创建一个实例。
        // Constructor对象和Method非常类似，不同之处仅在于它是一个构造方法，并且，调用结果总是返回实例
        Constructor constructor = Integer.class.getConstructor(int.class);
        Integer n = (Integer) constructor.newInstance(123);
        System.out.println(n);

        Constructor constructor1 = In.class.getDeclaredConstructor();
        constructor1.setAccessible(true);
        In in = (In) constructor1.newInstance();

        // 注意Constructor总是当前类定义的构造方法，和父类无关，因此不存在多态的问题。
    }
}

class In {
    private In() {
        System.out.println("private In");
    }

}