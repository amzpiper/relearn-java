## Stream

```
Stream将要处理的元素集合看作一种流，在流的过程中，借助Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。
```

Stream可以由数组或集合创建，对流的操作分为两种：
```
1.中间操作，每次返回一个新的流，可以有多个。
2.终端操作，每个流只能进行一次终端操作，终端操作结束后流无法再次使用。终端操作会产生一个新的集合或值。
```

另外，Stream有几个特性：
```
1.stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
2.stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
3.stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。
```

### Stream可以通过集合数组创建。
#### 1、通过 java.util.Collection.stream() 方法用集合创建流
```java
// 方式一：通过 java.util.Collection.stream() 方法用集合创建流
List<String> list = Arrays.asList("a", "b", "c");
// 黄健一个顺序流
Stream<String> stream = list.stream();
// 创建一个并行流
Stream<String> parallelStream = list.parallelStream();
```
#### 2、使用java.util.Arrays.stream(T[] array)方法用数组创建流
```
// 方式二：使用java.util.Arrays.stream(T[] array)方法用数组创建流
int[] array = {1,2,3,4,5};
IntStream stream2 = Arrays.stream(array);
stream2.forEach(t -> System.out.print(t));
```
#### 3、使用Stream的静态方法：of()、iterate()、generate()
```
// 方式三：使用Stream的静态方法：of()、iterate()、generate()
Stream<Integer> of = Stream.of(1,2,3,4,5);
of.forEach(t -> System.out.print(t));

Stream<Integer> iterate = Stream.iterate(0, (x) -> x+3).limit(2);
iterate.forEach(System.out::println);

Stream<Double> generate = Stream.generate(Math::random).limit(3);
generate.forEach(System.out::println);
```

