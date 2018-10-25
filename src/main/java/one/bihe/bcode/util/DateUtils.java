package one.bihe.bcode.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wrbug on 2017/8/23.
 */
public final class DateUtils {

    private static HashMap<String, DateFormat> mFormatsMap = new HashMap<>();

    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE = "yyyy-MM-dd";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE3 = "yyyy-MM";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE4 = "yyyyMMdd";

    public static final String FORMAT_SIMPLE5 = "yyyyMMddHHmmssSSS";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE6 = "yyyy.MM.dd HH:mm";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE8 = "yyyy.MM.dd";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE13 = "yyyy-MM-dd";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE14 = "yyyy-MM-ddHH:mm";
    public static final String FORMAT_SIMPLE9 = "yyyy年MM月";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE10 = "yyyy年MM月dd日";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE11 = "yyyyMM";
    /**
     * 简单的日期格式化.
     */
    public static final String FORMAT_SIMPLE12 = "M.d";
    /**
     * 一周内为几天.
     */
    public static final int LESS_A_WEEK = 7;

    /**
     * 三个月为90天(包括90天).
     */
    public static final int LESS_THREE_MONTH = 91;

    /**
     * 防止被构建.
     */
    private DateUtils() {

    }

    /**
     * 把日期格式化成短日期格式.
     * <p>
     * 格式如：HH:mm
     * <p>
     * .
     *
     * @param date 要格式化的日期.
     * @return 格式化结果.
     */
    public static String dateToShortString(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat tFormater = getFormater("HH:mm");
        return tFormater.format(date);
    }

    /**
     * 把日期格式化成短日期格式.
     * <p>
     * 格式如：HH:mm
     * <p>
     * .
     *
     * @param date 要格式化的日期.
     * @return 格式化结果.
     */
    public static String date2String(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat tFormater = getFormater("yyyy-MM-dd");
        return tFormater.format(date);
    }

    /**
     * 把日期格式化成短日期格式.
     * <p>
     * 格式如：HH:mm
     * <p>
     * .
     *
     * @param date 要格式化的日期.
     * @return 格式化结果.
     */
    public static String date3String(Date date) {
        DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm");
        if (date == null) {
            return null;
        }
        return tFormater.format(date);
    }

    /**
     * 把日期格式字符串解析成日期.
     *
     * @param dateStr 日期字符串.
     * @return 日期.2015-02-18
     */
    public static Date string2Date(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        DateFormat df = getFormater(FORMAT_SIMPLE);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(" 解析日期出错,输入格式不正确:" + dateStr, e);
        }
    }

    /**
     * 把日期格式字符串解析成日期.
     *
     * @param dateStr 日期字符串.
     */
    public static Date string2Date(String dateStr, String regx) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        DateFormat df = getFormater(regx);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }


    /**
     * 把日期格式字符串解析成日期.
     *
     * @param dateStr 日期字符串.
     * @return 日期.2015-02
     */
    public static Date stringToDate(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        DateFormat df = getFormater(FORMAT_SIMPLE3);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把日期格式字符串解析成日期.
     *
     * @return 20150218
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        return getFormater("yyyyMMdd").format(date);
    }

    /**
     * 把日期格式字符串解析成日期.
     *
     * @return 201502
     */
    public static String monthToString(Date date) {
        if (date == null) {
            return null;
        }
        return getFormater("yyyyMM").format(date);
    }

    /**
     * 得到某天时间的后面某天
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +day); // 得到后面某天
        return calendar.getTime();
    }

    /**
     * 得到某月的后某个月
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +month); // 得到后面某天
        return calendar.getTime();
    }

    /**
     * 解析时间.
     *
     * @param currentDate 源日期.
     * @param shortTime   短时间字符串.
     * @return 返回结果.
     */
    public static Date shortStringToDate(Date currentDate, String shortTime) {
        DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm:ss");
        if (currentDate == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getFormater("yyyy-MM-dd").format(currentDate)).append(" ")
                .append(shortTime).append(":00");
        try {
            return tFormater.parse(sb.toString());
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 转成日期时间格式的字符串.
     * <p>
     * 格式如：'yyyy-MM-dd HH:mm:ss'
     * <p>
     * .
     *
     * @param date 日期.
     * @return 日期时间格式字符串.
     */
    public static String toDateTimeString(Date date) {
        DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            return null;
        }
        return "'" + tFormater.format(date) + "'";
    }

    /**
     * 转成日期时间格式的字符串.
     * <p>
     * 格式如：'yyyy-MM-dd HH:mm:ss'
     * <p>
     * .
     *
     * @param date 日期.
     * @return 日期时间格式字符串.
     */
    public static String date2StringTime(Date date) {
        DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            return null;
        }
        return tFormater.format(date);
    }

    /**
     * 得到一天的最早时间.
     *
     * @param date 指定日期.
     * @return 一天的最早时间.
     */
    public static Date getZeroTime(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天0点时间戳
     *
     * @return
     */
    public static long getZeroTime(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到一天的最后时刻.
     *
     * @param date 指定日期.
     * @return 一天的最后时刻.
     */
    public static Date getLastTime(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();

    }

    /**
     * 把日期格式字符串解析成日期.
     *
     * @param dateStr 日期字符串.
     * @param regex   格式.
     * @return 日期.
     */
    public static Date parse(String dateStr, String regex) {
        if (TextUtils.isEmpty(dateStr) || TextUtils.isEmpty(regex)) {
            return null;
        }
        DateFormat df = getFormater(regex);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }


    /**
     * 把日期格式化成字符串.
     *
     * @param date  日期.
     * @param regex 格式.
     * @return 日期字符串.
     */
    public static String format(Date date, String regex) {
        DateFormat df = getFormater(regex);
        return df.format(date);
    }

    public static String toTimeFormat(int minute) {
        return String.format("%02d", minute / 60) + ":" + String.format("%02d", minute % 60);
    }

    /**
     * 将毫秒数的时间转成日期类型.
     *
     * @param timeMillis 毫秒数的时间.
     * @return 日期.
     */
    public static Date timeToDate(Long timeMillis) {
        if (timeMillis == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        return c.getTime();
    }

    public static int timeToInt(String time) {
        if (time != null) {
            String[] times = time.split(":");
            if (times.length == 2) {
                int hour = Integer.valueOf(times[0]);
                int minute = Integer.valueOf(times[1]);
                return hour * 60 + minute;
            }
        }
        return -1;
    }

    /**
     * 比较2个时间是否为同一天
     */
    public static boolean isDateEqual(String source, String target) {
        return isDateEqual(source, target, FORMAT_SIMPLE);
    }

    /**
     * 比较2个时间是否为同一天
     */
    public static boolean isDateEqual(String source, String target, String formatStr) {
        Calendar sourceDate = Calendar.getInstance();
        sourceDate.setTime(parse(source, formatStr));
        Calendar targetDate = Calendar.getInstance();
        targetDate.setTime(parse(target, formatStr));
        return sourceDate.get(Calendar.YEAR) == targetDate.get(Calendar.YEAR)
                && sourceDate.get(Calendar.MONTH) == targetDate.get(Calendar.MONTH)
                && sourceDate.get(Calendar.DAY_OF_MONTH) == targetDate.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 判断莫个日期string是否为周日
     *
     * @param dateStr 时间格式str.
     * @return boolean.
     */
    public static boolean isDateSunday(String dateStr) {
        return isDateSunday(dateStr, getFormater("yyyy-MM-dd"));
    }

    public static boolean isDateSunday(String dateStr, DateFormat formatStr) {
        if (dateStr == null) {
            return false;
        }
        Date bdate = null;
        try {
            bdate = formatStr.parse(dateStr);
        } catch (ParseException e) {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }


    /**
     * 返回当月最后一天的日期
     */
    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 返回当月第一天的日期
     */
    public static Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }


    /*
    判断是否是这个月后几天
     */
    public static boolean isThisMonthLastDays(int days) {

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(new Date());
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);


        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(getLastTime(getLastDayOfMonth()));//得到这个月的最后一天的最后一个时刻
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);


        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24) < days;
    }

    /**
     * 判断现在时刻是否是今天某时刻到某时刻之间
     *
     * @param startClock
     * @param endClock
     * @return
     */
    public static boolean isInTime(int startClock, int endClock) {
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        return (hour >= startClock && hour < endClock ? true : false);
    }

    /*
    判断是否是这个月前几天
     */
    public static boolean isThisMonthFrontDays(int days) {

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(new Date());
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(getZeroTime(getFirstDayOfMonth()));//得到这个月的第一天的最早一个时刻
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (fromCalendar.getTime().getTime() - toCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24) < days;

    }


    /**
     * 格式化时间戳成 yyyy-MM-dd HH:mm:ss
     */
    public static String formatLongToDate(long timeMill) {
        DateFormat sdf = getFormater("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date(timeMill);
        return sdf.format(date);
    }

    /**
     * 格式化时间戳成 yyyy-MM-dd HH:mm:ss
     */
    public static String formatLongToDateMill(long timeMill) {
        DateFormat sdf = getFormater("yyyy年MM月dd日 HH:mm:ss.SSS");
        Date date = new Date(timeMill);
        return sdf.format(date);
    }

    /**
     * 将字符串形式的日期转成时间戳,以毫秒为基数
     *
     * @return 失败返回null
     */
    public static long stringToLong(String date, String regx) {
        if (TextUtils.isEmpty(date)) {
            return 0L;
        }
        DateFormat format = getFormater(regx);
        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 获取明天的日期
     */
    public static Date getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 将一种格式的日期字符串转换为另一种格式的日期字符串
     *
     * @param aSourceDateText   原日期字符串
     * @param aSourceDateFormat 原日期字符串对应的格式, 如"yyyyMMdd", "yyyy-MM-dd"等
     * @param aDestDateFormat   目标日期字符串格式
     * @return 目标日期字符串格式对应的日期字符串
     */
    public static String convertDateString(String aSourceDateText,
                                           String aSourceDateFormat,
                                           String aDestDateFormat) {
        try {
            Date tDestDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
            return getFormater(aDestDateFormat).format(tDestDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getDayFromDate(String aSourceDateText, String aSourceDateFormat) {
        try {
            Date tDestDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
            return Integer.valueOf(getFormater("dd").format(tDestDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getMonthFromDate(String aSourceDateText, String aSourceDateFormat) {
        try {
            Date tDestDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
            return Integer.valueOf(getFormater("MM").format(tDestDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isToday(String aSourceDateText, String aSourceDateFormat) {
        try {
            Date tDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
            String tDateText = getFormater("yyyyMMdd").format(tDate);
            String tTodayText = getFormater("yyyyMMdd").format(new Date());
            return tDateText.equals(tTodayText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isThisMonth(String aSourceDateText, String aSourceDateFormat) {
        try {
            Date tDate = getFormater(aSourceDateFormat).parse(aSourceDateText);
            String tDateText = getFormater("yyyyMM").format(tDate);
            String tThisMonthText = getFormater("yyyyMM").format(new Date());
            return tDateText.equals(tThisMonthText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取某个月有多少天
     *
     * @param year  该月份所在的年份
     * @param month 月份，从1开始
     */
    public static int getTotalDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);//Java月份才0开始算
        return cal.getActualMaximum(Calendar.DATE);
    }


    /**
     * 获取某个月份的所有日期
     */
    @SuppressWarnings("WrongConstant")
    public static List<Date> getDatesByYearAndMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        List<Date> list = new ArrayList<>();
        do {
            list.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        } while ((month - 1) == calendar.get(Calendar.MONTH));
        return list;

    }

    public static String format(long mill, String regx) {
        DateFormat sdf = getFormater(regx);
        Date date = new Date();
        date.setTime(mill);
        return sdf.format(date);
    }

    /**
     * 格式化时间戳成 yyyy-MM-dd HH:mm
     */
    public static String formatLongToDateStr(long time) {
        DateFormat tFormater = getFormater("yyyy-MM-dd HH:mm");
        Date date = new Date(time * 1000);
        return tFormater.format(date);
    }

    /**
     * 格式化时间戳成 yyyy-MM-dd
     */
    public static String formatLongToDateStr2(long time) {
        Date date = new Date(time * 1000);
        return getFormater("yyyy-MM-dd").format(date);
    }

    public static String formatLongToDateStr(long timeInSec, String destFormat) {
        DateFormat tFormater = getFormater(destFormat);
        Date date = new Date(timeInSec * 1000);
        return tFormater.format(date);
    }

    public static String timestamp2date(long timestampMill, String format) {
        DateFormat tFormater = getFormater(format);
        Date date = new Date(timestampMill);
        return tFormater.format(date);
    }

    /**
     * 获取某个日期的上一个月
     *
     * @return 如果传入的时间或格式错误会返回“197001”
     */
    public static String lastMonth(String thisMonth, String regx) {
        Calendar calendar = Calendar.getInstance();
        DateFormat sdf = getFormater(regx);

        try {
            Date date = sdf.parse(thisMonth);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "197001";
        }
    }

    /**
     * 获取当前日期的下一个月
     */
    public static String getNextMonth(String thisMonth, String regx) {
        Calendar calendar = Calendar.getInstance();
        DateFormat sdf = getFormater(regx);

        try {
            Date date = sdf.parse(thisMonth);
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "197001";
        }
    }


    /**
     * date2比date1多的天数
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            return day2 - day1;
        }
    }

    /**
     * 计算两个日期相差的月份
     */
    public static int differentMonth(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return Math.abs(c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH));
        } else {
            if (date1.before(date2)) {
                Calendar ct = c1;
                c1 = c2;
                c2 = ct;
            }
            int monthSpace = 12 - c2.get(Calendar.MONTH) + c1.get(Calendar.MONTH);
            return monthSpace + 12 * (Math.abs(c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) - 1);
        }
    }

    /**
     * 将一个字符串转成Date类型
     *
     * @param str    要转的字符串
     * @param format 字符串的格式
     * @return 格式错误返回null
     */
    public static Date strToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static DateFormat getFormater(String aFormat) {
        if (!mFormatsMap.containsKey(aFormat)) {
            mFormatsMap.put(aFormat, new SimpleDateFormat(aFormat));
        }
        return mFormatsMap.get(aFormat);
    }

    public static boolean isTimeOverlap(Date d1s, Date d1e, Date d2s, Date d2e) {
        // d2s < d1s  && d2e>d1s
        boolean res1 = d2s.compareTo(d1s) < 0 && d2e.compareTo(d1s) > 0;
        //或d2s<t1.endTime && d2e > t1.endTime
        boolean res2 = d2s.compareTo(d1e) < 0 && d2e.compareTo(d1e) > 0;
        //或d2s >= d1s && d2e <= t1.endTime
        boolean res3 = d2s.compareTo(d1s) >= 0 && d2e.compareTo(d1e) <= 0;
        if (res1 || res2 || res3) {
            return true;
        }
        return false;
    }

}