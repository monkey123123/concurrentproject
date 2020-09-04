package me.monkey.gateway.demo.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileDemo {

    public static void main(String[] args) {
        keepPem("test------", "D:/", "test.txt");
    }
    /**
     * 新建pem写入证书密码
     *
     * @param certificateInfo
     * @return
     */
    public static void keepPem(String certificateInfo, String path, String cerFileName) {
        File caFile = new File(new File(path), cerFileName);
        try {
            System.out.printf("---------"+caFile.exists());
//            caFile.createNewFile();
            Writer fileWritter = new FileWriter(caFile);
            fileWritter.write("-----BEGIN CERTIFICATE-----\n");
            fileWritter.write("\n");
            fileWritter.write("-----END CERTIFICATE-----");
            fileWritter.flush();
            fileWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
