package com.beanpodtech.pkms.iot.ota.mqtt.reconnect;

import com.beanpodtech.pkms.iot.ota.mqtt.util.DataUtil;
import org.bouncycastle.util.encoders.Hex;

import java.nio.ByteOrder;

public class Demo {
    public static void main(String[] args) {
        int versionTemp = DataUtil.bytes2Int(Hex.decode("00010001"), ByteOrder.BIG_ENDIAN);
        int versionTemp2 = DataUtil.bytes2Int(Hex.decode("00010002"), ByteOrder.BIG_ENDIAN);
        System.out.println(versionTemp);
        System.out.println(versionTemp2);
        System.out.println(versionTemp < versionTemp2);
    }
}
