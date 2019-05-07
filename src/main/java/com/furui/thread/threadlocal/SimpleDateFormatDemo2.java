package com.furui.thread.threadlocal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 优化
 * @author furui
 * @date 2019/4/12 0012
 */
public class SimpleDateFormatDemo2 {
    private static ThreadLocal<DateFormat> dateTimeFormatThreadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private static ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static Date parseDateTime(String dateStr) throws ParseException {
        return dateTimeFormatThreadLocal.get().parse(dateStr);
    }

    public static String formatDateTime(Date date) {
        return dateTimeFormatThreadLocal.get().format(date);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return dateFormatThreadLocal.get().parse(dateStr);
    }

    public static String formatDate(Date date) {
        return dateFormatThreadLocal.get().format(date);
    }
}
