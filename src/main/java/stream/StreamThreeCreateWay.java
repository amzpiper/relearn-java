package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamThreeCreateWay {
    public static void main(String[] args) {
        // 方式一：通过 java.util.Collection.stream() 方法用集合创建流
        List<String> list = Arrays.asList("a", "b", "c");
        // 黄健一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();

        // 方式二：使用java.util.Arrays.stream(T[] array)方法用数组创建流
        int[] array = {1,2,3,4,5};
        IntStream stream2 = Arrays.stream(array);
        stream2.forEach(t -> System.out.print(t));

        // 方式三：使用Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> of = Stream.of(1,2,3,4,5);
        of.forEach(t -> System.out.print(t));

        Stream<Integer> iterate = Stream.iterate(0, (x) -> x+3).limit(2);
        iterate.forEach(System.out::println);

        Stream<Double> generate = Stream.generate(Math::random).limit(3);
        generate.forEach(System.out::println);
        
        
    }
}
