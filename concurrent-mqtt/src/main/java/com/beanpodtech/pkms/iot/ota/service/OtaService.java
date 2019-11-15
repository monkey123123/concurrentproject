package com.beanpodtech.pkms.iot.ota.service;

import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqCommonMessage;
import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqResponse;

import com.beanpodtech.pkms.common.model.*;

import com.beanpodtech.pkms.common.param.UserSessionParam;
import com.beanpodtech.pkms.common.param.mqtt.MqttParam;
import com.beanpodtech.pkms.iot.ota.mqtt.reconnect.MyMQTTClient;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Constant;
import com.beanpodtech.pkms.iot.ota.mqtt.util.DataUtil;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.ByteOrder;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
public interface OtaService{


    /**
     * 
     * @param param
     * @return
     * @throws Exception
     */
    public RabbitMqResponse pushInstallMessage(RabbitMqCommonMessage msg) throws Exception;
    public RabbitMqResponse push04(RabbitMqCommonMessage msg) throws Exception;

    
}
