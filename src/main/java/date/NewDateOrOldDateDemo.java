package date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 如何在新旧API之间互相转换呢
 *
 * @author guoyh
 */
public class NewDateOrOldDateDemo {
    public static void main(String[] args) {
        //旧转新
        Instant instant = new Date().toInstant();
        Instant instant1 = Calendar.getInstance().toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime1 = instant1.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);

        //新转旧
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now();
        long time = zonedDateTime2.toEpochSecond() * 1000;
        Date date = new Date(time);
        System.out.println(date);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone(zonedDateTime2.getZone().getId()));
        calendar.setTimeInMillis(time);
        System.out.println(calendar.getTime());

        //还可以找到另一个java.sql.Date，它继承自java.util.Date，但会自动忽略所有时间相关信息。
        //这个奇葩的设计原因要追溯到数据库的日期与时间类型
        //在数据库中，也存在几种日期和时间类型：
        //DATETIME：表示日期和时间；
        //DATE：仅表示日期；
        //TIME：仅表示时间；
        //TIMESTAMP：和DATETIME类似，但是数据库会在创建或者更新记录的时候同时修改TIMESTAMP

        //Java程序操作数据库时，我们需要把数据库类型与Java类型映射起来。下表是数据库类型与Java新旧API的映射关系：
        //数据库	        对应Java类（旧）	    对应Java类（新）
        //DATETIME	    java.util.Date	    LocalDateTime
        //DATE	        java.sql.Date	    LocalDate
        //TIME	        java.sql.Time	    LocalTime
        //TIMESTAMP	    java.sql.Timestamp	LocalDateTime

        //最好的方法是直接用长整数long表示，在数据库中存储为BIGINT类型
        //因为有了时刻信息，就可以根据用户自己选择的时区

        //通过存储一个long型时间戳，我们可以编写一个timestampToString()的方法，
        //非常简单地为不同用户以不同的偏好来显示不同的本地时间：
        long ts = 1574208900000L;
        System.out.println(timestampToString(ts, Locale.CHINA, "Asia/Shanghai"));
        System.out.println(timestampToString(ts, Locale.US, "America/New_York"));
    }

    private static String timestampToString(long ts, Locale locale, String s) {
        Instant instant = Instant.ofEpochMilli(ts);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);
        return dateTimeFormatter.withLocale(locale).format(ZonedDateTime.ofInstant(instant, ZoneId.of(s)));
    }

}
