package design.createmode.factorymethod;

import java.time.format.DateTimeFormatter;

public class Demo {
    public static void main(String[] args) {
        int num = 20200608;
        System.out.println(LocalDateFactory.fromInt(num).format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
