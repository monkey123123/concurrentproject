package me.monkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 */
//@Configuration
//@EnableAutoConfiguration
//@EnableDiscoveryClient
//@EnableFeignClients
//@EnableCircuitBreaker
//@EnableHystrix//add by allen
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
//@EnableMongoRepositories("me.monkey.modules.system.repository.mongodb")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    
    
}
