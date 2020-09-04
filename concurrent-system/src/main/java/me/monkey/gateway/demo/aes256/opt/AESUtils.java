package me.monkey.gateway.demo.aes256.opt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;


public class AESUtils {  
      
    private final static String KEY="12341234123400001234123412340000";
    private final static String IV= "1234123412340000";

    /** 
     * aes 加密 
     * @param dataBytes
     * @return 
     */  
    public static byte[] encryptData(byte[] dataBytes){
        try {  
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
//            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * aes 解密
     * @param encrypted1 密文
     * @return
     */
    public static byte[] decryptData(byte[] encrypted1){
        try {
//            byte[] encrypted1 =Base64.decodeBase64(data.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");  
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());  
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);  
            byte[] original = cipher.doFinal(encrypted1);  
            return original;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
      
    public static void main(String[] args) {
        byte[] data= null;
        try {
            data = "0000111122223333".getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(data.length);
        String enStr= Hex.encodeHexString(AESUtils.encryptData(data));
        System.out.println(enStr);
        System.out.println("加密:"+enStr);
        byte[] deStr= null;
        try {
            deStr = AESUtils.decryptData(Hex.decodeHex(enStr));
            System.out.println(deStr.length);
            System.out.println("解密:"+deStr);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }
  
}  