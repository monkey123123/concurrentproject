package com.beanpodtech.pkms.iot.ota;

import com.beanpodtech.pkms.iot.ota.mqtt.reconnect.MyMQTTClient;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@SpringBootApplication
@EnableScheduling
@EnableMongoRepositories("com.beanpodtech.pkms.iot.ota.repository.mongodb")
public class OtaApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(OtaApplication.class, args);
    	MyMQTTClient myMQTTClient = context.getBean(MyMQTTClient.class);
        String topicName4 = "mqtt/server/topic";
        myMQTTClient.subscribe(topicName4, Constant.QOS_2);
        if(Constant.TEST) {
//			String topicName5 = "mqtt/client/123456";
//			myMQTTClient.subscribe(topicName5, Constant.QOS_2);
//			String topicName7 = "$SYS/#";
//			myMQTTClient.subscribe(topicName7, Constant.QOS_2);
		}
		String topicName6 = "/lwt";
        myMQTTClient.subscribe(topicName6, Constant.QOS_2);
        myMQTTClient.subscribe(Constant.TOPIC_HEART, Constant.QOS_2);
	}


}
