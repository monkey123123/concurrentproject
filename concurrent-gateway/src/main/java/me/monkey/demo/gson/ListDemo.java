package me.monkey.demo.gson;

import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<String> list = null;
        for (String s: list) {
            //Exception in thread "main" java.lang.NullPointerException
            System.out.println(s);
        }
    }
}
