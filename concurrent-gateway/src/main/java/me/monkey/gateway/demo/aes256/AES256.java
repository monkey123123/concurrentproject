package me.monkey.gateway.demo.aes256;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述： AES-256-CBC 对称加密封装类,应用于接口加密
 */
public class AES256 {

    static {
        /*AES 加密默认128位的key，这里改成256位的（类在下面粘出来了）*/
//        UnlimitedKeyStrengthJurisdictionPolicy.ensure();
    }

    /*加密算法*/
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     *
     * @param srcData 要加密的数组（String 需要base64 编码）
     * @param key 公钥，32位byte数组
     * @param iv 私钥，16位byte数组
     * @return 加密后的byte数组
     * @throws Exception 找不到加密算法等
     */
    public static byte[] AES_cbc_encrypt(byte[] srcData, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        int max = Cipher.getMaxAllowedKeyLength(ALGORITHM);
        System.out.println(max);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
        return cipher.doFinal(srcData);
    }

    /**
     *
     * @param encData 要解密的数组
     * @param key 公钥
     * @param iv 私钥
     * @return 解密后的byte数组
     * @throws Exception 找不到解密算法等
     */
    private static byte[] AES_cbc_decrypt(byte[] encData, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher.getMaxAllowedKeyLength(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        return cipher.doFinal(encData);
    }

    public static void main(String[] args) throws Exception{
        byte[] text = "xxxxxxxxxxx_1548042768541".getBytes();
        byte[] key = "xxxxx*#xxxxxxxxoxxxxx*#xxxxxxxxo".getBytes();
        byte[] iv = "xxxxx*#xxxxxxxxo".getBytes();
        byte[] ss = AES_cbc_encrypt(text, key, iv);
        System.out.println("加密串: " + Hex.encodeHexString(ss) );
        System.out.println("解密值：" + new String(AES_cbc_decrypt(ss, key, iv)));
    }
}