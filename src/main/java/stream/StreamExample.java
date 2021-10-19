package stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        // 元素全部改为大写
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> strList = Arrays.asList(strArr).stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> strList2 = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素大写：" + strList2);
        // 整数数组每个元素+3
        List<Integer> intList = Arrays.asList(1,3,5,7,9,11);
        List<Integer> intListNew = intList.stream().map(x -> x+3).collect(Collectors.toList());
        System.out.println("每个元素+3：" + intListNew);

        //案例二：将员工的薪资全部增加1000。
        List<Person> personList3 = new ArrayList<Person>();
        personList3.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList3.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList3.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList3.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList3.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList3.add(new Person("Alisa", 7900, 26, "female", "New York"));
        //不改变原先员工集合的方式
        List<Person> personNew1 = personList3.stream().map(person ->{
            Person personNew = new Person(
                person.getName(), 
                person.getSalary()+1000, 
                person.getAge(), 
                person.getSex(), 
                person.getArea()
            );
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("将员工的薪资全部增加1000：" + personNew1);
        // 改变原来员工集合的方式
        List<Person> personNew2 = personList3.stream().map(person ->{
            person.setSalary(person.getSalary()+1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("将员工的薪资全部增加1000：" + personNew2);

        //案例三：将两个字符数组合并成一个新的字符数组。
        List<String> list6 = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list6.stream().flatMap(s->{
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("将两个字符数组合并成一个新的字符数组:" + listNew);
        //两个字符数组合并成一个新的字符数组:[m, k, l, a, 1, 3, 5, 7]


        // 5 归约(reduce)
        // https://blog.csdn.net/zhang89xiao/article/details/77164866
        // 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        // T reduce(T identity, BinaryOperator<T> accumulator);
        //<U> U reduce(U identity,BiFunction<U, ? super T, U> accumulator,BinaryOperator<U> combiner);
        //第三个函数参数并没有执行
        //Stream是支持并发操作的，为了避免竞争，对于reduce线程都会有独立的result，combiner的作用在于合并每个线程的result得到最终结果。
        //这也说明了了第三个函数参数的数据类型必须为返回数据类型了。
        //需要注意的是，因为第三个参数用来处理并发操作，如何处理数据的重复性，应多做考虑，否则会出现重复数据

        // 案例一：求Integer集合的元素之和、乘积和最大值。
        System.out.println("案例一：求Integer集合的元素之和、乘积和最大值。");
        // 和
        List<Integer> list7 = Arrays.asList(1,2,3,4,5,6);
        Optional<Integer> sum = list7.stream().reduce( (x,y) -> x+y );
        System.out.println("sum:"+sum.get());
        Optional<Integer> sum2 = list7.stream().reduce(Integer::sum);
        System.out.println("sum2:"+sum2.get());
        Integer sum3 = list7.stream().reduce(0, Integer::sum);
        System.out.println("sum3:"+sum3);
        Integer sum4 = list7.stream().reduce(2, (x,y)->x+y);
        System.out.println("sum4:"+sum4);
        // 积
        Optional<Integer> product = list7.stream().reduce((x,y)-> x*y);
        System.out.println("product:"+product.get());
        Integer product2 = list7.stream().reduce(1,(x,y)-> x*y);
        System.out.println("product2:"+product2);
        // 最大值
        Optional<Integer> ma = list7.stream().reduce( (x,y) -> x>y ? x:y );
        System.out.println("max:"+ma.get());
        Integer ma2 = list7.stream().reduce(0,Integer::max);
        System.out.println("max2:"+ma2);

        // 案例二：求所有员工的工资之和和最高工资。
        System.out.println("案例二：求所有员工的工资之和和最高工资。");
        List<Person> personList4 = new ArrayList<Person>();
        personList4.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList4.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList4.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList4.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList4.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList4.add(new Person("Alisa", 7900, 26, "female", "New York"));
        // 求工资之和方式1：
        Optional<Integer> sumSalary1 = personList4.stream().map(Person::getSalary).reduce(Integer::sum);
        Optional<Integer> sumSalary11 = personList4.stream().map(Person::getSalary).reduce((acc,item)-> {
            acc += item;
            return acc;
        });
        System.out.println("求工资之和方式1："+sumSalary11.get());
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream().reduce(0, (salarySum, p) -> salarySum += p.getSalary(),
        (salarySum1, salarySum2) -> salarySum1 + salarySum2);
        System.out.println("求工资之和方式2："+sumSalary2);
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream().reduce(0, (salarySum, p) -> salarySum += p.getSalary(), Integer::sum);
        System.out.println("求工资之和方式3："+sumSalary3);

        // 求最高工资方式1：
        Integer maxSalary1 = personList.stream().reduce(0,(maxSalary, p) -> maxSalary>p.getSalary()?maxSalary:p.getSalary(),
        (maxSalary11, maxSalary12) -> maxSalary11>maxSalary12?maxSalary11:maxSalary12);
        System.out.println("求最高工资方式1："+maxSalary1);
        // 求最高工资方式2：
        Integer maxSalary2 = personList.stream().reduce(0,(maxSalary, p) -> maxSalary>p.getSalary()?maxSalary:p.getSalary(),
        Integer::sum);
        System.out.println("求最高工资方式2："+maxSalary2);


        //6 收集(collect)
        //collect，收集，可以说是内容最繁多、功能最丰富的部分了。
        //从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
        //collect主要依赖java.util.stream.Collectors类内置的静态方法

        //6.1归集(toList/toSet/toMap)
        //因为流不存储数据，那么在流中的数据完成处理后，需要将流中的数据重新归集到新的集合里。
        //toList、toSet和toMap比较常用，另外还有toCollection、toConcurrentMap等复杂一些的用法。
        //下面用一个案例演示toList、toSet和toMap：
        List<Integer> list8 = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> list8New = list8.stream().filter(x -> x%2 ==0 ).collect(Collectors.toList());
        System.out.println("toList:"+list8New);
        
        Set<Integer> set8New = list8.stream().filter(x -> x%2 == 0).collect(Collectors.toSet());
        System.out.println("toSet:"+set8New);

        List<Person> personList5 = new ArrayList<Person>();
        personList5.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList5.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList5.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList5.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList5.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList5.add(new Person("Alisa", 7900, 26, "female", "New York"));
        Map<String, Object> map = personList5.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toMap:" + map);
        for(Entry<String, Object> entry: map.entrySet()){
            System.out.println(entry.getKey() + "：" + entry.getValue().toString());
        }
        map.forEach((key,value)->{
            System.out.println(key + "：" + value);
        });
        System.out.println("toMap-key去重：");
        personList5.add(new Person("Anni2", 8200, 24, "female", "New York"));
        Map<Integer, Object> map2 = personList5.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getSalary, Function.identity(),(existing, replacement) -> existing));
        map2.forEach((key,value)->{
            System.out.println(key + "：" + value);
        });    
        System.out.println("toConcurrentMap：");
        Map<Integer, Object> map3 = personList5.stream().filter(p -> p.getSalary() > 8000).collect(Collectors.toMap(Person::getSalary, Function.identity(),(existing, replacement) -> existing,ConcurrentHashMap::new));
        map3.forEach((key,value)->{
            System.out.println(key + "：" + value);
        });
        System.out.println("toTreeMap：");
        Map<Integer, Object> map4 = personList5.stream().sorted(Comparator.comparing(Person::getSalary)).collect(Collectors.toMap(Person::getSalary, Function.identity(),(existing, replacement) -> existing,TreeMap::new));
        map4.forEach((key,value)->{
            System.out.println(key + "：" + value);
        });

        //6.2 统计(count/averaging)
        //Collectors提供了一系列用于数据统计的静态方法：
        // 计数：count
        // 平均值：averagingInt、averagingLong、averagingDouble
        // 最值：maxBy、minBy
        // 求和：summingInt、summingLong、summingDouble
        // 统计以上所有：summarizingInt、summarizingLong、summarizingDouble

        //案例：统计员工人数、平均工资、工资总额、最高工资。
        
    }
}
