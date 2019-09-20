package com.xcode126.kgplayer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Author：sky on 2019/4/23 15:02.
 * Email：xcode126@126.com
 * Desc：时间格式工具类
 */
public class DateUtils {
    public final static String YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    public final static String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public final static String HOUR_MINUTE = "HH:mm";

    public static String format(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dateFormat.format(new Date(time));
    }

    public static String format(long time, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        return dateFormat.format(new Date(time));
    }


    public static Date parse(String date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Date parse(String date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * 格式指定格式的时间戳
     *
     * @param pattern
     * @param time
     * @return
     */
    public static String getFormatTime(String pattern, Object time) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        return sdf.format(Long.parseLong(String.valueOf(time)));
    }


    /**
     * 判断是否是当天
     *
     * @param time
     * @return
     */
    public static boolean isToday(Object time) {
        return getFormatTime(YEAR_MONTH_DAY).equals(getFormatTime(YEAR_MONTH_DAY, time));
    }

    /**
     * 指定格式化的当前时间
     *
     * @param pattern
     * @return
     */
    public static String getFormatTime(String pattern) {
        return getFormatTime(pattern, System.currentTimeMillis());
    }

    /**
     * 格式化特定格式的时间戳
     * 当天 HOUR_MINUTE 样式
     * 非当天 YEAR_MONTH_DAY_HOUR_MINUTE 样式
     *
     * @param time
     * @return
     */
    public static String getFormatAppointedTime(String time) {
        time = String.format("%1$s%2$s", time, "000");
        return getFormatTime(isToday(time) ? HOUR_MINUTE : YEAR_MONTH_DAY_HOUR_MINUTE, time);
    }


    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();


    /**
     * 时间戳格式化成字符串
     *
     * @param milSecond
     * @return
     */
    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String timeToString(long millions) {
        Formatter formatter = new Formatter();
        int totalSeconds = (int) (millions / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        if (hours > 0) {
            return formatter.format("%d:%02d:%02d", hours, minutes, seconds)
                    .toString();
        } else {
            return formatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }
}


