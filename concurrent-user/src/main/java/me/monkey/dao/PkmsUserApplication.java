package me.monkey.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
//@EnableHystrix
@SpringBootApplication
public class PkmsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PkmsUserApplication.class, args);
	}
}
