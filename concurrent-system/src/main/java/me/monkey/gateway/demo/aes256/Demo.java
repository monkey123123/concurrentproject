package me.monkey.gateway.demo.aes256;

import static me.monkey.gateway.demo.aes256.AESUtil.encryptOrdecrypt;

public class Demo {
    public static void main(String[] args) {
        System.out.println("【1.3】AES_CBC_NoPadding模式");
        String content = "在线助手在线助手在线助手在线助手";
        // 生成密钥需要的密码值
        String key = "www.it399.com";
        byte[] encrypt = new byte[0];
        try {
            encrypt = AESUtil.encryptOrdecrypt(true,
                    content.getBytes(AESUtil.CHARSET),
                    key,
                    AESUtil.getIV(),
                    AESType.AES_256,
                    EncodeType.AES_CBC_NoPadding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            byte[] res = encryptOrdecrypt(false,encrypt,key,AESUtil.getIV(),AESType.AES_256,EncodeType.AES_CBC_NoPadding);
            System.out.println(new String(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
