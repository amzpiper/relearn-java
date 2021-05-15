package thread;

import collection.User;

/**
 * @author DELL
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        //多线程是Java实现多任务的基础，Thread对象代表一个线程，
        //我们可以在代码中调用Thread.currentThread()获取当前线程。
        //例如，打印日志时，可以同时打印出当前线程的名字：
        log("start main ...");
        new Thread(() -> {
            log("run task...");
        }).start();
        new Thread(() -> {
            log("run print...");
        }).start();
        log("end main ...");
        //多任务，Java标准库提供的线程池可以方便地执行这些任务，同时复用线程。Web应用程序就是典型的多任务应用，每个用户请求页面时，我们都会创建一个任务，类似：
        //
        //public void process(User user) {
        //    checkPermission();
        //    doWork();
        //    saveStatus();
        //    sendResponse();
        //}
        //然后，通过线程池去执行这些任务。
        //
        //观察process()方法，它内部需要调用若干其他方法，同时，我们遇到一个问题：如何在一个线程内传递状态？
        //
        //process()方法需要传递的状态就是User实例。有的童鞋会想，简单地传入User就可以了：
        //
        //public void process(User user) {
        //    checkPermission(user);
        //    doWork(user);
        //    saveStatus(user);
        //    sendResponse(user);
        //}
        //但是往往一个方法又会调用其他很多方法，这样会导致User传递到所有地方：
        //
        //void doWork(User user) {
        //    queryStatus(user);
        //    checkStatus();
        //    setNewStatus(user);
        //    log();
        //}
        //这种在一个线程中，横跨若干方法调用，需要传递的对象，我们通常称之为上下文（Context），
        //它是一种状态，可以是用户身份、任务信息等。
        //给每个方法增加一个context参数非常麻烦，
        //而且有些时候，如果调用链有无法修改源码的第三方库，User对象就传不进去了。
        //Java标准库提供了一个特殊的ThreadLocal，它可以在一个线程中传递同一个对象。
        //ThreadLocal实例通常总是以静态字段初始化如下：
    }
    static ThreadLocal<String> threadLocalUser = new ThreadLocal<>();
    //它的典型使用方式如下：
    //
    //void processUser(user) {
    //    try {
    //        threadLocalUser.set(user);
    //        step1();
    //        step2();
    //    } finally {
    //        threadLocalUser.remove();
    //    }
    //}
    //通过设置一个User实例关联到ThreadLocal中，在移除之前，所有方法都可以随时获取到该User实例：
    //
    //void step1() {
    //    User u = threadLocalUser.get();
    //    log();
    //    printUser();
    //}
    //
    //void log() {
    //    User u = threadLocalUser.get();
    //    println(u.name);
    //}
    //
    //void step2() {
    //    User u = threadLocalUser.get();
    //    checkUser(u.id);
    //}
    //注意到普通的方法调用一定是同一个线程执行的，所以，step1()、step2()以及log()方法内，
    //threadLocalUser.get()获取的User对象是同一个实例。
    //实际上，可以把ThreadLocal看成一个全局Map<Thread, Object>：
    //每个线程获取ThreadLocal变量时，总是使用Thread自身作为key：
    //Object threadLocalValue = threadLocalMap.get(Thread.currentThread());
    //因此，ThreadLocal相当于给每个线程都开辟了一个独立的存储空间，
    //各个线程的ThreadLocal关联的实例互不干扰。
    //最后，特别注意ThreadLocal一定要在finally中清除：
    //
    //try {
    //    threadLocalUser.set(user);
    //    ...
    //} finally {
    //    threadLocalUser.remove();
    //}
    //这是因为当前线程执行完相关代码后，很可能会被重新放入线程池中，如果ThreadLocal没有被清除，
    //该线程执行其他代码时，会把上一次的状态带进去。
    //为了保证能释放ThreadLocal关联的实例，
    //我们可以通过AutoCloseable接口配合try (resource) {...}结构，让编译器自动为我们关闭。
    //例如，一个保存了当前用户名的ThreadLocal可以封装为一个UserContext对象：
    //public class UserContext implements AutoCloseable {
    //
    //    static final ThreadLocal<String> ctx = new ThreadLocal<>();
    //
    //    public UserContext(String user) {
    //        ctx.set(user);
    //    }
    //
    //    public static String currentUser() {
    //        return ctx.get();
    //    }
    //
    //    @Override
    //    public void close() {
    //        ctx.remove();
    //    }
    //}
    //使用的时候，我们借助try (resource) {...}结构，可以这么写：
    //
    //try (var ctx = new UserContext("Bob")) {
    //    // 可任意调用UserContext.currentUser():
    //    String currentUser = UserContext.currentUser();
    //} // 在此自动调用UserContext.close()方法释放ThreadLocal关联对象
    //这样就在UserContext中完全封装了ThreadLocal，
    //外部代码在try (resource) {...}内部可以随时调用UserContext.currentUser()获取当前线程绑定的用户名。

    static void log(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
