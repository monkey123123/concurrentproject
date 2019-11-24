package com.beanpodtech.pkms.iot.ota.mqtt.reconnect;

 
//import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTDeviceImageRepository;
import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTImageRepository;
import com.beanpodtech.pkms.iot.ota.service.IoTDeviceImageService;
import com.beanpodtech.pkms.iot.ota.service.IoTImageService;

/**
 * 
 **/
//@Slf4j
@Configuration
public class MyMQTTConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyMQTTConfiguration.class);
 
    @Value("${mqtt.host}")
    String host;
    @Value("${mqtt.username}")
    String username;
    @Value("${mqtt.password}")
    String password;
    @Value("${mqtt.clientId}")
    String clientId;
    @Value("${mqtt.timeout}")
    int timeOut;
    @Value("${mqtt.keepalive}")
    int keepAlive;
    @Value("${mqtt.waitSeconds}")
    long waitSeconds;

    
    @Value("${caFilePath}")
    String caFilePath;
    @Value("${clientCrtFilePath}")
    String clientCrtFilePath;
    @Value("${clientKeyFilePath}")
    String clientKeyFilePath;
    
    @Autowired
    private IoTDeviceImageService deviceImageService;
    
    @Autowired
    private IoTDeviceImageRepository deviceImageRepository;
    
    @Autowired
    private IoTImageService imageService;
    
    @Autowired
    private IoTImageRepository imageRepository;
    
    @Value("${uploadPath}")
    private String uploadPath;
    
    
    @Bean//注入spring
    public MyMQTTClient myMQTTClient() {
        MyMQTTClient myMQTTClient = new MyMQTTClient(
            host,
            clientId,
            username,
            password,
            timeOut,
            keepAlive,
            waitSeconds,
            caFilePath, clientCrtFilePath, clientKeyFilePath,
            deviceImageService,
            deviceImageRepository,
            imageService,
            imageRepository,
            uploadPath
        );
        /*
        try {
			myMQTTClient.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return myMQTTClient;*/
        
        for (int i = 0; i < 10; i++) {
            try {
                myMQTTClient.connect();
                return myMQTTClient;
            } catch (Exception e) {
            	e.printStackTrace();
            	LOGGER.error("MQTT connect exception,connect time = " + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return myMQTTClient;
    }
 
}