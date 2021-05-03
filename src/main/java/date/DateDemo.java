package date;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author guoyh
 */
public class DateDemo {
    public static void main(String[] args) {
        int n = 123400;
        System.out.println(n);
        System.out.println(Integer.toHexString(n));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(n));

        //Epoch Time又称为时间戳，在不同的编程语言中，会有几种存储方式：
        //以秒为单位的整数：1574208900，缺点是精度只能到秒；
        //以毫秒为单位的整数：1574208900123，最后3位表示毫秒数；
        //以秒为单位的浮点数：1574208900.123，小数点后面表示零点几秒。

        long t = 1574208900123L;
        System.out.println(System.currentTimeMillis());

        //一套定义在java.util这个包里面，主要包括Date、Calendar和TimeZone这几个类；
        //一套新的API是在Java 8引入的，定义在java.time这个包里面，主要包括LocalDateTime、ZonedDateTime、ZoneId等
        //因为历史遗留原因，旧的API存在很多问题，所以引入了新的API。

        Date date = new Date();
        System.out.println(date.getYear()+1900);
        System.out.println(date.getMonth());
        System.out.println(date.getDate());
        System.out.println(date.toString());
        System.out.println(date.toGMTString());
        System.out.println(date.toLocaleString());

        //获取当前时间:
        //M：输出9
        //MM：输出09
        //MMM：输出Sep
        //MMMM：输出September
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));

        SimpleDateFormat sdf2 = new SimpleDateFormat("E MMM dd yyyy");
        System.out.println(sdf2.format(date));

        //Calendar可以用于获取并设置年、月、日、时、分、秒，它和Date比，主要多了一个可以做简单的日期和时间运算的功能
        //年份不必转换，返回的月份仍然要加1
        //1~7分别表示周日，周一，……，周六
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH)+1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println(calendar.get(Calendar.MILLISECOND));

        calendar.set(Calendar.YEAR, 2019);
        // 设置9月:注意8表示9月:
        calendar.set(Calendar.MONTH, 8);
        // 设置2日:
        calendar.set(Calendar.DATE, 2);
        // 设置时间:
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 22);
        calendar.set(Calendar.SECOND, 23);
        //getTime()可以将一个Calendar对象转换成Date对象
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
        // 2019-09-02 21:22:23

        //Calendar和Date相比提供了TimeZone时区转换的功能
        TimeZone zone = TimeZone.getDefault();
        TimeZone zoneGMT9 = TimeZone.getTimeZone("GTM+09:00");
        TimeZone zoneNewYark = TimeZone.getTimeZone("America/New_York");
        System.out.println(zone.getID()); // Asia/Shanghai
        System.out.println(zoneGMT9.getID()); // GMT+09:00
        System.out.println(zoneNewYark.getID()); // America/New_York
        //时区的唯一标识是以字符串表示的ID

        //有了时区，我们就可以对指定时间进行转换
        calendar.clear();
        calendar.setTimeZone(zone);
        calendar.set(2019, 10 /* 11月*/, 20, 8, 15, 0);
        calendar.setTimeZone(zoneGMT9);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
        //利用Calendar进行时区转换的步骤是：
        //清除所有字段；
        //设定指定时区；
        //设定日期和时间；
        //创建SimpleDateFormat并设定目标时区；
        //格式化获取的Date对象（注意Date对象无时区信息，时区信息存储在SimpleDateFormat中）。
        //本质上时区转换只能通过SimpleDateFormat在显示的时候完成。

        //Calendar也可以对日期和时间进行简单的加减：
        calendar.add(Calendar.YEAR,+2);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
    }
}
