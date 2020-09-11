package me.monkey.demo;

import java.util.concurrent.TimeUnit;

public class DaemonThreadTest_2 extends Thread {


    public static void main(String[] args) {
        DaemonThreadTest_2 test = new DaemonThreadTest_2();
        // 如果不设置daemon，那么线程将输出100后才结束
//        test.setDaemon(true);
        test.start();
        System.out.println("isDaemon = " + test.isDaemon());
        //        try {
//            System.in.read(); // 接受输入，使程序在此停顿，一旦接收到用户输入，main线程结束，守护线程自动结束
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("isDaemon test");
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

}
//版权声明：本文为CSDN博主「W-大泡泡」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/u011389474/article/details/60880345