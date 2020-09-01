package me.monkey.demo.interviewee;

class Text3{

    public static void main(String[] args) {
        Text3 text3 = new Text3();
        text3.texe3();
    }

     public void texe3(){
       Thread t1=new Thread(new Runnable(){
            public void run(){
             System.out.println("第一个线程");
            }
           },"T1");



       Thread t2=new Thread(new Runnable(){
            public void run(){
             System.out.println("第二个线程");
              try{
                   t1.join(10);
                }catch(Exception e){
                      e.printStackTrace();
                 }

            }
           },"T2");



       Thread t3=new Thread(new Runnable(){
            public void run(){
             System.out.println("第三个线程");
            try{
               t2.join(10);
             }catch(Exception e){
                e.printStackTrace();
             }

            }



           },"T3");      



              t1.start();



              t2.start();



              t3.start();



     }



}