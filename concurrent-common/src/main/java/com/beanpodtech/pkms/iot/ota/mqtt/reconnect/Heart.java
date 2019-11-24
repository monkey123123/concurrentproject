package com.beanpodtech.pkms.iot.ota.mqtt.reconnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Heart {
    @Autowired
    private MyMQTTClient myMQTTClient;
    //3.添加定时任务
    //@Scheduled(cron = "0/30 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    @Scheduled(fixedRate=5000)
    public void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        try {
            myMQTTClient.pushHeart();
        } catch (Exception e) {
            System.out.println("------------send heart beat failed.---------");
            e.printStackTrace();
        }
    }
}
