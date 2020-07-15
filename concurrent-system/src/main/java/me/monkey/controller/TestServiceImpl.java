package me.monkey.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Async
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------run方法---------");
    }
}
