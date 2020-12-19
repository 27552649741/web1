package com.cyh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getNowTimeForUpload() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyMMddHHmmss");
        Date date=new Date();
        String format = simpleDateFormat.format(date);
        return format;
    }
}
