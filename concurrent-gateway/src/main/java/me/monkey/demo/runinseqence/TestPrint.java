package me.monkey.demo.runinseqence;

//第一种方法，使用Object的wait和notifyAll方法
public class TestPrint {
	static int count = 0;
	static final Object obj = new Object();
	Thread t1 = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				synchronized (obj) {
					if (count % 3 == 0) {
						System.out.println("A");
						count++;
						obj.notifyAll();
					} else
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}
	});
	Thread t2 = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				synchronized (obj) {
					if (count % 3 == 1) {
						System.out.println("B");
						count++;
						obj.notifyAll();
					} else
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}
	});
	Thread t3 = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				synchronized (obj) {
					if (count % 3 == 2) {
						System.out.println("C");
						count++;
						obj.notifyAll();
					} else
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		}
	});
 
	public void fun() {
		t3.start();
		t1.start();
		t2.start();
	}
 
	public static void main(String[] args) {
		TestPrint tp = new TestPrint();
		long t1 = System.currentTimeMillis();
		tp.fun();
		while (true) {
			if (System.currentTimeMillis() - t1 >= 10)// 运行10个毫秒
				System.exit(0);
		}
	}
}