package design.proxy.statics.thread;

public class MyThread implements Runnable {
    public static void main(String[] args) {
        new Thread(new MyThread()).start();;
    }

    @Override
    public void run() {
        System.out.println("1");
    }

    
}
