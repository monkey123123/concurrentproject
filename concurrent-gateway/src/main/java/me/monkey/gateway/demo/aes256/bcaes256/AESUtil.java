package me.monkey.gateway.demo.aes256.bcaes256;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtil {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    //加密
    public static byte[] AES_cbc_encrypt(byte[] srcData,byte[] key,byte[] iv)
    {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = null;
        byte[] encData = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
            encData = cipher.doFinal(srcData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return encData;
    }

    //解密
    public static byte[] AES_cbc_decrypt(byte[] encData,byte[] key,byte[] iv)
    {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = null;
        byte[] decbbdt = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
            decbbdt = cipher.doFinal(encData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return decbbdt;
    }

    public static void main(String[] args) {


        byte[] key= new byte[32];
        byte[] iv = new byte[16];

        String srcStr = "This is java default pkcs5padding PKCS5 TEST";
        System.out.println(srcStr);

        //设置key 全8，iv，全1，这里测试用
        for (int i = 0; i <32; i++) {
            key[i] = 8;
            if (i < 16) {iv[i] = 0;}
        }


        byte[] encbt = AES_cbc_encrypt(srcStr.getBytes(),key,iv);
        byte[] decbt = AES_cbc_decrypt(encbt,key,iv);
        String decStr = new String(decbt);
        System.out.println(decStr);

        if(srcStr.equals(decStr))
        {
            System.out.println("TEST PASS");
        }else
        {
            System.out.println("TEST NO PASS");
        }

    }
}
