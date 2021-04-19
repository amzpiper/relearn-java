package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ListDemo {
    public static void main(String[] args) {
        //ArrayList先创建一个更大的新数组，然后把旧数组的所有元素复制到新数组，紧接着用新数组取代旧数组：
        List list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        System.out.println(list.size());

        //通过Iterator遍历List永远是最高效的方式。
        //for each循环本身就可以帮我们使用Iterator遍历
        //会自动把for each循环变成Iterator的调用
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            String s = (String) iterator.next();
            System.out.println(s);
        }
        //List.of()方法不接受null值，如果传入null，会抛出NullPointerException异常
        //get(int)方法只有ArrayList的实现是高效的，换成LinkedList后，索引越大，访问速度越慢。

        //List和Array转换
        //第一种是调用toArray()方法直接返回一个Object[]数组
        Object[] oArray = list.toArray();

        //第二种方式是给toArray(T[])传入一个类型相同的Array，List内部自动把元素复制到传入的Array中
        //如果传入的数组不够大，那么List内部会创建一个新的刚好够大的数组，填充后返回；如果传入的数组比List元素还要多，那么填充完元素后，剩下的数组元素一律填充null。
        List<Integer> list3 = new ArrayList<Integer>();
        Integer[] iArray = list3.toArray(new Integer[3]);
        //最常用的是传入一个“恰好”大小的数组：
        Integer[] iArray2 = list3.toArray(new Integer[list3.size()]);

        //最后一种更简洁的写法是通过List接口定义的T[] toArray(IntFunction<T[]> generator)方法：
        //Integer[] array = list3.toArray(Integer[]::new);
        //调用List.of()，它返回的是一个只读List

        //ist内部并不是通过==判断两个元素是否相等，而是使用equals()方法判断两个元素是否相等，
        //例如contains()方法可以实现如下
        System.out.println(list.contains("1"));
        //要正确使用List的contains()、indexOf()这些方法，放入的实例必须正确覆写equals()方法，
        //否则，放进去的实例，查找不到。我们之所以能正常放入String、Integer这些对象，
        //是因为Java标准库定义的这些类已经正确实现了equals()方法。
        List plist = new ArrayList<Person>();
        plist.add(new Person("guoyuhang", 25));
        plist.add(new Person("baobo", 24));
        System.out.println(plist.contains(new Person("guoyuhang", 25)));

        //如果不调用List的contains()、indexOf()这些方法，那么放入的元素就不需要实现equals()方法。
    }
}
class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            //return this.name.equals(p.name) && this.age == p.age;
            boolean nameEquals = false;
            if (this.name == null && p.name == null) {
                nameEquals = true;
            }
            if (this.name != null) {
                nameEquals = this.name.equals(p.name);
            }
            //return nameEquals && this.age == age;

            //Objects.equals:
            //return (a == b) || (a != null && a.equals(b));
            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }
}
