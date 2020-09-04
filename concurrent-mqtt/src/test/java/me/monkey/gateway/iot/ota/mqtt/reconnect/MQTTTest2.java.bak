package me.monkey.iot.ota.mqtt.reconnect;

 
import java.nio.ByteOrder;

import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import me.monkey.iot.ota.OtaApplication;
import me.monkey.iot.ota.mqtt.util.Constant;
import me.monkey.iot.ota.mqtt.util.DataUtil;
import me.monkey.iot.ota.mqtt.util.Message;
 
/**
 * 
 */
@SpringBootTest(classes = OtaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MQTTTest2 {
 
    @Autowired
    private MyMQTTClient myMQTTClient;
 /*
    @Test
    public void testProduce() throws Exception {
        long l = System.nanoTime();
        for (int i = 0; i < 20000; i++) {
            String topicName = "topic11";
            //myMQTTClient.subscribe(topicName);
            myMQTTClient.publish(topicName + "发送消息" + i, topicName);
        }
        long l1 = System.nanoTime();
        double d = ((double) (l1 - l)) / 1000000000;
        long l2 = System.nanoTime();
        for (int i = 0; i < 20000; i++) {
            String topicName = "topic22";
            //myMQTTClient.subscribe(topicName);
            myMQTTClient.publish(topicName + "发送消息" + i, topicName);
        }
        double d2 = ((double) (System.nanoTime() - l2)) / 1000000000;
        System.err.println("=====================第一个topic发送2万数据花费时间：=================" + d + "秒");//2秒多
        System.err.println("=====================第二个topic发送2万数据花费时间：=================" + d2 + "秒");
    }
 
    */
    /**
     * 分级发布与订阅
     * @throws Exception
     */
    @Test
    public void testTopic() throws Exception {
        int count = 10;
        String topicName1 = "topic33/type/name";
        String topicName2 = "topic33/type";
        String topicName3 = "topic33";
        String topicName4 = "mqtt/server/topic";
        String topicName5 = "mqtt/client/123456";
//      myMQTTClient.subscribe(topicName3+"/#");
        myMQTTClient.subscribe(topicName4, 0);
        myMQTTClient.subscribe(topicName5, 0);
        /*
        for (int i = 0; i < count; i++) {
            myMQTTClient.publish(topicName1 + "发送消息" + i, topicName1);
        }
        for (int i = 0; i < count; i++) {
            myMQTTClient.publish(topicName2 + "发送消息" + i, topicName2);
        }
        for (int i = 0; i < count; i++) {
            myMQTTClient.publish(topicName3 + "发送消息" + i, topicName3);
        }
        */
//        for (int i = 0; i < count; i++) {
//        	myMQTTClient.publish(topicName4 + "发送消息" + i, topicName4);
//        }
        
//        test();
		
        
        while (true) {
			Thread.sleep(1000);
		}
    }
    
    private void test() {
    	String topicName5 = "mqtt/client/123456777";
    	
    	String signedImagePath = "E:/dj/us6/iot_second0924/张志阳/ceshi/test.sb2.old";
//		byte[] imageBytes = Constant.fileToBytes(Constant.signedImagePath);
		byte[] imageBytes = Constant.fileToBytes(signedImagePath);
		
		
		short pnum = 0;
		if(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE == 0) {
			pnum = (short) (imageBytes.length / Constant.MAX_SIZE_SINGLE_PACKAGE);
		}else {
			pnum = (short) (imageBytes.length / Constant.MAX_SIZE_SINGLE_PACKAGE +1);
		}
		
		byte[] packagenum = DataUtil.short2Bytes(pnum, ByteOrder.BIG_ENDIAN);
		byte[] cmdDownload = Constant.CMD_Download_Image;
		for(int i =1; i <= pnum; i++) {
			byte[] packageorder = DataUtil.short2Bytes((short)i, ByteOrder.BIG_ENDIAN);
			Message newMessage = new Message(
					Constant.magicByte, 
					Constant.versionByte, 
					cmdDownload,
					packagenum, 
					packageorder, 
					DataUtil.short2Bytes((short)imageBytes.length, ByteOrder.BIG_ENDIAN), 
					imageBytes);
			
			myMQTTClient.publish(newMessage.toBytes(), topicName5, 2, false);
			System.out.println("已发送：-----------------------------------------------------#####################################："+i);
		}
		
    }
    
    
 
}