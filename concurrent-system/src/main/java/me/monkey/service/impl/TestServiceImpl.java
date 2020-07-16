package me.monkey.service.impl;

import me.monkey.service.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
    @Override
    public void runScheduled() {
        System.out.println("--------runScheduled方法---------");
    }


    @Scheduled(cron="${time.cron}")
    @Override
    public void testPlaceholder1() {
        System.out.println("Execute testPlaceholder1 at " + System.currentTimeMillis());
    }

    @Scheduled(cron="*/${time.interval} * * * * *")
    @Override
    public void testPlaceholder2() {
        System.out.println("Execute testPlaceholder2 at " + System.currentTimeMillis());
    }



}
