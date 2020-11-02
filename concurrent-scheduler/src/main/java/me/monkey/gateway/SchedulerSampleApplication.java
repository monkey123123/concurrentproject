package me.monkey.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主类
 *
 * @author tjq
 * @since 2020/4/17
 */
@EnableScheduling
@SpringBootApplication
public class SchedulerSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerSampleApplication.class, args);
    }
}
