package com.beanpodtech.pkms.iot.ota.mqtt.reconnect;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.util.encoders.Hex;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Constant;
import com.beanpodtech.pkms.iot.ota.mqtt.util.DataUtil;
import com.beanpodtech.pkms.iot.ota.mqtt.util.Message;
import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTDeviceImageRepository;
import com.beanpodtech.pkms.iot.ota.repository.mongodb.IoTImageRepository;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTDeviceImage;
import com.beanpodtech.pkms.iot.ota.repository.pojo.IoTImage;
import com.beanpodtech.pkms.iot.ota.service.IoTDeviceImageService;
import com.beanpodtech.pkms.iot.ota.service.IoTImageService;
 
/**
 * 
 **/
public class MyMQTTCallback implements MqttCallback {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMQTTCallback.class);
 
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
    
    private MyMQTTClient myMQTTClient;
 
    public MyMQTTCallback(MyMQTTClient myMQTTClient) {
        this.myMQTTClient = myMQTTClient;
    }

	@Override
    public void connectionLost(Throwable cause) {
        System.out.println("connectionLost---------");
        stop();//关闭
        start();//重新连接
    }

//	@Override
    public void messageArrived3(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("***** get message start *****");
        System.out.println("topic:" + topic);
        System.out.println("Qos:" + mqttMessage.getQos());
        System.out.println("message : " + new String(mqttMessage.getPayload()));
	}
	
	//以下方法是正式方法，为不影响沈阳终端测试暂时注掉
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("***** get message start *****");
        System.out.println("topic:" + topic);
        System.out.println("Qos:" + mqttMessage.getQos());
        System.out.println("message : " + new String(mqttMessage.getPayload()));
        
     // subscribe后得到的消息会执行到这里面
        LOGGER.info("messageArrived接收消息主题 : {}，接收消息内容 : {}", topic, Hex.toHexString(mqttMessage.getPayload()));
        LOGGER.info("接收消息字节数 {}: " , mqttMessage.getPayload().length);
        
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + mqttMessage.getQos());
        
        try {
        	int a=0/0;
        }catch (ArithmeticException e) {
			e.printStackTrace();
		}
        
        boolean going = false;
        byte[] payload = mqttMessage.getPayload();
        try {
        	going = Constant.checkMessage(payload);
        }catch (Exception e) {
			e.printStackTrace();
		}
        if(going) {
        	msgProcess(topic, mqttMessage);
        }else {
        	LOGGER.error("报文检查未通过，往下执行测试报文");
        	test(topic, mqttMessage);
        }
        
        System.out.println("***** get message end *****");
    }
    
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("MessageId: "+token.getMessageId()+ " , Topics: "+token.getTopics()+", deliveryComplete---------" + token.isComplete());
    }
    

    public void stop() {
        try {
            // 断开连接
        	myMQTTClient.getClient().disconnect();
            // 关闭客户端
        	myMQTTClient.getClient().close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            while (true) {
            	try {//先暂停5秒可防止频繁连接
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
                    if (!myMQTTClient.getClient().isConnected()) {
                        System.out.println("***** client to connect *****");
                        myMQTTClient.getClient().connect(myMQTTClient.getMqttConnectOptions());
                    }
                    if (myMQTTClient.getClient().isConnected()) {//连接成功，跳出连接
                        System.out.println("***** connect success *****");
                        break;
                    }
                } catch (MqttException e1) {
                    e1.printStackTrace();
                }
                
            }
            //订阅消息 明天改成动态获取
            String topicName4 = "mqtt/server/topic";
            myMQTTClient.getClient().subscribe(topicName4, 2);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void msgProcess(String topic, MqttMessage mqttMessage) {
    	// subscribe后得到的消息会执行到这里面
        LOGGER.info("msgProcess接收消息主题 : {}，接收消息内容 : {}", topic, new String(mqttMessage.getPayload()));
        
        boolean going = false;
        byte[] payload = mqttMessage.getPayload();
        try {
        	going = Constant.checkMessage(payload);
        }catch (Exception e) {
			e.printStackTrace();
		}
        if(!going) {
        	LOGGER.error("报文检查未通过");//直接结束，不能让错误报文影响功能
        	return;
        }
        
        if(topic.startsWith("mqtt/server/topic")) {
        	//根据协议 最小报文头是14
        	if(payload != null && payload.length >= 14) {
        		byte[] mag = ArrayUtils.subarray(payload, 0, 4);
        		
        		Message m2 = new Message(payload);
        		int magic = DataUtil.bytes2Int(m2.getMagic(), ByteOrder.BIG_ENDIAN);
        		//模数一样才需要处理，否则不需要处理
        		if(magic == Constant.magic) {
        			short version = DataUtil.bytes2Short(m2.getVersion(), ByteOrder.BIG_ENDIAN);
        			short cmd = DataUtil.bytes2Short(m2.getCmd(), ByteOrder.BIG_ENDIAN);
        			
        			if(version != Constant.version) {
            			System.out.println("协议版本不对，结束执行。。。");
            			return;
            		}
        			
        			switch (cmd) {
					case 0x0001://镜像版本上传 Node ==> Server，云端收到后，判断有没有新版本镜像，有则下发，无则忽略
						System.out.println("run in 0x0001-------------------------------------------");
						byte[] uuidAndVer = m2.getData();
						byte[] uuid = new byte[16];
						System.arraycopy(uuidAndVer, 0, uuid, 0, uuid.length);
						byte[] ver = new byte[4];
						System.arraycopy(uuidAndVer, 16, ver, 0, ver.length);
						int receiveVer = DataUtil.bytes2Int(Hex.decode(ver), ByteOrder.BIG_ENDIAN);
						
						
						LOGGER.debug("接收到的报文中镜像版本："+Hex.toHexString(ver));
						//此时应去数据库查询有没有新镜像，代码暂时略去
						//该响应什么消息？
						
						IoTImage maxVersionImage = null;
						List<IoTImage> images = imageService.queryAll();
						for(IoTImage temp : images) {
							if(StringUtils.hasText(temp.getImagefilepath())) {
								int versionTemp = DataUtil.bytes2Int(Hex.decode(temp.getImageversion()), ByteOrder.BIG_ENDIAN);
								if(versionTemp > receiveVer) {
									receiveVer = versionTemp;
									maxVersionImage = temp;
								}
							}
						}
						
						if(maxVersionImage != null) {
							String absolutePath = uploadPath + maxVersionImage.getImagefilepath();
//							byte[] imageBytes = Constant.fileToBytes(Constant.signedImagePath);
							byte[] imageBytes = Constant.fileToBytes(absolutePath);
							if(imageBytes == null || imageBytes.length == 0) {
								System.out.println("出错了------------------imageBytes.length =="+imageBytes.length);
								return;
							}
							
							short pnum = 0;
							if(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE == 0) {
								pnum = (short) (imageBytes.length / Constant.MAX_SIZE_SINGLE_PACKAGE);
							}else {
								pnum = (short) (imageBytes.length / Constant.MAX_SIZE_SINGLE_PACKAGE +1);
							}
							
							byte[] pnumBytes = DataUtil.short2Bytes(pnum, ByteOrder.BIG_ENDIAN);
							int offset = 0;//数据偏移量
							for(int i =1; i <= pnum; i++) {
								short packagedatalength = (short)0;
								if(i < pnum) {
									packagedatalength = Constant.MAX_SIZE_SINGLE_PACKAGE;
								}else {
									if(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE == 0) {
										packagedatalength = Constant.MAX_SIZE_SINGLE_PACKAGE;
									}else {
										packagedatalength = (short)(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE);
									}
								}
								byte[] packagedata = new byte[packagedatalength];
								System.arraycopy(imageBytes, offset, packagedata, 0, packagedatalength);
								offset += packagedatalength;
								
								byte[] packageorder = DataUtil.short2Bytes((short)i, ByteOrder.BIG_ENDIAN);
								Message newMessage = new Message(
										Constant.magicByte, 
										Constant.versionByte, 
										Constant.CMD_Download_Image,
										pnumBytes, 
										packageorder, 
										DataUtil.short2Bytes(packagedatalength, ByteOrder.BIG_ENDIAN), 
										packagedata);
								//此处应是动态topic 该给谁发给谁发
								String topic2 = "mqtt/client/123456";
								myMQTTClient.publish(newMessage.toBytes(), topic2, Constant.QOS_2, Constant.Retained);
								
							}
							
							
							//推送完毕后，执行数据库保存

							//查询db
							IoTDeviceImage dbDI = null;
							IoTDeviceImage deviceImage = new IoTDeviceImage();
							deviceImage.setDeviceid(Hex.toHexString(uuid));
							List<IoTDeviceImage> list = deviceImageService.queryListNoPage(deviceImage);
							if(list !=null && list.size() > 0) {
								if(list.size() > 1) {
						    		System.out.println("--------------数据错误，多余1条，deviceid="+Hex.toHexString(uuid));
						    	}
								dbDI = list.get(0);
								
								dbDI.setImageid(maxVersionImage.getId());
								dbDI.setStatus(Constant.STATUS_HAVE_NEW);
								deviceImageRepository.save(dbDI);
							}else {
								dbDI = new IoTDeviceImage();
								IoTImage image = new IoTImage();
					    		image.setId("xuchp");
					    		image.setInputusername("xuchp");
					    		dbDI.setImage(image);//测试
					    		dbDI.setStatus(Constant.STATUS_HAVE_NEW);
					    		deviceImageRepository.save(dbDI);
							}
							
							//镜像推送完毕后，下发安装指令
							/*
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
							*/
						}
						break;
					case 0x0002://镜像传输指令 Server ==> Node 不可能收到该指令类型的报文
						break;
					case 0x0003://镜像更新指令Server ==> Node 不可能收到该指令类型的报文
						break;
					case 0x0004://镜像更新指令Node <==> Server
						System.out.println("run in 0x0004-------------------------------------------");
						byte[] statusCode = m2.getData();
						int receiveStatusCode = DataUtil.bytes2Int(statusCode, ByteOrder.BIG_ENDIAN);
						if(receiveStatusCode == Constant.STATUS_CODE_SUCCESS) {
							
							String tempImageId = null;
							//查询db
							IoTDeviceImage dbDI = null;
							IoTDeviceImage deviceImage = new IoTDeviceImage();
							deviceImage.setDeviceid(Constant.DEVICE_ID);
							List<IoTDeviceImage> list = deviceImageService.queryListNoPage(deviceImage);
							if(list !=null && list.size() > 0) {
								if(list.size() > 1) {
									System.out.println("--------------数据错误，多于1条，deviceid=默认");
								}
								dbDI = list.get(0);
								dbDI.setStatus(Constant.STATUS_HAVE_NOT_NEW);
								deviceImageRepository.save(dbDI);
								tempImageId = dbDI.getImageid();
							}else {
								System.out.println("--------------数据错误，查到device_image记录0条，deviceid=默认");
								return;
							}
							
							Optional<IoTImage> optional = imageRepository.findById(tempImageId);
							IoTImage tempIoTImage = optional.get();
							
							//检查更新记录中更新的是哪个版本，把版本号下发
							
							//告知终端它更新的是哪个版本
							Message imageVersionMessage = new Message(
									Constant.magicByte, 
									Constant.versionByte, 
									Constant.CMD_INSTALL_Image_VERSION,
									new byte[]{0,0}, 
									new byte[]{0,0}, 
									DataUtil.short2Bytes((short)0, ByteOrder.BIG_ENDIAN), 
									Hex.decode(tempIoTImage.getImageversion()));
							//此处应是动态topic 该给谁发给谁发
							String topic5 = "mqtt/client/123456";
							myMQTTClient.publish(imageVersionMessage.toBytes(), topic5, Constant.QOS_2, Constant.Retained);
							
						}
						
						break;
					default:
						LOGGER.info("接收到未定义命令类型的指令");
						break;
					}
        		}
        			
    		}else {
    			LOGGER.info("报文头小于14 没用");
    		}
        	
        		
        		
        }else {
        	LOGGER.info("收到未处理主题："+topic);
        }
        
        
    }
 
    /******************************以下为测试代码**********************************/
    private void test(String topic, MqttMessage mqttMessage) {
    	
    	if(topic.startsWith("mqtt/server/topic")) {
    		String receiveMsg = new String(mqttMessage.getPayload());
    		
    		String packageFilePath = Constant.signedImagePath;
    		switch (receiveMsg) {
			case "package1":
				packageFilePath = Constant.signedImagePath1;
				break;
			case "package2":
				packageFilePath = Constant.signedImagePath2;
				break;
			case "package10":
				packageFilePath = Constant.signedImagePath10;
				break;
			case "package50":
				packageFilePath = Constant.signedImagePath50;
				break;
			case "package193":
				packageFilePath = Constant.signedImagePath193;
				break;
			case "push04":
				try {
					push04();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;


			default:
				break;
			}
    		
    		
			byte[] imageBytes = Constant.fileToBytes(packageFilePath);
			
			//以上代码属于临时改造测试代码
//			byte[] imageBytes = Constant.fileToBytes(Constant.signedImagePath);
			
			
			short pnum = 0;
			if(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE == 0) {
				pnum = (short) (imageBytes.length / Constant.MAX_SIZE_SINGLE_PACKAGE);
			}else {
				pnum = (short) (imageBytes.length / Constant.MAX_SIZE_SINGLE_PACKAGE +1);
			}
			
			byte[] pnumBytes = DataUtil.short2Bytes(pnum, ByteOrder.BIG_ENDIAN);
			int offset = 0;//数据偏移量
			for( int i =1; i <= pnum; i++) {
				short packagedatalength = (short)0;
				if(i < pnum) {
					packagedatalength = Constant.MAX_SIZE_SINGLE_PACKAGE;
				}else {
					if(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE == 0) {
						packagedatalength = Constant.MAX_SIZE_SINGLE_PACKAGE;
					}else {
						packagedatalength = (short)(imageBytes.length % Constant.MAX_SIZE_SINGLE_PACKAGE);
					}
				}
				byte[] packagedata = new byte[packagedatalength];
				System.arraycopy(imageBytes, offset, packagedata, 0, packagedatalength);
				offset += packagedatalength;
				
				byte[] packageorder = DataUtil.short2Bytes((short)i, ByteOrder.BIG_ENDIAN);
				Message newMessage = new Message(
						Constant.magicByte, 
						Constant.versionByte, 
						Constant.CMD_Download_Image,
						pnumBytes, 
						packageorder, 
						DataUtil.short2Bytes(packagedatalength, ByteOrder.BIG_ENDIAN), 
						packagedata);
				
				String topic2 = "mqtt/client/123456";
				myMQTTClient.publish(newMessage.toBytes(), topic2, Constant.QOS_2, Constant.Retained);
				
			}
			
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
			
    	}else {
    		System.out.println("MyMQTTClient.test(): 非mqtt/server/topic,------------ topic = : "+topic);
    	}
    	
    }
    
    
    public void push04() throws Exception {
    	System.out.println("pushInstallMessage come on (data) " );
    	
    	//告知终端它更新的是哪个版本
		Message imageVersionMessage = new Message(
				Constant.magicByte, 
				Constant.versionByte, 
				Constant.CMD_INSTALL_ASK_VERSION,
				new byte[]{0,0}, 
				new byte[]{0,0}, 
				DataUtil.short2Bytes((short)0, ByteOrder.BIG_ENDIAN), 
				null);
		//此处应是动态topic 该给谁发给谁发
		String topic2 = "mqtt/client/123456";
		myMQTTClient.publish(imageVersionMessage.toBytes(), topic2, Constant.QOS_2, Constant.Retained);
		
		
		String tempImageId = null;
		//查询db
		IoTDeviceImage dbDI = null;
		IoTDeviceImage deviceImage = new IoTDeviceImage();
		deviceImage.setDeviceid(Constant.DEVICE_ID);
		List<IoTDeviceImage> list = deviceImageService.queryListNoPage(deviceImage);
		if(list !=null && list.size() > 0) {
			if(list.size() > 1) {
				System.out.println("--------------数据错误，多于1条，deviceid=默认");
			}
			dbDI = list.get(0);
			dbDI.setStatus(Constant.STATUS_HAVE_NOT_NEW);
			deviceImageRepository.save(dbDI);
			tempImageId = dbDI.getImageid();
		}else {
			System.out.println("--------------数据错误，查到device_image记录0条，deviceid=默认");
			return;
		}
		
		Optional<IoTImage> optional = imageRepository.findById(tempImageId);
		IoTImage tempIoTImage = optional.get();
		System.out.println("---------------4444---------------------"+JSONObject.toJSONString(tempIoTImage));
		
    }
    
}
//原文链接：https://blog.csdn.net/yzh_1346983557/article/details/85059524