package me.monkey.demo;

import java.util.concurrent.CountDownLatch;

public class TestSynchronized {
    final static CountDownLatch cdl = new CountDownLatch(1);
    static String database = null;
    public static String initDataBase(){
        try {
            cdl.await();
            System.out.println("Thread.Id="+Thread.currentThread().getName()+"  ,到达");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (TestSynchronized.class){
            if(database==null){
                System.out.println("Thread.Id="+Thread.currentThread().getName());
                database = "mongodb";

            }
            return database;
        }
    }
    public static void main(String[] args) {
        // TestSynchronized ts = new TestSynchronized();
        Thread t1 = new Thread(() -> TestSynchronized.initDataBase(), "线程1");
        Thread t2 = new Thread(() -> TestSynchronized.initDataBase(), "线程2");
        Thread t3 = new Thread(() -> TestSynchronized.initDataBase(), "线程3");
        Thread t4 = new Thread(() -> TestSynchronized.initDataBase(), "线程4");
        Thread t5 = new Thread(() -> TestSynchronized.initDataBase(), "线程5");
        Thread t6 = new Thread(() -> TestSynchronized.initDataBase(), "线程6");
        Thread t7 = new Thread(() -> TestSynchronized.initDataBase(), "线程7");
        Thread t8 = new Thread(() -> TestSynchronized.initDataBase(), "线程8");

        cdl.countDown();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
    }

}
