package com.weimi.formx.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangsh on 2018-05-18
 */
public class DateUtils {

    private DateUtils() {}

    /**
     * 将日期转化为 yyyy-MM-dd
     * @param date 日期
     * @return 日期字符串
     */
    public static String toDateString(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);

    }

}
