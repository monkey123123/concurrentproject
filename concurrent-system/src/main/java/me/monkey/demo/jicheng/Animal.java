package me.monkey.demo.jicheng;

public class Animal extends Life{


    static {
        System.out.println("-------Animal static block ---------");
    }
    public Animal() {
        System.out.println("-------Animal 无参 constructor---------");
    }

    public void run() {

        System.out.println("动物可以奔跑");

    }

    public static void shout() {

        System.out.println("动物可以叫");

    }

    public int die2() {
        System.out.println("动物死了");
        return super.die();
    }

}