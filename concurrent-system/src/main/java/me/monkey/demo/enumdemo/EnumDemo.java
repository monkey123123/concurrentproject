package me.monkey.demo.enumdemo;

public class EnumDemo {
    public static void main(String[] args) {
        System.out.println(TriggerMode.DEVICE.name());
        System.out.println(TriggerMode.DEVICE.toString());
        System.out.println(TriggerMode.valueOf(TriggerMode.DEVICE.name()));
        System.out.println(TriggerMode.DEVICE.getValue().get());
    }
}
