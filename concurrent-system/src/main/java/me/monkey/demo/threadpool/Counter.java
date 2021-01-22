package me.monkey.demo.threadpool;
    public class Counter {
     //新建一个静态的ThreadLocal变量，并通过get方法将其变为一个可访问的对象
     private static ThreadLocal<Integer> counterContext = new ThreadLocal<Integer>(){
         @Override
         protected synchronized Integer initialValue(){
             return 10;
         }
     };
     // 通过静态的get方法访问ThreadLocal中存储的值
     public static Integer get(){
         return counterContext.get();
     }
     // 通过静态的set方法将变量值设置到ThreadLocal中
     public static void set(Integer value) {
         counterContext.set(value);
     }
     // 封装业务逻辑，操作存储于ThreadLocal中的变量
     public static Integer getNextCounter() {
         counterContext.set(counterContext.get() + 1);
         return counterContext.get();
     }
}