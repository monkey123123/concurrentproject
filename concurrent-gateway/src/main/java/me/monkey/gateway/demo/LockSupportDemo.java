package me.monkey.gateway.demo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }
/*
这儿park和unpark其实实现了wait和notify的功能，不过还是有一些差别的。

park不需要获取某个对象的锁
因为中断的时候park不会抛出InterruptedException异常，所以需要在park之后自行判断中断状态，然后做额外的处理

作者：juconcurrent
链接：https://www.jianshu.com/p/f1f2cd289205
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        System.out.println("-------------t1 start");
        Thread.sleep(1000L);
        t2.start();
        System.out.println("-------------t2 start");

        Thread.sleep(3000L);
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}