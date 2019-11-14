package me.monkey.demo;

/*

 */
public class Volatile implements Runnable {
  private static volatile Boolean flag = true;

  @Override
  public void run() {
    while (flag) {
      System.out.println(Thread.currentThread().getName() + " - 执行");
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("线程结束");
  }

  public static void main(String[] args) {
    Thread t = new Thread(new Volatile());
    t.start();
    try {
      Thread.sleep(5);
      flag = false;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}