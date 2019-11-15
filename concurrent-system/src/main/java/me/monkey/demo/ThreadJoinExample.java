package me.monkey.demo;
/*
Java Thread join示例与详解
Java Thread join方法用来暂停当前线程直到join操作上的线程结束。java中有三个重载的join方法：

public final void join()：此方法会把当前线程变为wait，直到执行join操作的线程结束，如果该线程在执行中被中断，则会抛出InterruptedException。

public final synchronized void join(long millis)：此方法会把当前线程变为wait，直到执行join操作的线程结束或者在执行join后等待millis的时间。因为线程调度依赖于操作系统的实现，因为这并不能保证当前线程一定会在millis时间变为RUnnable。

public final synchroinzed void join(long millis, int nanos)：此方法会把当前线程变为wait，直到执行join操作的线程结束或者在join后等待millis+nanos的时间。

下面的示例程序展示了Thread join方法的用法。在这段程序要保证main线程是最后一个完成的线程，同时保证第三个线程(third thread)在第一个线程(first thread)结束后才开始执行。
 */
public class ThreadJoinExample {

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();

        //start second thread after waiting for 2 seconds or if it's dead
        try {
            t1.join(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();

        //start third thread only when first thread is dead
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();

        //let all threads finish execution before finishing main thread
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("All threads are dead, exiting main thread");
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread started:::"+Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended:::"+Thread.currentThread().getName());
    }
}