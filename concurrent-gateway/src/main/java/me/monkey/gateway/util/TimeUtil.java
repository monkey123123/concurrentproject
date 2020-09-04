package me.monkey.gateway.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static Date converEndTime(Date endTime){
        if(endTime == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTimeStr = sdf.format(endTime);
            endTimeStr = endTimeStr.substring(0,10) + " 23:59:59:";
            return sdf.parse(endTimeStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
