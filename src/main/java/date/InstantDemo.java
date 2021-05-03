package date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 计算机存储的当前时间，本质上只是一个不断递增的整数
 * 这个当前时间戳在java.time中以Instant类型表示，我们用Instant.now()获取当前时间戳
 * @author guoyh
 */
public class InstantDemo {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        // 秒
        System.out.println(instant.getEpochSecond());
        // 毫秒
        System.out.println(instant.toEpochMilli());
        //System
        System.out.println(System.currentTimeMillis());

        //Instant内部只有两个核心字段
        //一个是以秒为单位的时间戳，一个是更精确的纳秒精度
        //private final long seconds;
        //private final int nanos;
        //它和System.currentTimeMillis()返回的long相比，只是多了更高精度的纳秒。

        //时间戳，那么，给它附加上一个时区，就可以创建出ZonedDateTime
        long time = 1620037211608L;
        Instant instant1 = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = instant1.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.toLocalDateTime());
        //对于某一个时间戳，给它关联上指定的ZoneId，就得到了ZonedDateTime，继而可以获得了对应时区的LocalDateTime
        //转换的时候，只需要留意long类型以毫秒还是秒为单位即可
    }
}
