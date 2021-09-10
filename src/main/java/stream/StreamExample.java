package stream;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {
    
    public static void main(String[] args) {

        // 1 遍历/匹配（foreach/find/match）
        // Stream也是支持类似集合的遍历和匹配元素的，只是Stream中的元素是以Optional类型存在的。Stream的遍历、匹配非常简单。
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        Stream<Integer> stream =  list.stream().filter(x -> x > 6);
        stream.forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> optional = list.stream().findFirst();
        System.out.println("匹配第一个值："+optional.get());
        // 匹配任意（适用于并行流）
        Optional<Integer> optional2 = list.parallelStream().filter(x -> x > 6).findAny();
        System.out.println("匹配任意一个值："+optional2.get());
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x<6);
        System.out.println("是否存在大于6的值："+anyMatch);

        // 2 筛选（filter）
        // 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。
        // 案例一：筛选出Integer集合中大于7的元素，并打印出来
        System.out.println("案例一：筛选出Integer集合中大于7的元素，并打印出来");
        List<Integer> list2 = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        Stream<Integer> stream2 = list2.stream();
        stream2.filter(x -> x > 7).forEach(System.out::println);
        //案例二：筛选员工中工资高于8000的人，并形成新的集合。形成新集合依赖collect（收集），后文有详细介绍。
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        List<Person> filterPersonList = personList.stream().filter(x -> x.getSalary()>8000).collect(Collectors.toList());
        System.out.println("高于8000的员工姓名-List<Person>：");
        for (Person person : filterPersonList) {
            System.out.println(person.toString());
        }
        System.out.println("高于8000的员工姓名-List<String>");
        List<String> filterPersonNameList = personList.stream().filter(x -> x.getSalary()>8000).map(Person::getName).collect(Collectors.toList());
        filterPersonNameList.forEach(System.out::println);

        // 3 聚合（max/min/count)
        // max、min、count这些字眼你一定不陌生，没错，在mysql中我们常用它们进行数据统计。Java stream中也引入了这些概念和用法，极大地方便了我们对集合、数组的数据统计工作。
        // 案例一：获取String集合中最长的元素。
        List<String> list3 = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list3.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
        // Comparator.comparing(String::length)获取字符串长度后比较字符串之后用max选取最大长度的字符串
        // 案例二：获取Integer集合中的最大值。
        List<Integer> list4 = Arrays.asList(7, 6, 9, 4, 11, 6);
        Optional<Integer> max2 = list4.stream().max(Integer::compareTo);
        Optional<Integer> max3 = list4.stream().max(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            };
        });
        System.out.println("自然排序的最大值：" + max2.get());
        System.out.println("自定义排序的最大值：" + max3.get());
        // 案例三：获取员工工资最高的人。
        List<Person> personList2 = new ArrayList<Person>();
        personList2.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList2.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList2.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList2.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList2.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList2.add(new Person("Alisa", 7900, 26, "female", "New York"));
        Optional<Person> max4 = personList2.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值：" + max4.get().toString());
        // 案例四：计算Integer集合中大于6的元素的个数。
        List<Integer> list5 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list5.stream().filter(x -> x>6).count();
        System.out.println("list中大于6的元素个数：" + count);

        // 4 映射(map/flatMap)
        // 映射，可以将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap：
        // map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        // flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        // 案例一：英文字符串数组的元素全部改为大写,整数数组每个元素+3
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> strList = Arrays.asList(strArr).stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> strList2 = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素大写：" + strList2);

        List<Integer> intList = Arrays.asList(1,3,5,7,9,11);
        List<Integer> intListNew = intList.stream().map(x -> x+3).collect(Collectors.toList());
        System.out.println("每个元素+3：" + intListNew);
        //案例二：将员工的薪资全部增加1000。
        List<Person> personList3 = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        //不改变原先员工集合的方式
        List<Person> personNew1 = personList3.stream().map(person ->{
            Person personNew = new Person(person.getName(), person.getSalary()+1000), person, sex, area)
        }).
    }
}
