package me.monkey.gateway.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dog extends Animal

{

    static {
        System.out.println("-------Dog static block ---------");
    }
    public Dog() {
        System.out.println("-------Dog 无参 constructor---------");
    }

    public static void main(String[] args) {
        Animal dog = new Dog();

        dog.run();

        dog.shout();//static method

        shout();

        Dog dog2 = new Dog();

        dog2.run();


        dog2.shout();
        /*

-------Animal static block ---------
-------Dog static block ---------
-------Animal 无参 constructor---------
-------Dog 无参 constructor---------
狗跑得很快
动物可以叫
狗的叫声是汪汪汪
-------Animal 无参 constructor---------
-------Dog 无参 constructor---------
狗跑得很快
狗的叫声是汪汪汪

         */
        List<String> list = new ArrayList();
        Map map = new HashMap();
    }

    @Override

    public void run() {

        System.out.println("狗跑得很快");

    }

    public static void shout() {

        System.out.println("狗的叫声是汪汪汪");

    }

}