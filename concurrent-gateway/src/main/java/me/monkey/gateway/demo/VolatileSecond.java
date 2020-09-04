package me.monkey.gateway.demo;

public class VolatileSecond{
    public static volatile int t = 0;

    private synchronized static void incr(){
        for (int j = 0; j < 1000; j++) {
            t = t + 1;
        }
    }
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        System.out.println("start: "+start);
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++){
            //每个线程对t进行1000次加1的操作
            threads[i] = new Thread(new Runnable(){
                @Override
                public void run(){
                    incr();
                }
            });
            threads[i].start();
        }

        //等待所有累加线程都结束
        while(Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println("duration: "+(System.currentTimeMillis()-start));

        //打印t的值
        System.out.println(t);
    }
}