package me.monkey.util;
  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;  
  
public class FileUtil {
    public static void main(String[] args) {
        System.out.println(FileUtil.getType("D:/test.png"));
    }
    /** 
     * 获取文件头信息 
     *  
     * @param filePath 
     * @return 
     * @throws IOException 
     */  
    private static String getFileHeader(String filePath) {
        InputStream inputStream = null;
        try {
            byte[] b = new byte[28];
            inputStream = new FileInputStream(filePath);
            inputStream.read(b, 0, 28);
            return bytes2hex(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
  
    /** 
     * 判断文件类型 
     *
     * @param filePath 
     * @return 
     * @throws IOException 
     */
    public static FileType getType(String filePath) {
        String fileHeader = getFileHeader(filePath);  
        if (fileHeader == null || fileHeader.length() == 0) {  
            return null;  
        }  
        fileHeader = fileHeader.toUpperCase();  
        FileType[] fileTypes = FileType.values();  
        for (FileType type : fileTypes) {  
            if (fileHeader.startsWith(type.getValue())) {  
                return type;  
            }
        }
        return null;  
    }  
  
    private static String bytes2hex(byte[] src) {  
        StringBuilder builder = new StringBuilder();  
        if (src == null || src.length == 0) {  
            return "";  
        }
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                builder.append(0);  
            }  
            builder.append(hv);  
        }  
        return builder.toString();  
    }
  
}