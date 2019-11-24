package com.beanpodtech.pkms.iot.ota.mqtt.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;

public class Constant {
	//Qos质量 2
	public static final int QOS_0 = 0;
	public static final int QOS_1 = 1;
	public static final int QOS_2 = 2;
	//Retain-Flag（保持标志）
	//该标志确定代理是否持久保存某个特定主题的消息。订阅该主题的新客户端将在订阅后立即收到该主题的最后保留消息。
	// true保留 false不保留，如果保留可能会导致mosquitto broker中存储过多消息，client连上的时候一次性收到大量历史消息
	public static final boolean Retained = false;
	//模数
	public static int magic = 0x19977991;
	//终端要求大端对齐
	public static final byte[] magicByte = {0x19, (byte) 0x97, 0x79, (byte) 0x91};
	//版本号 协议里的版本号 2字节 不要与报文里的版本号4字节混淆了
	public static final int version = 0x0001;
	public static final boolean TEST = true;
	public static final byte[] versionByte = {0x00, 0x01};
	//
	public static final short MAX_SIZE_SINGLE_PACKAGE = 1024;
	public static final byte[] CMD_Download_Image = {0x00, 0x02};
	public static final byte[] CMD_INSTALL_Image = {0x00, 0x03};
	public static final byte[] CMD_INSTALL_ASK_VERSION = {0x00, 0x04};
	public static final byte[] CMD_INSTALL_Image_VERSION = {0x00, 0x05};
	public static final int maxInflight = 20000;
	
	//有新的镜像已推送 03指令已经推下去
	public static final int STATUS_CAN_ASK_VERSION = 3;
	//有新的镜像已推送 02指令已经推下去
	public static final int STATUS_HAVE_NEW = 2;
	public static final int STATUS_HAVE_NOT_NEW = 1;
	public static final int ONLINE_YES = 1;
	public static final int ONLINE_NO = 0;
	public static final long WAIT_SECONDS = 20L;
	public static final int STATUS_CODE_SUCCESS = 0x00000002;
	//重连间隔5秒
	public static final long CONNECT_INTERNAL = 5000;
	//
	public static String signedImagePath = "E:/mqtt/ceshi/test.sb2";
	public static String signedImagePath1 = "E:/mqtt/ceshi/test.sb21";
	public static String signedImagePath2 = "E:/mqtt/ceshi/test.sb22";
	public static String signedImagePath10 = "E:/mqtt/ceshi/test.sb210";
	public static String signedImagePath50 = "E:/mqtt/ceshi/test.sb250";
	public static String signedImagePath193 = "E:/mqtt/ceshi/test.sb2193";
	public static String DEVICE_ID = "dae432fcd31744429b01c992d56d71bf";
	public static String TOPIC_WILL = "/lwt";
	public static String TOPIC_Server = "mqtt/server/topic";
	public static String TOPIC_HEART = "mqtt/server/heart";

	public static void main(String[] args) {
		/*
		magic = 0x19977991;
		byte[] magicByte = DataUtil.int2Bytes(magic, ByteOrder.LITTLE_ENDIAN);
		System.out.println(Hex.toHexString(magicByte));// 91799719
		byte[] magicByte2 = DataUtil.int2Bytes(magic, ByteOrder.BIG_ENDIAN);
		System.out.println(Hex.toHexString(magicByte2));// 19977991
		*/
		
		String hex = "1997799100010001000000001400dae432fcd31744429b01c992d56d71bf00010001";
		boolean f = checkMessage(Hex.decode(hex));
		System.out.println(f);
	}

	public static String zhifuUrl; 
	@Value("${signedImagePath}")
	public void setZhifuUrl(String url) {
		Constant.signedImagePath = url;
	}


	
	
	public static byte[] fileToBytes(String filePath) {
        byte[] buffer = null;
        File file = new File(filePath);
        
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;

        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();

            byte[] b = new byte[1024];

            int n;

            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            
            buffer = bos.toByteArray();
        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
//            Logger.getLogger(JmsReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        	ex.printStackTrace();
//            Logger.getLogger(JmsReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException ex) {
            	ex.printStackTrace();
//                Logger.getLogger(JmsReceiver.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    if(null!=fis){
                        fis.close();
                    }
                } catch (IOException ex) {
                	ex.printStackTrace();
//                    Logger.getLogger(JmsReceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return buffer;
    }
	
	public static void bytesToFile(byte[] buffer, final String filePath){

        File file = new File(filePath);

        OutputStream output = null;
        BufferedOutputStream bufferedOutput = null;

        try {
            output = new FileOutputStream(file);

            bufferedOutput = new BufferedOutputStream(output);

            bufferedOutput.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(null!=bufferedOutput){
                try {
                    bufferedOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(null != output){
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
	
	
	/*
     * bytes应该是84字节
     */
    public static boolean checkMessage(byte[] bytes) {
    	
    	int offset = 0;
		byte[] temp = new byte[4];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] magic = temp;
		if(!Arrays.equals(temp, Constant.magicByte)) {
			System.out.println("模数不对");
			return false;
		}
		
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] version = temp;
		
		if(!Arrays.equals(temp, Constant.versionByte)) {
			System.out.println("版本号不对");
			return false;
		}
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] cmd = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] packagenum = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] packageorder = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] length = temp;
		
		//稍后放在外面检查
		short len = DataUtil.bytes2Short(temp, ByteOrder.BIG_ENDIAN);
		if(len > Constant.MAX_SIZE_SINGLE_PACKAGE) {//长度不规范
			return false;
		}else if(offset + len != bytes.length) {//长度不规范
			return false;
		}
		
		temp = new byte[len];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		byte[] data = temp;
		
		return true;
	}
	
	
}
