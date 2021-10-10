package com.manage.bookstore.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static Date getSqlDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = sdf.parse(strDate);
        long millis = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(millis);
        return sqlDate;
    }
}
