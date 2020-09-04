package me.monkey.gateway.demo.aes256.opt;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    //使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
    private static String KEY ;
    private static String IV ;

    public static String getKEY() {
        return "6a3c2dd76f53e69b6a3c2dd76f53e69b";
    }
    public static String getIV() {
        return "6a3c2dd76f53e69b";
    }

    public static void main(String[] args) {
        byte[] pt = "0000111122223333".getBytes();

        byte[] res = testEncrypt(pt ,getKEY().getBytes());
        try {
            byte[] ct = encrypt(pt, getKEY(), getIV());
            byte[] pt2 = decrypt(ct, getKEY(), getIV());
            System.out.println(pt2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static byte[] testEncrypt(byte[] content, byte[] aesKey) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //写成512 测代码正确性 java.security.InvalidParameterException: Wrong keysize: must be equal to 128, 192 or 256
            int keySize = 256;
            kgen.init(keySize);
            SecretKey key = kgen.generateKey();
            byte[] aesKey2 = key.getEncoded();
            System.out.println("aesKey2 = " + aesKey2.length);

            System.out.println("key:   "+ Hex.encodeHexString(aesKey));
            SecretKeySpec aesKeySpec = new SecretKeySpec(aesKey, "AES");
            Cipher aesCipher = Cipher.getInstance("AES");
            System.out.println("content = [" + content + "], aesKey = [" + aesKey + "]" + aesCipher.getBlockSize());
            aesCipher.init(Cipher.ENCRYPT_MODE, aesKeySpec);
            byte[] encryptedContent = aesCipher.doFinal(content);
            return encryptedContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 加密方法
     * @param dataBytes  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     * @throws Exception
     */
    public static byte[] encrypt(byte[] dataBytes, String key, String iv) {
        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"NoPadding PkcsPadding
            int blockSize = cipher.getBlockSize();

//            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return encrypted;
//            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密方法
     * @param encrypted1 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     * @throws Exception
     */
    public static byte[] decrypt(byte[] encrypted1, String key, String iv) {
        try {
//            byte[] encrypted1 = new Base64().decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            return original;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
