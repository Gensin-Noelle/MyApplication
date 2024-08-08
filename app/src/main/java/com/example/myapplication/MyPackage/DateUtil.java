package com.example.myapplication.MyPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 返回当前时间
     */
    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
