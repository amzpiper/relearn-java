package refect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author GUOYHA
 */
public class ViewFieldAndMethodDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 1、获取Class
        Class stdClass = Student.class;
        // 获取public字段"score"
        System.out.println(stdClass.getField("score").getName());
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("age").getType());
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));

        // 2、Modifier
        int grade = stdClass.getDeclaredField("grade").getModifiers();
        System.out.println(Modifier.isPrivate(grade));

        // 3、获取字段值
        Person person = new Person("guoyuhang");
        // 获取class
        Class pClass = person.getClass();
        // 获取field
        Field field = pClass.getDeclaredField("name");
        // 获取指定实例的指定字段的值
        // 不出意外，会得到一个IllegalAccessException，这是因为name被定义为一个private字段，正常情况下，Main类无法访问Person类的private字段。
        // 修复错误，可以将private改为public，或者，在调用Object value = f.get(p);前写一句：f.setAccessible(true);意思是，别管这个字段是不是public，一律允许访问。
        // setAccessible(true) 并不是将方法的访问权限改成了public，而是取消java的权限控制检查。所以即使是public方法，其accessible 属相默认也是false
        field.setAccessible(true);
        Object value = field.get(person);
        System.out.println(value);

        // 反射是一种非常规的用法，使用反射，首先代码非常繁琐，其次，它更多地是给工具或者底层框架来使用，目的是在不知道目标实例任何信息的情况下，获取特定字段的值。
        // 某个SecurityManager可能不允许对java和javax开头的package的类调用setAccessible(true)，这样可以保证JVM核心库的安全。

        // 4、设置字段值
        // 设置字段值是通过Field.set(Object, Object)实现的，其中第一个Object参数是指定的实例，第二个Object参数是待修改的值。
        Person person2 = new Person("guoyuhang");
        Class pClass2 = person.getClass();
        Field field2 = pClass2.getDeclaredField("name");
        field2.setAccessible(true);
        field2.set(person2, "guoyuhang2");
        System.out.println(person2.getName());

        // 5、获取方法
        Person person3 = new Person("gyh");
        Class pClass3 = person3.getClass();
        // 获取所以public方法，包括父类
        System.out.println(pClass3.getMethod("setName", String.class));
        // 获取private方法，不包括父类
        System.out.println(pClass3.getDeclaredMethod("sayHello", String.class));

        // 6、Method
        Method setName = pClass3.getMethod("setName", String.class);
        Method sayHello = pClass3.getDeclaredMethod("sayHello", String.class);
        System.out.println(setName.getName());
        System.out.println(setName.getReturnType());
        System.out.println(setName.getParameterTypes());
        System.out.println(setName.getModifiers());
        System.out.println(Person.class.getDeclaredMethod("sayHello", String.class));

        // 7、调用public方法
        String s = new String("Hello World");
        Method method = String.class.getMethod("substring", int.class);
        String r = (String) method.invoke(s, 6);
        System.out.println(r);

        // 8、调用静态方法
        Method method1 = Integer.class.getMethod("parseInt", String.class);
        Integer n = (Integer) method1.invoke(null, "123");
        System.out.println(n);

        // 9、调用private方法
        Method sayHello1 = Person.class.getDeclaredMethod("sayHello", String.class);
        sayHello1.setAccessible(true);
        sayHello1.invoke(person3, "yuhang");

        // 10、调用多态方法
        // 使用反射调用方法时，仍然遵循多态原则：即总是调用实际类型的覆写方法（如果存在）.
        /**
         * 相当于:
         * Person p = new Student();
         * p.hello();
         */
        Person p = new Person("123");
        Method h = Person.class.getMethod("hello");
        h.invoke(p);
        h.invoke(new Student());

    }

}

class Student extends Person {
    public int score;
    private int grade;

    @Override
    public void hello() {
        System.out.println("Student:hello");
    }
}

class Person {
    private String name;
    public String age;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void sayHello(String msg) {
        System.out.println("Hello "+msg);
    }

    public void hello() {
        System.out.println("Student:hello");
    }
}
