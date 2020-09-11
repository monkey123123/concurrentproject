package me.monkey.demo.runinseqence;

import java.util.concurrent.Semaphore;
 
//使用信号量
public class ConcurrentPrint {
	// 共享资源个数都初始为1
	private static Semaphore s1 = new Semaphore(1);
	private static Semaphore s2 = new Semaphore(1);
	private static Semaphore s3 = new Semaphore(1);
	Thread t1 = new Thread(new Runnable() {
		public void run() {
			while (true) {
				try {
					s1.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("A");
				s2.release();
			}
		}
	});
	Thread t2 = new Thread(new Runnable() {
		public void run() {
			while (true) {
				try {
					s2.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("B");
				s3.release();
			}
		}
	});
	Thread t3 = new Thread(new Runnable() {
		public void run() {
			while (true) {
				try {
					s3.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("C");
				s1.release();
			}
		}
	});
 
	public void fun() throws InterruptedException {
		// 先占有输出BC的线程的信号量计数
		// 则只能从输出A的线程开始。获取信号量A，然后释放B-获取B-释放C-获取C-释放A，由此形成循环
		s2.acquire();
		s3.acquire();
		t2.start();
		t3.start();
		t1.start();
	}
 
	public static void main(String[] args) throws InterruptedException {
		ConcurrentPrint cp = new ConcurrentPrint();
		long t1 = System.currentTimeMillis();
		cp.fun();
		while (true) {
			if (System.currentTimeMillis() - t1 >= 10)
				System.exit(0);
		}
	}
}