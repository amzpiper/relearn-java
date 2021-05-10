package thread;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author guoyh
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        //前面讲到的ReentrantLock保证了只有一个线程可以执行临界区代码：
        //但是有些时候，这种保护有点过头。因为我们发现，任何时刻，只允许一个线程修改，也就是调用inc()方法是必须获取锁，
        //但是，get()方法只读取数据，不修改数据，它实际上允许多个线程同时调用。
        //实际上我们想要的是：允许多个线程同时读，但只要有一个线程在写，其他线程就必须等待：

        //使用ReadWriteLock可以解决这个问题，它保证：
        //只允许一个线程写入（其他线程既不能写入也不能读取）；
        //没有写入时，多个线程允许同时读（提高性能）。
        //用ReadWriteLock实现这个功能十分容易。我们需要创建一个ReadWriteLock实例，然后分别获取读锁和写锁：

        //把读写操作分别用读锁和写锁来加锁，在读取时，多个线程可以同时获得读锁，这样就大大提高了并发读的执行效率。
        //使用ReadWriteLock时，适用条件是同一个数据，有大量线程读取，但仅有少数线程修改。
        //例如，一个论坛的帖子，回复可以看做写入操作，它是不频繁的，但是，浏览可以看做读取操作，是非常频繁的，
        //这种情况就可以使用ReadWriteLock。

        //小结
        //使用ReadWriteLock可以提高读取效率：
        //ReadWriteLock只允许一个线程写入；
        //ReadWriteLock允许多个线程在没有写入时同时读取；
        //ReadWriteLock适合读多写少的场景。
        //锁的目的不是读的数据是错的，是保证连续读逻辑上一致的：
    }
}

class Counter3 {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock rlock = readWriteLock.readLock();
    private final Lock wlock = readWriteLock.writeLock();
    int[] n = new int[10];

    void add(int index) {
        wlock.lock(); // 加写锁
        try {
            n[index]++;
        } finally {
            wlock.unlock(); // 释放写锁
        }
    }

    int[] get() {
        rlock.lock(); // 加读锁
        try {
            return Arrays.copyOf(n,n.length);
        }finally {
            rlock.unlock(); // 释放读锁
        }
    }
}
