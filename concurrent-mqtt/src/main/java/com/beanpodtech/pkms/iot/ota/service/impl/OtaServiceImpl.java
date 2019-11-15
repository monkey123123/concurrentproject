package com.beanpodtech.pkms.iot.ota.service.impl;

import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqCommonMessage;
import com.beanpodtech.pkms.common.message.rabbitmq.RabbitMqResponse;

import com.beanpodtech.pkms.common.param.mqtt.MqttParam;
import com.beanpodtech.pkms.iot.ota.mqtt.reconnect.MyMQTTClient;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Constant;
import com.beanpodtech.pkms.iot.ota.mqtt.util.DataUtil;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Message;
import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTDeviceImageRepository;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDeviceImage;
import com.beanpodtech.pkms.iot.ota.service.IoTDeviceImageService;
import com.beanpodtech.pkms.iot.ota.service.OtaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.ByteOrder;
import java.util.List;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.detDSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 */
@Service
public class OtaServiceImpl  implements OtaService{

    protected final Logger logger = LoggerFactory.getLogger(OtaServiceImpl.class);

    @Autowired
    private MyMQTTClient myMQTTClient;
    @Autowired
    private IoTDeviceImageService deviceImageService;
    @Autowired
    private IoTDeviceImageRepository deviceImageRepository;

    /**
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public RabbitMqResponse pushInstallMessage(RabbitMqCommonMessage msg) throws Exception {
    	String param = msg.getParam();
    	System.out.println("OtaServiceImpl.pushInstallMessage():------------param:"+param);
        ObjectMapper mapper = new ObjectMapper();
        MqttParam mqttParam = mapper.readValue(param, MqttParam.class);
        IoTDeviceImage dbDeviceImage = null;
        if(mqttParam != null && !StringUtils.isEmpty(mqttParam.getDeviceid()) && !StringUtils.isEmpty(mqttParam.getImageid())) {
        	//查询db
        	IoTDeviceImage deviceImage = new IoTDeviceImage();
        	deviceImage.setDeviceid(mqttParam.getDeviceid());
        	List<IoTDeviceImage> list = deviceImageService.queryListNoPage(deviceImage);
        	if(list !=null && list.size() > 0) {
        		dbDeviceImage = list.get(0);
        	}
        }
        if(dbDeviceImage != null) {
        	
        	//向某设备推送镜像
        	
        	//镜像推送完毕后，下发安装指令
        	Message installMessage = new Message(
        			Constant.magicByte, 
        			Constant.versionByte, 
        			Constant.CMD_INSTALL_Image,
        			new byte[]{0,0}, 
        			new byte[]{0,0}, 
        			DataUtil.short2Bytes((short)0, ByteOrder.BIG_ENDIAN), 
        			null);
        	//此处应是动态topic 该给谁发给谁发
        	String topic2 = "mqtt/client/123456";
        	myMQTTClient.publish(installMessage.toBytes(), topic2, Constant.QOS_2, Constant.Retained);
        	
        	
        	//推送完之后要改成 允许问询版本
        	dbDeviceImage.setStatus(Constant.STATUS_CAN_ASK_VERSION);
        	dbDeviceImage.setCurrentimageid(dbDeviceImage.getImageid());
        	deviceImageRepository.save(dbDeviceImage);
        	
        	myMQTTClient.push04();
        }
		
		
		
        RabbitMqResponse response = new RabbitMqResponse();
        response.setErrorCode(0);
        response.setRtnValue(null);
        return response;
    }
    /**
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public RabbitMqResponse push04(RabbitMqCommonMessage msg) throws Exception {
    	String param = msg.getParam();
    	System.out.println("OtaServiceImpl.push04():------------param:"+param);
    	ObjectMapper mapper = new ObjectMapper();
    	MqttParam mqttParam = mapper.readValue(param, MqttParam.class);
    	IoTDeviceImage dbDI = null;
    	if(mqttParam != null && !StringUtils.isEmpty(mqttParam.getDeviceid()) && !StringUtils.isEmpty(mqttParam.getImageid())) {
    		//查询db
    		IoTDeviceImage deviceImage = new IoTDeviceImage();
    		deviceImage.setDeviceid(mqttParam.getDeviceid());
    		List<IoTDeviceImage> list = deviceImageService.queryListNoPage(deviceImage);
    		if(list !=null && list.size() > 0) {
    			if(list.size() > 1) {
    				System.out.println("--------------数据错误，多于1条，deviceid="+mqttParam.getDeviceid());
    			}
    			dbDI = list.get(0);
//    			dbDI.setStatus(Constant.STATUS_CAN_ASK_VERSION);
//    			deviceImageRepository.save(dbDI);
    		}else {
    			System.out.println("--------------数据错误，查到device_image记录0条，deviceid="+mqttParam.getDeviceid());
    		}
    		
    	}
    	if(dbDI != null) {
    		
    		//向某设备推送镜像
    		
    		//镜像推送完毕后，下发安装指令
    		Message installMessage = new Message(
    				Constant.magicByte, 
    				Constant.versionByte, 
    				Constant.CMD_INSTALL_ASK_VERSION,
    				new byte[]{0,0}, 
    				new byte[]{0,0}, 
    				DataUtil.short2Bytes((short)0, ByteOrder.BIG_ENDIAN), 
    				null);
    		//此处应是动态topic 该给谁发给谁发
    		String topic2 = "mqtt/client/123456";
    		myMQTTClient.publish(installMessage.toBytes(), topic2, Constant.QOS_2, Constant.Retained);
    		
//    		dbDI.setStatus(Constant.STATUS_HAVE_NOT_NEW);
//    		deviceImageRepository.save(dbDI);
    	}
		
    	RabbitMqResponse response = new RabbitMqResponse();
    	response.setErrorCode(0);
    	response.setRtnValue(null);
    	return response;
    	
    }

    
}
