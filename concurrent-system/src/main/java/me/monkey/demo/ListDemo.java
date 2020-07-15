package me.monkey.demo;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> lis = new ArrayList();
        lis.add(Integer.valueOf(3));
        boolean exist = lis.contains(3);
        System.out.println(exist);
    }
}
