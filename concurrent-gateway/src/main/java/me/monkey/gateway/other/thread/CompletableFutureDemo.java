package me.monkey.gateway.other.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture
                //委托师傅做蛋糕
                .supplyAsync(()-> {
                    try {
                        System.out.println("师傅准备做蛋糕");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("师傅做蛋糕做好了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "cake";
                })
                //做好了告诉我一声
                .thenAccept(cake->{
                    System.out.println("我吃蛋糕:" + cake);
                });
        System.out.println("我先去喝杯牛奶");
        //Java坑点： Thread.currentThread().join()
        // Thread.currentThread().join()
        //join方法的作用是阻塞，即等待线程结束，才继续执行。
        //t.join() 的作用是 谁执行t.join()方法就等待t执行完毕
        //坑点在于：
        // Thread.currentThread().join()；
        //线程一直在阻塞，无法终止。自己等待自己结束。
        Thread.currentThread().join();
    }
}
