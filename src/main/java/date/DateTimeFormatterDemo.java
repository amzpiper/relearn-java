package date;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * DateTimeFormatter不但是不变对象，它还是线程安全的
 *
 * @author guoyh
 */
public class DateTimeFormatterDemo {
    public static void main(String[] args) {
        //分别以默认方式、中国地区和美国地区对当前时间进行显示
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ZZZZ", Locale.CHINA);
        System.out.println(formatter.format(ZonedDateTime.now()));

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy MM dd EE HH:mm:ss ZZZZ", Locale.CHINA);
        System.out.println(formatter1.format(ZonedDateTime.now()));

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E MMMM/dd/yyyy HH:mm:ss ZZZZ", Locale.US);
        System.out.println(formatter2.format(ZonedDateTime.now()));

        //默认的toString()方法显示的字符串就是按照ISO 8601格式显示的
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt.toString());
        System.out.println(DateTimeFormatter.ISO_DATE.format(ldt));
        System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(ldt));
    }
}
