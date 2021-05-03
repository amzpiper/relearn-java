package date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author guoyh
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        //从Java 8开始，java.time包提供了新的日期和时间API，主要涉及的类型有：
        //本地日期和时间：LocalDateTime，LocalDate，LocalTime；
        //带时区的日期和时间：ZonedDateTime；
        //时刻：Instant；
        //时区：ZoneId，ZoneOffset；
        //时间间隔：Duration。
        //以及一套新的用于取代SimpleDateFormat的格式化类型DateTimeFormatter。

        //新API修正了旧API不合理的常量设计：
        //Month的范围用1~12表示1月到12月；
        //Week的范围用1~7表示周一到周日
        //放心使用不必担心被修改,新API的类型几乎全部是不变类型

        //本地日期和时间
        //通过now()获取到的总是以当前默认时区返回的,按照ISO 8601规定的日期和时间格式进行打印
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

        //有一个小问题，在获取3个类型的时候，由于执行一行代码总会消耗一点时间，
        //因此，3个类型的日期和时间很可能对不上（时间的毫秒数基本上不同）。为了保证获取到同一时刻的日期和时间，可以改写如下：
        LocalDateTime dateTime1 = LocalDateTime.now();
        LocalDate date1 = dateTime1.toLocalDate();
        LocalTime time1 = dateTime1.toLocalTime();
        System.out.println(date1);
        System.out.println(time1);
        System.out.println(dateTime1);

        //反过来，通过指定的日期和时间创建LocalDateTime可以通过of()方法：
        LocalDate date2 = LocalDate.of(2021, 12, 12);
        LocalTime time2 = LocalTime.of(12, 12, 12);
        LocalDateTime dateTime21 = LocalDateTime.of(date2, time2);
        LocalDateTime dateTime22 = LocalDateTime.of(2020,11,11,11,11,11);
        System.out.println(date2);
        System.out.println(time2);
        System.out.println(dateTime21);
        System.out.println(dateTime22);

        //按照ISO 8601的格式,字符串转换为LocalDateTime就可以传入标准格式
        LocalDateTime dateTime3 = LocalDateTime.parse("2019-11-11T11:11:11");
        LocalDate date3 = LocalDate.parse("2019-02-02");
        LocalTime time3 = LocalTime.parse("13:13:13.123");
        System.out.println(date3);
        System.out.println(time3);
        System.out.println(dateTime3);

        //注意ISO 8601规定的日期和时间分隔符是T。标准格式如下：
        //日期：yyyy-MM-dd
        //时间：HH:mm:ss
        //带毫秒的时间：HH:mm:ss.SSS
        //日期和时间：yyyy-MM-dd'T'HH:mm:ss
        //带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS

        //自定义输出的格式,把一个非ISO 8601格式的字符串解析成LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(formatter.format(LocalDateTime.now()));
        LocalDateTime localDateTime = LocalDateTime.parse("2019/12/12 12:12:12", formatter);
        System.out.println(localDateTime);

        //提供了对日期和时间进行加减的非常简单的链式调用：
        //对日期和时间进行调整则使用withXxx()方法
        LocalDateTime localDateTime2 = localDateTime.plusDays(5).minusHours(12).minusMonths(1);
        System.out.println(localDateTime2);
        LocalDateTime localDateTime3 = localDateTime.withYear(2090).withMonth(2).withDayOfMonth(28);
        System.out.println(localDateTime3);

        //通用的with()方法允许我们做更复杂的运算
        // 本月第一天0:00时刻:
        LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDay);
        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);
        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);
        // 本月第1个周一:
        LocalDate firstWeekDay = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekDay);

        //判断两个LocalDateTime的先后
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 1, 1, 1, 1);
        System.out.println(now.isAfter(target));
        System.out.println(now.isBefore(target));
        System.out.println(now.isEqual(target));
        //LocalDateTime无法与时间戳进行转换，因为LocalDateTime没有时区，无法确定某一时刻。
        //后面我们要介绍的ZonedDateTime相当于LocalDateTime加时区的组合，它具有时区，可以与long表示的时间戳进行转换。

        //Duration表示两个时刻之间的时间间隔
        Duration duration = Duration.between(now, target);
        System.out.println(duration);
        //Period表示两个日期之间的天数
        Period period = LocalDate.of(2019, 11, 30).until(LocalDate.of(2019, 12, 31));
        Period period2 = now.toLocalDate().until(target.toLocalDate());
        //PT-20487H-18M-55.043S
        System.out.println(period);
        //P1M1D
        System.out.println(period2);

        //两个LocalDateTime之间的时间间隔使用Duration表示
        //P...T之间表示日期间隔，T后面表示时间间隔。
        //如果是PT...的格式表示仅有时间间隔。利用ofXxx()或者parse()方法也可以直接创建Duration

        //和开源的Joda Time很像
        //JDK团队邀请Joda Time的作者Stephen Colebourne共同设计了java.timeAPI
    }
}
