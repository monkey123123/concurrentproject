package me.monkey.demo.classloader;
public class Test{
    public static void main(String[] arg){
//        sun.misc.Launcher$AppClassLoader@18b4aac2
//        sun.misc.Launcher$ExtClassLoader@45c8e616
//        null
        ClassLoader c  = Test.class.getClassLoader();  //获取Test类的类加载器
        System.out.println(c); 
        ClassLoader c1 = c.getParent();  //获取c这个类加载器的父类加载器
        System.out.println(c1);

        ClassLoader c2 = c1.getParent();//获取c1这个类加载器的父类加载器
        System.out.println(c2);
  }
}