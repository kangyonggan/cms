package com.kangyonggan.cms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 新日期时间工具类，会不断完善
 *
 * @author kangyonggan
 * @date 4/7/17
 */
public class DateUtil {

    private static final String FULL_DATETIME_PATTERN = "yyyyMMddHHmmssSSS";
    private static final String DATETIME_PATTERN = "yyyyMMddHHmmss";
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final String TIME_PATTERN = "HHmmss";

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String toXmlDateTime(Date date) {
        synchronized (DATE_FORMAT) {
            return DATE_FORMAT.format(date) + "T" + DATE_FORMAT.format(date) + "Z";
        }
    }

    /**
     * yyyyMMddHHmmssSSS
     *
     * @return
     */
    public static String getFullDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(FULL_DATETIME_PATTERN));
    }

    /**
     * yyyyMMdd
     *
     * @return
     */
    public static String getDate() {
        return LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 在当前日期的基础上加nanosToAdd纳秒
     *
     * @param nanosToAdd 为负数则是减去nanosToAdd纳秒
     * @return
     */
    public static Date plusNanos(long nanosToAdd) {
        return Date.from(LocalDateTime.now().plusNanos(nanosToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加secondsToAdd秒
     *
     * @param secondsToAdd 为负数则是减去secondsToAdd秒
     * @return
     */
    public static Date plusSeconds(long secondsToAdd) {
        return Date.from(LocalDateTime.now().plusSeconds(secondsToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加minutesToAdd分钟
     *
     * @param minutesToAdd 为负数则是减去minutesToAdd分钟
     * @return
     */
    public static Date plusMinutes(long minutesToAdd) {
        return Date.from(LocalDateTime.now().plusMinutes(minutesToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加hoursToAdd小时
     *
     * @param hoursToAdd 为负数则是减去hoursToAdd小时
     * @return
     */
    public static Date plusHours(long hoursToAdd) {
        return Date.from(LocalDateTime.now().plusHours(hoursToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加datesToAdd天
     *
     * @param datesToAdd 为负数则是减去datesToAdd天
     * @return
     */
    public static Date plusDays(long datesToAdd) {
        return Date.from(LocalDateTime.now().plusDays(datesToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加datesToAdd天
     *
     * @param datesToAdd 为负数则是减去datesToAdd天
     * @return
     */
    public static String plusStrDays(long datesToAdd) {
        return LocalDateTime.now().plusDays(datesToAdd).format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 在当前日期的基础上加weeksToAdd周
     *
     * @param weeksToAdd 为负数则是减去weeksToAdd周
     * @return
     */
    public static Date plusWeeks(long weeksToAdd) {
        return Date.from(LocalDateTime.now().plusWeeks(weeksToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加monthsToAdd月
     *
     * @param monthsToAdd 为负数则是减去monthsToAdd月
     * @return
     */
    public static Date plusMonths(long monthsToAdd) {
        return Date.from(LocalDateTime.now().plusMonths(monthsToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 在当前日期的基础上加yearsToAdd月
     *
     * @param yearsToAdd 为负数则是减去yearsToAdd月
     * @return
     */
    public static Date plusYears(long yearsToAdd) {
        return Date.from(LocalDateTime.now().plusYears(yearsToAdd).atZone(ZONE_ID).toInstant());
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date now() {
        return Date.from(LocalDateTime.now().atZone(ZONE_ID).toInstant());
    }

    /**
     * 字符串转成8位日期
     *
     * @param dateStr
     * @return
     */
    public static Date fromDate(String dateStr) throws ParseException {
        return new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
    }

    /**
     * 字符串转成10位日期
     *
     * @param dateStr
     * @return
     */
    public static Date fromDate10(String dateStr) throws ParseException {
        synchronized (DATE_FORMAT) {
            return DATE_FORMAT.parse(dateStr);
        }
    }

    /**
     * 转为yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String toFullDate(Date date) {
        synchronized (DATE_TIME_FORMAT) {
            return DATE_TIME_FORMAT.format(date);
        }
    }
}
