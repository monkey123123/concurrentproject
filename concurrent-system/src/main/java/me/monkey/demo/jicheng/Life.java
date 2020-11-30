package me.monkey.demo.jicheng;

import java.util.Random;

public abstract class Life {
     final int die(){
         System.out.println("生命结束");
        return new Random().nextInt();
    }
}
