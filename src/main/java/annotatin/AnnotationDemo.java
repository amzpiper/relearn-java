package annotatin;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        // 第一类是由编译器使用的注解，例如：
        // 这类注解不会被编译进入.class文件，它们在编译后就被编译器扔掉了。

        // 第二类是由工具处理.class文件使用的注解，比如有些工具会在加载class的时候，对class做动态修改，实现一些特殊的功能。
        // 加载结束后并不会存在于内存中。这类注解只被一些底层库使用，一般我们不必自己处理。

        // 第三类是在程序运行期能够读取的注解，它们在加载后一直存在于JVM中，这也是最常用的注解。
        // 例如，一个配置了@PostConstruct的方法会在调用构造方法后自动被调用
        // 这是Java代码读取该注解实现的功能，JVM并不会识别该注解。

        // 定义一个注解时，还可以定义配置参数。配置参数可以包括：
        //      所有基本类型；
        //      String；
        //      枚举类型；
        //      基本类型、String、Class以及枚举的数组。
        // 因为配置参数必须是常量，所以，上述限制保证了注解在定义时就已经确定了每个参数的值。
        // 注解的配置参数可以有默认值，缺少某个配置参数时将使用默认值
        // 大部分注解会有一个名为value的配置参数，对此参数赋值，可以只写常量，相当于省略了value参数。
        // 只写注解，相当于全部使用默认值。

        // 定义注解
        @Check(min=0, max=100, value=55)
        int n;

        @Check(value=99)
        int p;

        @Check(99) // @Check(value=99)
        int x;

        @Check
        int y;

        //处理注解
        //只讨论如何读取RUNTIME类型的注解。
        //读取注解，需要使用反射API。
        // 判断@Target是否存在于Person类:
        System.out.println(Person.class.isAnnotationPresent(Check.class));
        //方法一是先判断Annotation是否存在，如果存在，就直接读取：
        Check check = Person.class.getAnnotation(Check.class);

        //读取方法参数的注解
        //因为方法参数本身可以看成一个数组，而每个参数又可以定义多个注解，
        //所以，一次获取方法参数的所有注解就必须用一个二维数组来表示。
        Method method = Person.class.getMethod("hello",String.class,String.class);
        //获取所有参数的Annotation:
        Annotation[][] annotations = method.getParameterAnnotations();
        //第一个参数（索引为0）的所有Annotation:
        Annotation[] fieldAnnotations = annotations[0];
        for (Annotation fieldAnnotation : fieldAnnotations) {
            System.out.println(fieldAnnotation);
        }
        Annotation[] fieldAnnotations2 = annotations[1];
        for (Annotation fieldAnnotation : fieldAnnotations2) {
            System.out.println(fieldAnnotation);
        }

        //使用Range注解
        //定义了注解，本身对程序逻辑没有任何影响。我们必须自己编写代码来使用注解。
        //编写一个Person实例的检查方法，它可以检查Person实例的String字段长度是否满足@Range的定义：
        Person person = new Person("guoyha", "beijing");
        check(person);
    }

    static void check(Person person) {
        // 遍历所有Field:
        for (Field field : person.getClass().getFields()) {
            // 获取Field定义的@Range:
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                //获取person的field值
                try {
                    Object value = field.get(person);
                    if (value instanceof String) {
                        String s = (String) value;
                        // 判断值是否满足@Range的min/max
                        if (s.length() < range.min() || s.length() > range.max()) {
                            throw new IllegalArgumentException("Invalid field: " + field.getName());
                        }else{
                            System.out.println("field: "+field.getName()+" is ok:");
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

@Check()
class Person {
    //使用Range注解
    @Range(min=1, max=20)
    public String name;

    @Range(max=10)
    public String city;

    public Person() {

    }

    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void hello(@NotNull @Check(max=5) String name, @NotNull String prefix) {

    }

}
