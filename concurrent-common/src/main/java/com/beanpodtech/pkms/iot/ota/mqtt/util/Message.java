package com.beanpodtech.pkms.iot.ota.mqtt.util;

import java.nio.ByteOrder;

import org.apache.commons.lang3.ArrayUtils;

public class Message {
    private byte[] magic;//4字节
    private byte[] version;//2字节
    private byte[] cmd;//2字节
    private byte[] packagenum;//2字节
    private byte[] packageorder;//2字节
    private byte[] length;//2字节
    private byte[] data;//<=1024字节
    
    /*
     * bytes应该是84字节
     */
    public Message(byte[] bytes) {
    	int offset = 0;
		byte[] temp = new byte[4];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		this.magic = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		this.version = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		this.cmd = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		this.packagenum = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		this.packageorder = temp;
		
		temp = new byte[2];
		System.arraycopy(bytes, offset, temp, 0, temp.length);
		offset += temp.length;
		this.length = temp;
		
		//稍后放在外面检查
		short len = DataUtil.bytes2Short(this.length, ByteOrder.BIG_ENDIAN);
		if(offset + len != bytes.length) {
			
		}
		if(len == 0) {
			this.data = null;
		}else {
			temp = new byte[len];
			System.arraycopy(bytes, offset, temp, 0, temp.length);
			offset += temp.length;
			this.data = temp;
		}
	}
    
    public byte[] toBytes() {
		return ArrayUtils.addAll(
				magic,
				ArrayUtils.addAll(version,
					ArrayUtils.addAll(cmd,
						ArrayUtils.addAll(packagenum,
							ArrayUtils.addAll(packageorder,
									data==null? length : ArrayUtils.addAll(length, data)
							)
						)
					)
				)
			); 
			
    }
    
	public Message() {
		super();
	}
	public Message(
		byte[] magic,
	    byte[] version,
	    byte[] cmd,
	    byte[] packagenum,
	    byte[] packageorder,
	    byte[] length,
	    byte[] data) {
		super();
		
		this.magic = magic;
		this.version = version;
		this.cmd = cmd;
		this.packagenum = packagenum;
		this.packageorder = packageorder;
		this.length = length;
		this.data = data;
	}

	public byte[] getMagic() {
		return magic;
	}

	public void setMagic(byte[] magic) {
		this.magic = magic;
	}

	public byte[] getVersion() {
		return version;
	}

	public void setVersion(byte[] version) {
		this.version = version;
	}

	public byte[] getCmd() {
		return cmd;
	}

	public void setCmd(byte[] cmd) {
		this.cmd = cmd;
	}

	public byte[] getPackagenum() {
		return packagenum;
	}

	public void setPackagenum(byte[] packagenum) {
		this.packagenum = packagenum;
	}

	public byte[] getPackageorder() {
		return packageorder;
	}

	public void setPackageorder(byte[] packageorder) {
		this.packageorder = packageorder;
	}

	public byte[] getLength() {
		return length;
	}

	public void setLength(byte[] length) {
		this.length = length;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	/*
	public byte[] genBytesForSign() {
		byte[] signBefore = ArrayUtils.addAll(
			new byte[] {ProtocolVersion.version},
			ArrayUtils.addAll(new byte[] {MessageType.MessageType_C1},
				ArrayUtils.addAll(DataUtil.short2Bytes(padding, ByteOrder.BIG_ENDIAN),
					ArrayUtils.addAll(terminalID,
						ArrayUtils.addAll(dhPublicKey_C,
							challenge_C
						)
					)
				)
			)
		); 
		return signBefore;
	}*/
	/*
	public byte[] toBytes() {
    	byte[] signBeforeS1 = genBytesForSign();
    	byte[] obj = ArrayUtils.addAll(signBeforeS1, signature);
    	return obj;
    }*/
	
	
	
	

}
