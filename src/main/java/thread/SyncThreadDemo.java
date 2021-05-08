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
        //说明多线程模型下，要保证逻辑正确，对共享变量进行读写时，必须保证一组指令以原子方式执行：即某一个线程执行时，其他线程必须等待：
    }
}

class Counter {
    static int count = 0;
}
class AddThread extends Thread {
    @Override
    public void run() {
        int n = 100000;
        for (int i = 0; i < n; i++) {
            Counter.count++;
            System.out.println("Add"+Counter.count);
        }
    }
}
class DecThread extends Thread {
    @Override
    public void run() {
        int n = 100000;
        for (int i = 0; i < n; i++) {
            Counter.count--;
            System.out.println("Dec"+Counter.count);
        }
    }
}