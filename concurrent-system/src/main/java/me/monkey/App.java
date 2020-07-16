package me.monkey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 */
//@Configuration
//@EnableAutoConfiguration
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableCircuitBreaker
//@EnableHystrix//add by allen

@EnableScheduling
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("me.monkey.dao")
//@EnableMongoRepositories("me.monkey.modules.system.repository.mongodb")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
