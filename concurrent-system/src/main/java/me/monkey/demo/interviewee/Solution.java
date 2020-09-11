package me.monkey.demo.interviewee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution {



    // 设置线程池的容量为1,就可以保证线程的顺序执行


    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            public void run() {
                System.out.println("This is thread a"+Thread.currentThread().getId());
            }
        });


        Thread b = new Thread(new Runnable() {
            public void run() {
                System.out.println("This is thread b"+Thread.currentThread().getId());
            }
        });


        Thread c = new Thread(new Runnable() {
            public void run() {
                System.out.println("This is thread c"+Thread.currentThread().getId());
            }
        });
        ExecutorService executor = Executors.newFixedThreadPool(1);
        for(int i = 0;i<10;i++){
            executor.execute(a);
            executor.execute(b);
            executor.execute(c);
        }
    }


}
