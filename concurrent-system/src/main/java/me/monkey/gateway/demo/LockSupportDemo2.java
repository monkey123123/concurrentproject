package me.monkey.gateway.demo;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo2 {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被中断了");
                }
                System.out.println("继续执行");
            }
        }
    }

    /*
    t1内部有休眠1s的操作，所以unpark肯定先于park的调用，但是t1最终仍然可以完结。这是因为park和unpark会对每个线程维持一个许可（boolean值）

unpark调用时，如果当前线程还未进入park，则许可为true
park调用时，判断许可是否为true，如果是true，则继续往下执行；如果是false，则等待，直到许可为true




    park() 为了线程调度，禁用当前线程，除非许可可用。
    unpark(Thread thread) 如果给定线程的许可尚不可用，则使其可用。
    总结一下
park和unpark可以实现类似wait和notify的功能，但是并不和wait和notify交叉，也就是说unpark不会对wait起作用，notify也不会对park起作用。
park和unpark的使用不会出现死锁的情况
blocker的作用是在dump线程的时候看到阻塞对象的信息

作者：juconcurrent
链接：https://www.jianshu.com/p/f1f2cd289205
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        t1.start();
        System.out.println("--------------t1 start");
        LockSupport.unpark(t1);
        System.out.println("--------------t1 unpark, 主线程即将结束，t1是否会继续执行？");
    }
}
