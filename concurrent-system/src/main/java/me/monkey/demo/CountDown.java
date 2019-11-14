package me.monkey.demo;

import java.util.concurrent.CountDownLatch;

/*
CountDownLatch可以代替wait/notify的使用,并去掉synchronized,下面重写第一个例子:
 */
public class CountDown {
  private static Integer i = 0;
  final static CountDownLatch countDown = new CountDownLatch(1);

  public void odd() {
    while (i < 10) {
      if (i % 2 == 1) {
        System.out.println(Thread.currentThread().getName() + " - " + i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        i++;
        countDown.countDown();
      } else {
        try {
          countDown.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void even() {
    while (i < 10) {
      if (i % 2 == 0) {
        System.out.println(Thread.currentThread().getName() + " - " + i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        i++;
        countDown.countDown();
      } else {
        try {
          countDown.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {

    CountDown countDown = new CountDown();

    Thread t1 = new Thread(() -> countDown.odd(), "线程1");
    Thread t2 = new Thread(() -> countDown.even(), "线程2");

    t1.start();
    t2.start();
  }
}
