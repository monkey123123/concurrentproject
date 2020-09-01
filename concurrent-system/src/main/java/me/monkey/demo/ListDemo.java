package me.monkey.demo;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> lis = new ArrayList();
        lis.add(Integer.valueOf(3));
        boolean exist = lis.contains(3);
        System.out.println(exist);
        System.out.println(equipmentType.GW.getValue());// SDW-GW
//        equipmentType.GW.setValue("123456");
        System.out.println(equipmentType.GW.getValue());// 123456
    }
}
