package date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 表示一个带时区的日期和时间
 * 把ZonedDateTime理解成LocalDateTime加ZoneId
 *
 * @author guoyh
 */
public class ZoneDateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
        //毫秒数不同是执行语句时的时间差
        //这种方式创建的ZonedDateTime，它的日期和时间不同

        //通过给一个LocalDateTime附加一个ZoneId，就可以变成ZonedDateTime:
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime2 = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime3 = localDateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println(localDateTime);
        System.out.println(zonedDateTime2);
        System.out.println(zonedDateTime3);
        //2021-05-03T17:21:14.367
        //2021-05-03T17:21:14.367+08:00[Asia/Shanghai]
        //2021-05-03T17:21:14.367-04:00[America/New_York]
        //这种方式创建的ZonedDateTime，它的日期和时间与LocalDateTime相同，但附加的时区不同

        //转换时区,日期和时间不同，附加的时区不同
        //由于夏令时的存在，不同的日期转换的结果很可能是不同的
        //两次转换后的纽约时间有1小时的夏令时时差
        ZonedDateTime zonedDateTime4 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zonedDateTime5 = zonedDateTime4.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime4);
        System.out.println(zonedDateTime5);

        //转换为LocalDateTime本地时间就非常简单,转换为LocalDateTime时，直接丢弃了时区信息
        LocalDateTime localDateTime1 = zonedDateTime5.toLocalDateTime();
        System.out.println(localDateTime1);

        //练习
        //某航线从北京飞到纽约需要13小时20分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。
        int hours = 13;
        int minutes = 20;
        LocalDateTime departureAtBeijing = LocalDateTime.of(2019, 9, 15, 13, 0, 0);
        LocalDateTime arrivalAtNewYork = calculateArrivalAtNY(departureAtBeijing, hours, minutes);
        System.out.println(departureAtBeijing + " -> " + arrivalAtNewYork);
        // test:
        System.out.println("test:");
        if (!LocalDateTime.of(2019, 10, 15, 14, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 10, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        } else if (!LocalDateTime.of(2019, 11, 15, 13, 20, 0)
                .equals(calculateArrivalAtNY(LocalDateTime.of(2019, 11, 15, 13, 0, 0), 13, 20))) {
            System.err.println("测试失败!");
        }
    }

    static LocalDateTime calculateArrivalAtNY(LocalDateTime bj, int h, int m) {
        ZonedDateTime zonedDateTimeShangHai = bj.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("ShangHai:"+zonedDateTimeShangHai);
        ZonedDateTime zonedDateTimeNewYork = zonedDateTimeShangHai.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("New_York:"+zonedDateTimeNewYork);
        return zonedDateTimeNewYork.toLocalDateTime().plusHours(h).plusMinutes(m);
    }
}
