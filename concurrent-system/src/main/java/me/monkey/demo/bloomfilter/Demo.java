package me.monkey.demo.bloomfilter;

import me.monkey.demo.pattern.mediatorpattern.User;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        User user = new User("abc");

        objectObjectHashMap.put(user, "abc");
        user = new User("efg");
        System.out.println(objectObjectHashMap.get(user));
        
        


    }
}
