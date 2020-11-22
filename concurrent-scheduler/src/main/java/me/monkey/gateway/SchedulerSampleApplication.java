package me.monkey.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.web.client.RestTemplate;

/**
 * 主类
 *
 * @author tjq
 * @since 2020/4/17
 */

//@EnableFeignClients
//@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class SchedulerSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchedulerSampleApplication.class, args);
    }
//    @Autowired
//    private RestTemplateBuilder builder;

//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return builder.build();
//    }
}
