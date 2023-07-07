package dodomu.deliverymanager.utils;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static int getYearFromYearMonthString(String yearMonth) {
        return Integer.parseInt(yearMonth.substring(0, 4));
    }
    public static int getMonthFromYearMonthString(String yearMonth) {
        return Integer.parseInt(yearMonth.substring(5, 7));
    }

    public static @NotNull String getCurrentYearMonth() {
         return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
}
