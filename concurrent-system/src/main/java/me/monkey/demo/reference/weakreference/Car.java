package me.monkey.demo.reference.weakreference;

import java.lang.ref.WeakReference;

public class Car {
    private double price;
    private String colour;

    public Car(double price, String colour) {
        this.price = price;
        this.colour = colour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return colour + "car costs $" + price;
    }

}

/*
在上例中, 程序运行一段时间后, 程序打印出"Object has been collected." 说明, weak reference指向的对象的被回收了.

值得注意的一点, 即使有car引用指向对象, 且car是一个strong reference, weak reference weakCar指向的对象仍然被回收了.
这是因为java的编译器在发现进入while循环之后, car已经没有被使用了, 所以进行了优化(将其置空?).
 */
// https://blog.csdn.net/qq_33663983/article/details/78349641
class TestWeakReference {

    public static void main(String[] args) {
        //strong reference强引用，运行了数个小时依然没有回收
//        Car car = new Car(22000, "silver");
//        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        //weak reference弱引用，运行了数秒就被回收
        WeakReference<Car> weakCar = new WeakReference<Car>(new Car(22000, "silver"));

        int i = 0;

        while (true) {
            if (weakCar.get() != null) {
                i++;
                System.out.println("Object is alive for " + i + " loops - " );
            } else {
                System.out.println("Object has been collected.-----------------");
                break;
            }
        }
    }

}