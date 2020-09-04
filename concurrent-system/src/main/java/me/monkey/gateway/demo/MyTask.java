package me.monkey.gateway.demo;

import java.util.concurrent.Callable;

public class MyTask  implements Callable<Object> {
    private String args1;
    private String args2;
    //构造函数，用来向task中传递任务的参数
    public  MyTask(String args1,String args2) {
        this.args1=args1;
        this.args2=args2;
    }
    //任务执行的动作
    @Override
    public Object call() throws Exception {
        
        for(int i=0;i<100;i++){
            System.out.println(args1+args2+i);
        }
        return true;
    }
}