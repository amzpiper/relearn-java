package collection;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapDemo {
    public static void main(String[] args) {
        //Key必须实现Comparable接口。String、Integer这些类已经实现了Comparable接口
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String s : map.keySet()) {
            System.out.println("key:" + s);
            System.out.println("value:" + map.get(s));
        }

        //Person类并未覆写equals()和hashCode()，因为TreeMap不使用equals()和hashCode()
        //TreeMap在比较两个Key是否相等时，依赖Key的compareTo()方法或者Comparator.compare()方法。在两个Key相等时，必须返回0。
        Map<Person, Integer> map1 = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //顺序
                //return o1.name.compareTo(o2.name);
                //倒序
                return o2.name.compareTo(o1.name);
            }
        });
        map1.put(new Person("Tom",25), 1);
        map1.put(new Person("Bob",25), 2);
        map1.put(new Person("Lily",25), 3);
        for (Person person : map1.keySet()) {
            System.out.println("key:"+person.toString());
            System.out.println("value:"+map1.get(person));
        }
        
    }
}
