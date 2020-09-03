package me.monkey.demo;

import lombok.SneakyThrows;

public class TryWithResources {
    public static void main(String[] args) {
        run();
    }

    /*
     * 持续exception
     */
    public static void run() {
        try {
            System.out.println("---------try---------------");
            int a = 4;
            int b = a / 0;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            run();
        } finally {
            System.out.println("---------finally---------------");
        }

    }


}
