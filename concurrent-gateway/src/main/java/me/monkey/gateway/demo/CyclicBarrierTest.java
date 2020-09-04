package me.monkey.gateway.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
CyclicBarrier
等待N个线程都达到某个状态后继续运行
 */
public class CyclicBarrierTest {
  public static void main(String[] args) {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": 准备...");
      try {
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      System.out.println("全部启动完毕!");
    }, "线程1").start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": 准备...");
      try {
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      System.out.println("全部启动完毕!");
    }, "线程2").start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": 准备...");
      try {
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      System.out.println("全部启动完毕!");
    }, "线程3").start();
  }
}