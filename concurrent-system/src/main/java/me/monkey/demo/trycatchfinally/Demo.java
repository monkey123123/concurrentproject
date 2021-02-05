package me.monkey.demo.trycatchfinally;

public class Demo {
    public static void main(String[] args) {
        start();
    }

    public static int start() {
        try {
            int a = 5/0;

        } catch (Throwable t) {
            System.out.println(t);
            throw t;
        } finally {
            System.out.println("------------");
            //绝对不能在finally里面写return，不然异常就无法抛出了
//            return 1111;
        }
        return 0;
    }

}
