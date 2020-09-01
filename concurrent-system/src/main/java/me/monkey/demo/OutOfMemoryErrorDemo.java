package me.monkey.demo;

import com.google.gson.Gson;

public class OutOfMemoryErrorDemo
{
    static String[] arr = null;
    public static void main(String[] args) {
        for(int i=0; i < 10000000;i++){
            arr = new String[1024*1024*64];
            System.out.println(i);
            arr = null;
        }

//        new Gson().
    }
}
