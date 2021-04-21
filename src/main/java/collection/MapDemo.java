package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author guoyh
 */
public class MapDemo {
    public static void main(String[] args) {

        //高效通过key快速查找value,不保证顺序
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 123);
        map.put("pear", 123);
        System.out.println("map:"+map.get("pear"));
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(value);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        //HashMap之所以能根据key直接拿到value，原因是它内部通过空间换时间的方法，用一个大数组存储所有value
        //并根据key直接计算出value应该存储在哪个索引

        //我们放入Map的key是字符串"a"，但是，当我们获取Map的value时，传入的变量不一定就是放入的那个key对象。
        //在Map的内部，对key做比较是通过equals()实现的，这一点和List查找元素需要正确覆写equals()是一样的
        //即正确使用Map必须保证：作为key的对象必须正确覆写equals()方法。
        //如果我们放入的key是一个自己写的类，就必须保证正确覆写了equals()方法
        //key对象的hashCode()方法，它返回一个int整数。HashMap正是通过这个方法直接定位key对应的value的索引，继而直接返回value

        //正确使用Map必须保证：
        //1.作为key的对象必须正确覆写equals()方法，相等的两个key实例调用equals()必须返回true；
        //2.作为key的对象还必须正确覆写hashCode()方法，且hashCode()方法要严格遵循以下规范：
        //如果两个对象相等，则两个对象的hashCode()必须相等；
        //如果两个对象不相等，则两个对象的hashCode()尽量不要相等。
        //第一条规范是正确性，必须保证实现，否则HashMap不能正常工作
        //第二条如果尽量满足，则可以保证查询效率，因为不同的对象，如果返回相同的hashCode()，会造成Map内部存储冲突，使存取的效率下降。
        //正确编写equals()的方法我们已经在编写equals方法一节中讲过了
        //正确编写hashCode()
        //编写equals()和hashCode()遵循的原则是：
        //
        //equals()用到的用于比较的每一个字段，都必须在hashCode()中用于计算；
        //equals()中没有使用到的字段，绝不可放在hashCode()中计算。
        Map<Person2, String> map2 = new HashMap<>();
        map2.put(new Person2("guoyuhang",25), "apple");
        map2.put(new Person2("guoyuhang2",24), "apple2");
        System.out.println("map2:"+map2.get(new Person2("guoyuhang2", 24)));
        System.out.println("map2.containsKey:"+map2.containsKey(new Person2("guoyuhang2", 24)));

    }
}

class Person2 {
    public String name;
    public int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 正确编写equals
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Person2) {
            Person2 p = (Person2) o;
            System.out.println(Objects.equals(this.name, p.name) && this.age == p.age);
            return Objects.equals(this.name, p.name) && this.age == p.age;
        }
        return false;
    }

    /**
     * 正确实现equals()的基础上，我们还需要正确实现hashCode()，即上述3个字段分别相同的实例，hashCode()返回的int必须相同
     * String类已经正确实现了hashCode()方法，我们在计算Person的hashCode()时，
     * 反复使用31*h，这样做的目的是为了尽量把不同的Person实例的hashCode()均匀分布到整个int范围
     * 如果name为null，代码工作起来就会抛NullPointerException。
     * 为了解决这个问题，我们在计算hashCode()的时候，经常借助Objects.hash()来计算
     * equals()用到的用于比较的每一个字段，都必须在hashCode()中用于计算；
     * equals()中没有使用到的字段，绝不可放在hashCode()中计算。
     */
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + name.hashCode();
        hashCode = 31 * hashCode + age;
        System.out.println(hashCode);
        return hashCode;
//        System.out.println(Objects.hash(name, age));
//        return Objects.hash(name, age);
    }

}
