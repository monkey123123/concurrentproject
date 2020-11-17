package me.monkey.demo.fatherson;

/**
 * 父类能调用子类的方法吗？
 *
 * 答：能。
 *
 * 怎么做？
 *
 * 把子类传递到父类的有参构造中，然后调用。
 * 使用反射的方式调用，你使用了反射还有谁不能调用的？！
 * 父类调用子类的静态方法。
 */
public class Test2{
    public static void main(String[] args)
    {
        Son son = new Son();
        Father father=new Father(son);
        father.fun1();
        father.fun4();
    }   
}
class Father{
    public Son son;
    public Father(Son son){     
        this.son=son;
    }   
    public Father() {
         
    }
  
    public void fun4(){
        //方法3：使用反射的方式调用子类方法
        try {
            Class cls=Class.forName("me.monkey.demo.fatherson.Son");
            Son son=(Son) cls.newInstance();
            son.fun2();
        } catch (Exception e) {
         
            e.printStackTrace();
        }
    }
    public void fun1(){
        //方法1：把子类传递到父类的有参构造函数中，然后调用。
        System.out.println("我是父类的方法");
        son.fun2();
         
        //方法2：父类调用子类的静态方法。
        Son.fun3();
    }       
}
  
class Son extends Father{
     
    public static void fun3(){
        System.out.println("我是子类的静态方法");
    }
    public void fun2(){
        System.out.println("我是子类的方法");
         
    }
     
}