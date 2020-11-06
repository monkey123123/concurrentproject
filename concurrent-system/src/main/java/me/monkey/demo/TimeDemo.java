package me.monkey.demo;

import java.util.Date;

public class TimeDemo {
    public static void main(String[] args) {
        long now = new Date().getTime();
        System.out.println((System.currentTimeMillis() - now));
        Long xx = null;
        System.out.println(now - xx.longValue() >= now);
    }
}
