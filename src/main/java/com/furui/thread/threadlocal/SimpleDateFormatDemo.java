package com.furui.thread.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author furui
 * @date 2019/4/12 0012
 */
public class SimpleDateFormatDemo {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String dateTime = "2019-04-12 15:35:34";

    public static void main(String[] args) {
        for (int j=1;j<=10;j++) {
            new Thread(() -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(
//                                Thread.currentThread().getName() + "\t" + format.parse(dateTime));
                                Thread.currentThread().getName() + "\t" + SimpleDateFormatDemo2.parseDateTime(dateTime));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, "thread" + j).start();
        }

    }
}
