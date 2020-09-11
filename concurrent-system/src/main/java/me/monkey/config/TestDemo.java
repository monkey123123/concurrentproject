package me.monkey.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestDemo {
    @Async("asyncServiceTaskExecutor")
    public void checkReport() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("88888888");
    }
}
