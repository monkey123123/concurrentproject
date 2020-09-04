package me.monkey.gateway.demo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 *  AES加解密方法类
 */
public class AesUtils {
    // 偏移量
    public static final String VIPARA = "5928772605893626";

    // 编码方式
    public static final String CODE_TYPE = "UTF-8";

    // 填充类型
    public static final String AES_TYPE = "AES/CBC/NoPadding";

    // 填充字符集
    private static final String[] consult = new String[]{"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    /**
     *  AES加密
     * @param text 需加密字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String text, String aesKey) throws Exception{
        // 偏移量
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        // 密钥填充至16位
        if(aesKey.length()<16) {
            aesKey = completionCodeFor16Bytes(aesKey);
        }
        SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(AES_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(completionCodeFor16Bytes(text).getBytes(CODE_TYPE));
        return toHex(encryptedData);
    }

    /**
     *  AES解密
     * @param encryptText -需解密字符串
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptText, String aesKey) throws Exception{
        byte[] bytes = hexToBytes(encryptText);
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        // 密钥填充至16位
        if(aesKey.length()<16) aesKey = completionCodeFor16Bytes(aesKey);
        SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(AES_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptedData = cipher.doFinal(bytes);
        return restoreData(new String(decryptedData, CODE_TYPE));
    }

    /**
     *  字符填充（加密前处理）
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String completionCodeFor16Bytes(String str) throws UnsupportedEncodingException {
        int len = str.getBytes(CODE_TYPE).length;
        int index = len%16;
        int coverCnt = 16 - index; // 需填充字符数量
        String coverVal = consult[coverCnt - 1]; // 填充值
        StringBuffer sb = new StringBuffer(str);
        // 补位填充
        for(int i=0; i<coverCnt; i++) sb.append(coverVal);
        return sb.toString();
    }

    /**
     *  还原字符串（去填充）
     * @param str
     * @return
     */
    public static String restoreData(String str) {
        // 获取最后的字符串值
        int num = 0;
        String markStr = str.substring(str.length()-1);
        // 获取需截取字符长度
        for(int i=0;i<consult.length; i++) {
            if(consult[i].equals(markStr)) {
                num = i +1;
                break;
            }
        }
        // 还原字符
        str = str.substring(0,str.length()-num);
        return str;
    }

    /**
     *  byte数组转16进制值字符串
     * @param buf
     * @return
     */
    public static String toHex(byte[] buf) {
        if(buf != null && buf.length != 0) {
            StringBuilder out = new StringBuilder();

            for(int i = 0; i < buf.length; ++i) {
                out.append(consult[buf[i] >> 4 & 15]).append(consult[buf[i] & 15]);
            }

            return out.toString();
        } else {
            return "";
        }
    }

    /**
     *  字符串转byte数组
     * @param str
     * @return
     */
    public static byte[] hexToBytes(String str) {
        if(str == null) {
            return null;
        } else {
            char[] hex = str.toCharArray();
            int length = hex.length / 2;
            byte[] raw = new byte[length];

            for(int i = 0; i < length; ++i) {
                int high = Character.digit(hex[i * 2], 16);
                int low = Character.digit(hex[i * 2 + 1], 16);
                int value = high << 4 | low;
                if(value > 127) {
                    value -= 256;
                }

                raw[i] = (byte)value;
            }

            return raw;
        }
    }

    public static void main(String[] args) throws Exception{
        String text = "xxxxxxxxxxx_1548042768541";
        String s = encrypt(text, "xxxxx*#xxxxxxxxo");
        System.out.println("加密串: " + s);
        System.out.println("解密值：" + decrypt(s, "xxxxx*o#xxxxxxxx"));
    }
}