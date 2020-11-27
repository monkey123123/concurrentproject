package me.monkey.demo.threadpool;

import java.util.concurrent.*;

public class ScheduledPoolDemo {
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);


    public static void main(String[] args) {

        int wait = 0;
        for(int i=0; i< 6; i++){
            ScheduledFuture<?> schedule = scheduler.schedule(() -> test(), wait, TimeUnit.SECONDS);
            wait += 4;
        }
    }


    public static void test(){
        System.out.println("---------" + System.currentTimeMillis());
    }
}
