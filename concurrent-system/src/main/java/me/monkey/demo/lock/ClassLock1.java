package me.monkey.demo.lock;

public class ClassLock1 implements Runnable {

	// 这两个实例，声明为static，是因为要在main方法中测试，与方法锁无关
	static ClassLock1 instance1 = new ClassLock1();
	static ClassLock1 instance2 = new ClassLock1();

	// 关键： static synchronized两个关键字同时使用
	private static synchronized void method() {
		System.out.println("线程名：" + Thread.currentThread().getName() + "，运行开始");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程：" + Thread.currentThread().getName() + "，运行结束");
	}

	@Override
	public void run() {
		method();
	}

	public static void main(String[] args) {
		Thread thread1 = new Thread(instance1);
		Thread thread2 = new Thread(instance2);
		thread1.start();
		thread2.start();
		try {
			//两个线程必须都执行完毕后
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("两个线程都已经执行完毕");
	}
}