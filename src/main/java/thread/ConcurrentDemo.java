package thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DELL
 */
public class ConcurrentDemo {
    public static void main(String[] args) {
        //我们在前面已经通过ReentrantLock和Condition实现了一个BlockingQueue：
        //
        //public class TaskQueue {
        //    private final Lock lock = new ReentrantLock();
        //    private final Condition condition = lock.newCondition();
        //    private Queue<String> queue = new LinkedList<>();
        //
        //    public void addTask(String s) {
        //        lock.lock();
        //        try {
        //            queue.add(s);
        //            condition.signalAll();
        //        } finally {
        //            lock.unlock();
        //        }
        //    }
        //
        //    public String getTask() {
        //        lock.lock();
        //        try {
        //            while (queue.isEmpty()) {
        //                condition.await();
        //            }
        //            return queue.remove();
        //        } finally {
        //            lock.unlock();
        //        }
        //    }
        //}
        //BlockingQueue的意思就是说，当一个线程调用这个TaskQueue的getTask()方法时，该方法内部可能会让线程变成等待状态，直到队列条件满足不为空，线程被唤醒后，getTask()方法才会返回。
        //
        //因为BlockingQueue非常有用，所以我们不必自己编写，可以直接使用Java标准库的java.util.concurrent包提供的线程安全的集合：ArrayBlockingQueue。
        //
        //除了BlockingQueue外，针对List、Map、Set、Deque等，java.util.concurrent包也提供了对应的并发集合类。我们归纳一下：
        //
        //interface	non-thread-safe	thread-safe
        //List	ArrayList	CopyOnWriteArrayList
        //Map	HashMap	ConcurrentHashMap
        //Set	HashSet / TreeSet	CopyOnWriteArraySet
        //Queue	ArrayDeque / LinkedList	ArrayBlockingQueue / LinkedBlockingQueue
        //Deque	ArrayDeque / LinkedList	LinkedBlockingDeque
        //使用这些并发集合与使用非线程安全的集合类完全相同。我们以ConcurrentHashMap为例：

        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("A", "1");
        concurrentHashMap.put("B", "2");
        concurrentHashMap.put("C", "3");

        //java.util.Collections工具类还提供了一个旧的线程安全集合转换器，可以这么用：
        Map unsafeMap = new HashMap();
        Map threadSafeMap = Collections.synchronizedMap(unsafeMap);
        //但是它实际上是用一个包装类包装了非线程安全的Map，然后对所有读写方法都用synchronized加锁，
        //这样获得的线程安全集合的性能比java.util.concurrent集合要低很多，所以不推荐使用。

        //小结
        //使用java.util.concurrent包提供的线程安全的并发集合可以大大简化多线程编程：
        //多线程同时读写并发集合是安全的；
        //尽量使用Java标准库提供的并发集合，避免自己编写同步代码。
    }
}
