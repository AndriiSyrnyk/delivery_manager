package dodomu.deliverymanager.utils;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }

    public static List<Date> getDatesByYearMonth(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        int daysInMonth = ym.lengthOfMonth();

        List<Date> dates = new ArrayList<>();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = ym.atDay(day);
            dates.add(Date.valueOf(date));
        }

        return dates;
    }

    public static Date getFirstDateOfYearMonth(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate date = ym.atDay(1);
        return Date.valueOf(date);
    }

    public static Date getLastDateOfYearMonth(String yearMonth) {
        YearMonth ym = YearMonth.parse(yearMonth);
        int daysInMonth = ym.lengthOfMonth();
        LocalDate date = ym.atDay(daysInMonth);
        return Date.valueOf(date);
    }

    public static String timeToStringTime(Time time) {
        return time.toString().substring(0, 5);
    }

    public static Time stringTimeToTime(String timeString) {
        final int hour = Integer.parseInt(timeString.substring(0, 2));
        final int minute = Integer.parseInt(timeString.substring(3, 5));
        return new Time(hour, minute, 0);
    }
}
