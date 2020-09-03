package me.monkey.demo.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CustomeThreadPoolDemo {
    public static void main(String[] args) {
        //无返回值
        ThreadPoolUtil.getExecutorService().execute(() -> {
            System.out.println("test1");
        });
        //有返回值 lambda
        Future<Integer> future = ThreadPoolUtil.getExecutorService().submit(() -> {
            System.out.println("test2");
            return 10;
        });
        //有返回值
        Future<Integer> future2 = ThreadPoolUtil.getExecutorService().submit(new MyCallable());
        Object o = null;
        Object o2 = null;
        try {
            o = future.get();
            o2 = future2.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(o);
        System.out.println(o2);
        MyThread myThread = new MyThread();
        myThread.setName("myThread-%d");
        //错误的线程使用方式
        myThread.start();
        //如果不调用shutdown，主线程无法终止
        //ThreadPoolUtil.getExecutorService().shutdown();
    }
}

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("-------业务方法-------");
        return 1;
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            System.out.println("----MyThread------");
            Thread.sleep(10000);
            System.out.println("----MyThread end------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}