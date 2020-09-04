package me.monkey.gateway.demo.aes256;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSha256 {
    public static void main(String[] args) {
        String dksfjsdk = mac("00001111".getBytes(), "dksfjsdk");
        System.out.println(dksfjsdk);
        //如果是String转int，请直接用
        int i = Integer.parseInt("99", 16);
        System.out.println(i);
    }
    public static String mac(byte[] message,String key){
        String outPut= null;
        try{
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message);
//            outPut = byteArrayToHexString(bytes);
            outPut = Hex.encodeHexString(bytes);
            return outPut;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error HmacSHA256========"+e.getMessage());
        }
        return outPut;
    }
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                sb.append('0');
            sb.append(stmp);
        }
        return sb.toString().toLowerCase();
    }

}