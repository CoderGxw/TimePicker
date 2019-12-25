package com.liuwan.demo.datepicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：日期格式化工具
 * 作者：liuwan1992
 * 添加时间：2018/12/17
 * 修改人：CoderGxw
 * 修改时间：2019/12/24
 */
public class DateFormatUtils {

    private static final String DATE_FORMAT_PATTERN_YMD = "yyyy-MM-dd";
    private static final String DATE_FORMAT_PATTERN_YMD_HM = "yyyy-MM-dd HH:mm";

    /**
     * 时间戳转字符串
     *
     * @param timestamp     时间戳
     * @param isPreciseTime 是否包含时分
     * @return 格式化的日期字符串
     */
    public static String long2Str(long timestamp, boolean isPreciseTime) {
        return long2Str(timestamp, getFormatPattern(isPreciseTime));
    }

    private static String long2Str(long timestamp, String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINA).format(new Date(timestamp));
    }

    /**
     * 字符串转时间戳
     *
     * @param dateStr       日期字符串
     * @param isPreciseTime 是否包含时分
     * @return 时间戳
     */
    public static long str2Long(String dateStr, boolean isPreciseTime) {
        return str2Long(dateStr, getFormatPattern(isPreciseTime));
    }

    private static long str2Long(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.CHINA).parse(dateStr).getTime();
        } catch (Throwable ignored) {
        }
        return 0;
    }

    private static String getFormatPattern(boolean showSpecificTime) {
        if (showSpecificTime) {
            return DATE_FORMAT_PATTERN_YMD_HM;
        } else {
            return DATE_FORMAT_PATTERN_YMD;
        }
    }

    /**
     * 判断字符串格式是否为YYYY-MM-DD
     * @param YYYYMMDD
     * @return
     */
    public static  boolean isYYYYMMDD(String YYYYMMDD){
        Pattern p = Pattern
                .compile("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$");
        Matcher matcher = p.matcher(YYYYMMDD);
        return matcher.matches();
    }
    /**
     * 判断字符串格式是否为YYYY-MM-DD HH:MM
     * @param YYYYMMDDHHMM
     * @return
     */
    public static boolean isYYYYMMDDHHMM(String YYYYMMDDHHMM){
        Pattern p = Pattern
                .compile("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})$");
        Matcher matcher = p.matcher(YYYYMMDDHHMM);
        return matcher.matches();
    }

}
