package me.monkey.demo.timerange;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class CheckTimeRange {

    public static void main(String[] args) {
        //LocalDate is API scensed from JDK1.8
        LocalDate currDate = LocalDate.now();
        LocalTime currTime = LocalTime.now();

        System.out.println(currDate.getDayOfMonth());
        System.out.println(currTime.getHour());

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        Calendar beforeC = Calendar.getInstance();
        Calendar afterC = Calendar.getInstance();
        beforeC.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH), 20, 30, 0);
        afterC.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH), 21, 30, 0);

        // 默认第一天是周日，
        if (cal.after(beforeC) && cal.before(afterC)
                && (dayOfWeek == 1 || dayOfWeek == 4 || dayOfWeek == 5)) {
            System.out.println("true========>" + fmt.format(cal.getTime()));
        } else {
            System.out.println("false========>" + fmt.format(cal.getTime()));
        }
    }
    
    public boolean checkTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        if(week != 0 && week != 2 && week !=4) {    //星期日是0
            return false;
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        System.out.println(hour);
        System.out.println(minute);

        if((hour==20 && minute>=30)
                || (hour==21 && minute<30)) {
            return true;
        }
        return false;
    }


}
