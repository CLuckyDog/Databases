package com.databases.databases.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public final class TimeUtil {

    /**
     * <p>获取35号文格式时间</p>
     * <p>时间格式：yyyy-MM-ddTHH:mm:ssZ</p>
     *
     * @return 当前时间，精确到秒
     */
    public static String get35TimeNowS() {
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd").format(date) + "T" +
                new SimpleDateFormat("HH:mm:ss").format(date) + "Z";
    }

    /**
     * <p>获取35号文格式时间</p>
     * <p>时间格式：yyyy-MM-ddTHH:mm:ss.SSSZ</p>
     *
     * @return 当前时间，精确到毫秒
     */
    public static String get35TimeNowMS() {
        Date date = new Date();
        return new SimpleDateFormat("yyyy-MM-dd").format(date) + "T" +
                new SimpleDateFormat("HH:mm:ss.SSS").format(date) + "Z";
    }

    /**
     * <p>35号文格式时间转换</p>
     * <p>时间格式：yyyy-MM-ddTHH:mm:ssZ</p>
     *
     * @param date date时间
     * @return 转换后的时间，精确到秒
     */
    public static String get35TimeS(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date) + "T" +
                new SimpleDateFormat("HH:mm:ss").format(date) + "Z";
    }

    /**
     * <p>35号文格式时间转换</p>
     * <p>时间格式：yyyy-MM-ddTHH:mm:ss.SSSZ</p>
     *
     * @param date date时间
     * @return 转换后的时间，精确到毫秒
     */
    public static String get35TimeMS(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date) + "T" +
                new SimpleDateFormat("HH:mm:ss.SSS").format(date) + "Z";
    }

    /**
     * <p>获取当前时间</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 当前时间，精确到秒
     */
    public static String getTimeNowS() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * <p>获取当前时间</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss.SSS</p>
     *
     * @return 当前时间，精确到毫秒
     */
    public static String getTimeNowMS() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date date时间
     * @return 转换后的时间，精确到秒
     */
    public static String getTimeS(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date date时间
     * @return 转换后的时间，精确到秒
     */
    public static String getTimeD(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("HH:mm:ss").format(date);
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss.SSS</p>
     *
     * @param date date时间
     * @return 转换后的时间，精确到毫秒
     */
    public static String getTimeMS(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
    }
    
    /**
     * <p>时间转换</p>
     * <p>时间格式：HH:mm:ss</p>
     *
     * @param date date时间
     * @return 转换后的时间，时分秒
     */
    public static Date getTimeY(String date) {
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date string时间
     * @return 转换后的时间
     */
    public static Date getDayTime(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = null;
        if (date.contains("-")) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (date.contains("/")) {
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        }

        if (simpleDateFormat == null) {
            return null;
        }

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date string时间
     * @return 转换后的时间
     */
    public static Date getDayTimeS(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd</p>
     *
     * @param date string时间
     * @return 转换后的时间
     */
    public static String getFormatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(date);
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date string时间
     * @return 转换后的时间
     */
    public static Date getTime(String date) {
        return getTimeS(date);
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date string时间
     * @return 转换后的时间
     */
    public static Date getTimeS(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss.SSS</p>
     *
     * @param date string时间
     * @return 转换后的时间
     */
    public static Date getTimeMS(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * <p>时间转换</p>
     * <p>时间格式：yyyy-MM-dd HH:mm:ss</p>
     */
    public static String timeFormat(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String year = time.substring(0, 4);
        String month = time.substring(4, 6);
        String day = time.substring(6, 8);
        String hour = time.substring(8, 10);
        String min = time.substring(10, 12);
        String second = time.substring(12, 14);
        sb.append(year).append("-").append(month).append("-").append(day).append(" ").append(hour).append(":").append(min).append(":").append(second);
        return sb.toString();
    }

    public static String getDayString(Date date) {
        if (date == null) {
            return "";
        }
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        if (StringUtils.isEmpty(format)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        String[] chars = format.split(" ");
        stringBuilder.append(chars[0]);
        return stringBuilder.toString();
    }

    public static List<Date> findDateList(Date beginDate, Date endDate) {
        List<Date> dateList = new ArrayList<Date>();
        dateList.add(beginDate);
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(beginCalendar.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            beginCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(beginCalendar.getTime());
        }
        return dateList;
    }

    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 取两个时间的差值小时数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static double getHour(Date startTime, Date endTime) {
        double result = (startTime.getTime() - endTime.getTime()) / 3600000;
        // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
        return result;
    }

    public static double getHour(long startTime, long endTime) {
        double result = (startTime - endTime) / 3600000;
        // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
        return result;
    }


    // 判断两个时间差是否相差一定天数
    public static boolean timeCompare(String startTime, String endTime, int apart) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = sdf.parse(startTime);
            endDate = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        if (null == startDate || null == endDate) {
            return false;
        }

        startCal.setTime(startDate);
        endCal.setTime(endDate);
        //得到两个日期相差的天数   还可通过去除后面的数 求得相差的小时数,分钟数,秒   等等
        int days = ((int) (endCal.getTime().getTime() / 1000) - (int) (startCal.getTime().getTime() / 1000)) / 3600 / 24;
        return days >= apart;
    }


}
