package me.monkey.gateway.demo.aes256.php;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by smart on 2015/8/17.
 */
public class Decrypt {
    //密匙
    private static String DEFAULTKEY = "12345678123456781234567812345678";
    //偏移量
    private static String IVPARAMETER = "0102030405060708";

    public static String execute(String sSrc) throws Exception {
        if ( sSrc==null) {
            return null;
        }
        return decrypt(sSrc, DEFAULTKEY);
    }

    // 解密
    private static String decrypt(byte[] sSrc, String sKey) throws Exception {
        if (sKey == null || sSrc==null) {
            return null;
        }
        return new String( decryptByte( sSrc, sKey));
    }

    private static byte[] decryptByte(byte[] sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null || sSrc==null) {
                return null;
            }
            // 判断Key是否为16位
//            if (sKey.length() != 16) {
//                return null;
//            }
            byte[] raw = sKey.getBytes();// "ASCII"
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IVPARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            try {
                return cipher.doFinal(sSrc);
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    private static String decrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null || sSrc==null) {
            return null;
        }
        return decrypt( hex2byte(sSrc), sKey);
    }

    private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] arg) {
        try {
//            System.out.println(execute("55a280077e82db68806ce77fe9602a7c"));
//            System.out.println(execute("70d4a2a4ea1e2da3cc0d5e47ce30c1f5"));
            System.out.println(execute("65c379563b446a440b22f1bfeba5c6e7"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//————————————————
//版权声明：本文为CSDN博主「织梦猫2」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/jason19905/article/details/78676605