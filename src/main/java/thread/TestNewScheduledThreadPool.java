package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestNewScheduledThreadPool {

    public static void main(String[] args) {
        // 表示延迟3秒执行。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // scheduledThreadPool.schedule(new Runnable() {
        //     @Override
        //     public void run() {
        //     System.out.println("delay 3 seconds");
        //     }
        // }, 3, TimeUnit.SECONDS);
        
        // 延迟1秒后每1秒执行一次。
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 1 seconds");
            }
        }, 1, 1, TimeUnit.SECONDS);

        // 延迟3秒后每3秒执行一次。
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
            System.out.println("delay 2 seconds, and excute every 2 seconds");
            }
        }, 2, 2, TimeUnit.SECONDS);
    }
}
