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
        addThread.join();
        decThread.join();
        System.out.println(Counter.count);
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
        //synchronized (Counter.lock) {// 获取锁
        //表示用Counter.lock实例作为锁，两个线程在执行各自的synchronized(Counter.lock) { ... }代码块时，
        //必须先获得锁，才能进入代码块进行。
        //执行结束后，在synchronized语句块结束会自动释放锁。
        //这样一来，对Counter.count变量进行读写就不可能同时进行。上述代码无论运行多少次，最终结果都是0。

        //使用synchronized解决了多线程同步访问共享变量的正确性问题。但是，它的缺点是带来了性能下降。因为synchronized代码块无法并发执行。此外，加锁和解锁需要消耗一定的时间，所以，synchronized会降低程序的执行效率。
        //我们来概括一下如何使用synchronized：
        //找出修改共享变量的线程代码块；
        //选择一个共享实例作为锁；
        //使用synchronized(lockObject) { ... }。
        //使用synchronized的时候，不必担心抛出异常。因为无论是否有异常，都会在synchronized结束处正确释放锁

        //因为两个线程各自的synchronized锁住的不是同一个对象！这使得两个线程各自都可以同时获得锁：
        //因为JVM只保证同一个锁在任意时刻只能被一个线程获取，但两个不同的锁在同一时刻可以被两个线程分别获取。
        //因此，使用synchronized的时候，获取到的是哪个锁非常重要。锁对象如果不对，代码逻辑就不对。

        //4个线程对两个共享变量分别进行读写操作，但是使用的锁都是Counter.lock这一个对象，
        //这就造成了原本可以并发执行的Counter.studentCount += 1和Counter.teacherCount += 1，
        //现在无法并发执行了，执行效率大大降低。
        //实际上，需要同步的线程可以分成两组：
        //AddStudentThread和DecStudentThread，
        //AddTeacherThread和DecTeacherThread，
        //组之间不存在竞争，因此，应该使用两个不同的锁
        System.out.println("---------------------------------------------------");
        long first = System.currentTimeMillis();
        Thread[] threads = new Thread[]{new AddThread(), new DecThread(), new AddTeacher(), new DecTeacher()};
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Count:"+Counter.count);
        System.out.println("teacherCount:"+Counter.teacherCount);
        System.out.println((end-first)/1000.00);

        //让线程自己选择锁对象往往会使得代码逻辑混乱，也不利于封装。更好的方法是把synchronized逻辑封装起来。
        //例如，我们编写一个计数器如下
        Count count = new Count();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count.add();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count.dec();
                }
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(count.getN());
        //如果一个类被设计为允许多线程正确访问，我们就说这个类就是“线程安全”的（thread-safe），
        //上面的Count类就是线程安全的。Java标准库的java.lang.StringBuffer也是线程安全的。
        //还有一些不变类，例如String，Integer，LocalDate，它们的所有成员变量都是final，
        //多线程同时访问时只能读不能写，这些不变类也是线程安全的。
        //最后，类似Math这些只提供静态方法，没有成员变量的类，也是线程安全的。
        //除了上述几种少数情况，大部分类，例如ArrayList，都是非线程安全的类，我们不能在多线程中修改它们。
        //但是，如果所有线程都只读取，不写入，那么ArrayList是可以安全地在线程间共享的。

        //public synchronized static void test(int n) {
        //    ...
        //}
        //对于static方法，是没有this实例的，因为static方法是针对类而不是实例。
        //但是我们注意到任何一个类都有一个由JVM自动创建的Class实例，
        //因此，对static方法添加synchronized，锁住的是该类的Class实例。
        //上述synchronized static方法实际上相当于：
        //public class Counter {
        //    public static void test(int n) {
        //        synchronized(Counter.class) {
        //            ...
        //        }
        //    }
        //}
        //如果我们把代码稍微改一下，返回一个包含两个int的对象：
        // public Pair get() {
        //     Pair p = new Pair();
        //     p.first = first;
        //     p.last = last;
        //     return p;
        // }
        // 就必须要同步了。

        //小结
        //用synchronized修饰方法可以把整个方法变为同步代码块，synchronized方法加锁对象是this；
        //通过合理的设计和数据封装可以让一个类变为“线程安全”；
        //一个类没有特殊说明，默认不是thread-safe；
        //多线程能否安全访问某个非线程安全的实例，需要具体问题具体分析。
    }
}

class Counter {
    public static final Object lock = new Counter();
    public static final Object lock2 = new Counter();
    public static int count = 0;
    public static int teacherCount = 0;
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

class AddTeacher extends Thread {
    @Override
    public void run() {
        int n = 100000;
        for (int i = 0; i < n; i++) {
            //使用相同锁影响并发
            //synchronized (Counter.lock) {
            //使用不同锁
            synchronized (Counter.lock2) {
                Counter.teacherCount++;
            }
        }
    }
}
class DecTeacher extends Thread {
    @Override
    public void run() {
        int n = 100000;
        for (int i = 0; i < n; i++) {
            //使用相同锁影响并发
            //synchronized (Counter.lock) {
            //使用不同锁
            synchronized (Counter.lock2) {
                Counter.teacherCount--;
            }
        }
    }
}

class Count {
    private int n = 0;

    public void add() {
        synchronized (this) {
            n++;
        }
    }

    public void dec() {
        synchronized (this) {
            n--;
        }
    }

    public int getN() {
        return n;
    }
}