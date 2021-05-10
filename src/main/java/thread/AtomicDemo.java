package thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author DELL
 */
public class AtomicDemo {
    public static void main(String[] args) {
        //Java的java.util.concurrent包除了提供底层锁、并发集合外，还提供了一组原子操作的封装类，它们位于java.util.concurrent.atomic包。
        //我们以AtomicInteger为例，它提供的主要操作有：
        //增加值并返回新值：int addAndGet(int delta)
        //加1后返回新值：int incrementAndGet()
        //获取当前值：int get()
        //用CAS方式设置：int compareAndSet(int expect, int update)
        //Atomic类是通过无锁（lock-free）的方式实现的线程安全（thread-safe）访问。它的主要原理是利用了CAS：Compare and Set。
        //如果我们自己通过CAS编写incrementAndGet()，它大概长这样：
    }
    public int incrementAndGet(AtomicInteger var) {
        int prev, next;
        do {
            prev = var.get();
            next = prev + 1;
        //用CAS方式设置：int compareAndSet(int expect, int update)
        } while (!var.compareAndSet(prev, next));
        return next;
    }
    //CAS是指，在这个操作中，如果AtomicInteger的当前值是prev，那么就更新为next，返回true。
    //如果AtomicInteger的当前值不是prev，就什么也不干，返回false。
    //通过CAS操作并配合do ... while循环，即使其他线程修改了AtomicInteger的值，最终的结果也是正确的。
    //我们利用AtomicLong可以编写一个多线程安全的全局唯一ID生成器：
}
class IdGenerator {
    AtomicLong var = new AtomicLong();
    LongAdder var2 = new LongAdder();
    public long getNextId() {
        //加1后返回新值：int incrementAndGet()
        var2.increment();
        return var.incrementAndGet();
    }
}
//通常情况下，我们并不需要直接用do ... while循环调用compareAndSet实现复杂的并发操作，
//而是用incrementAndGet()这样的封装好的方法，因此，使用起来非常简单。
//在高度竞争的情况下，还可以使用Java 8提供的LongAdder和LongAccumulator。

//AtomicInteger类compareAndSet通过原子操作实现了CAS操作，最底层基于汇编语言实现。
//简单说一下原子操作的概念，“原子”代表最小的单位，所以原子操作可以看做最小的执行单位，
//该操作在执行完毕前不会被任何其他任务或事件打断。
//CAS相比Synchronized，避免了锁的使用，总体性能比Synchronized高很多。