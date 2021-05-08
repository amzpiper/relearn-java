package thread;

/**
 * 当多个线程同时运行时，线程的调度由操作系统决定，程序本身无法决定。
 * 因此，任何一个线程都有可能在任何指令处被操作系统暂停，然后在某个时间段后继续执行。
 * 这个时候，有个单线程模型下不存在的问题就来了：如果多个线程同时读写共享变量，会出现数据不一致的问题。
 *
 * @author guoyh
 */
public class SyncThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        DecThread decThread = new DecThread();
        addThread.start();
        decThread.start();
        //有个单线程模型下不存在的问题就来了：如果多个线程同时读写共享变量，会出现数据不一致的问题。
        //说明多线程模型下，要保证逻辑正确，对共享变量进行读写时，必须保证一组指令以原子方式执行：即某一个线程执行时，其他线程必须等待：

        //通过加锁和解锁的操作，就能保证3条指令总是在一个线程执行期间，不会有其他线程会进入此指令区间。
        //即使在执行期线程被操作系统中断执行，其他线程也会因为无法获得锁导致无法进入此指令区间。
        //只有执行线程将锁释放后，其他线程才有机会获得锁并执行。
        //这种加锁和解锁之间的代码块我们称之为临界区（Critical Section），任何时候临界区最多只有一个线程能执行。
        //Java程序使用synchronized关键字对一个对象进行加锁
        //synchronized(lock) {
        //    n = n + 1;
        //}
        //保证了代码块在任意时刻最多只有一个线程能执行
    }
}

class Counter {
    static final Object lock = new Counter();
    static int count = 0;
}
class AddThread extends Thread {
    @Override
    public void run() {
        int n = 100000;
        for (int i = 0; i < n; i++) {
            synchronized (Counter.lock) {// 获取锁
                Counter.count++;
            }// 释放锁
            //看上去是一行语句，实际上对应了3条指令：
            //ILOAD
            //IADD
            //ISTORE
            System.out.println("Add"+Counter.count);
        }
    }
}
class DecThread extends Thread {
    @Override
    public void run() {
        int n = 100000;
        for (int i = 0; i < n; i++) {
            synchronized (Counter.lock) {// 获取锁
                Counter.count--;
            }// 释放锁
            System.out.println("Dec"+Counter.count);
        }
    }
}