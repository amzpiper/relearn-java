package collection;

import java.util.Deque;
import java.util.LinkedList;

//双端队列
//既可以添加到队尾，也可以添加到队首；
//既可以从队首获取，又可以从队尾获取。
//Deque接口实际上扩展自Queue
//Queue提供的add()/offer()方法在Deque中也可以使用，但是，使用Deque，最好不要调用offer()，而是调用offerLast()
//它的实现类有ArrayDeque和LinkedList
public class DequeDemo {
    public static void main(String[] args) {

        // 不推荐的写法:
        LinkedList<String> d1 = new LinkedList<>();
        d1.offerLast("z");
        // 推荐的写法：
        Deque<String> d2 = new LinkedList<>();
        d2.offerLast("z");
        //尽量持有接口，而不是具体的实现类
    }
}
