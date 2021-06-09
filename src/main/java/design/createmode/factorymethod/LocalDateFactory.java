package design.createmode.factorymethod;

import java.time.LocalDate;

public class LocalDateFactory {
    public static LocalDate fromInt(int times) {
        return LocalDate.of(times/10000, times/100%100, times%100);
    }
}
