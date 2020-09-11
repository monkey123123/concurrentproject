package me.monkey.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class AsycThreadPool {
    private AsycThreadPool() {
    }

    public static ThreadPoolExecutor getThreadPool() {
        System.out.println("--------getThreadPool-------");
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 6,
                30L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(50),
                new ThreadFactory() {
                    private final AtomicLong counter = new AtomicLong();

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("AsycThreadPool-scheduler-" + counter.incrementAndGet());
                        return thread;
                    }

                },
                new MyIgnorePolicy());
        return pool;
    }

    public static void main(String[] args) throws Exception{
        MyCallable call = new MyCallable();
        ThreadPoolExecutor pool = AsycThreadPool.getThreadPool();
        ThreadPoolExecutor pool2 = AsycThreadPool.getThreadPool();
        Future future = pool.submit(call);
        Object o1 = future.get();
        System.out.println(o1);

        FTask ft = new FTask(call);
        Future<?> future2 = pool.submit(ft);// submit有返回结果 execute没有返回结果
        System.out.println(ft.get());
        System.out.println("----"+future2.get());// null

        // Runnable Thread都拿不到返回值，只有Callable能拿到返回值
        MyRunnable2 myRunnable2 = new MyRunnable2();
        pool.execute(myRunnable2);
        Future<?> future1 = pool.submit(myRunnable2);
        Object o = future1.get();

        Future<?> future3 = pool.submit(new MyThread());
        Object o2 = future3.get();
        System.out.println(o2);

        pool.shutdown();
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

}

class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("---------MyThread.run()--------");
    }
}
class MyRunnable2 implements Runnable{

    @Override
    public void run() {
        System.out.println("---------MyRunable.run()--------");
    }
}
class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("-----------------");
        return 1;
    }
}
class FTask extends FutureTask<Integer> {

    public FTask(Callable<Integer> callable) {
        super(callable);
        System.out.println("callable = " + callable);
    }
}

